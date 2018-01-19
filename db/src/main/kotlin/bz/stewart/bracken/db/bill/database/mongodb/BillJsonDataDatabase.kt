package bz.stewart.bracken.db.bill.database.mongodb

import bz.stewart.bracken.db.bill.RuntimeMode
import bz.stewart.bracken.db.bill.data.Bill
import bz.stewart.bracken.db.bill.index.DenormalizeBillText
import bz.stewart.bracken.db.database.DatabaseClient
import bz.stewart.bracken.db.file.DataWalk
import bz.stewart.bracken.db.file.FileUtils
import bz.stewart.bracken.db.file.parse.AbstractJacksonParser
import bz.stewart.bracken.db.file.parse.Parser
import com.mongodb.MongoClient
import com.mongodb.util.JSONParseException
import mu.KLogging
import org.litote.kmongo.find
import org.litote.kmongo.formatJson
import org.litote.kmongo.json
import java.io.File
import java.util.Date

/**
 * Sync the database with the congress data
 * Created by stew on 3/9/17.
 */
class BillJsonDataDatabase(val dataRoot: File,
                           dbClient: DatabaseClient<MongoClient>,
                           private val collName: String = "bills",
                           val runtimeMode: RuntimeMode = RuntimeMode.NONE,
                           val testRun: Boolean = true,
                           writer: BillWriter) : BillMongoDb(dbClient, writer) {

    companion object : KLogging()

    val parent = this
    var droppedCount: Long = 0
    val skippedWrite: MutableCollection<Bill> = mutableListOf()

    val MIN_DATE: Date = Date(Long.MIN_VALUE)

    fun billModifiedSinceLastTime(externalModTime: Date?, billFromDb: Bill): Boolean {
        val externalModifiedTime = externalModTime ?: MIN_DATE
        val internalLastModifiedTime = billFromDb.getLastModified() ?: throw RuntimeException(
            "Bill from database shouldn't have a null modified date: $billFromDb")
        return externalModifiedTime.after(internalLastModifiedTime)

    }

    fun shouldUpdate(externalModTime: Date?, billFromDb: Bill?): Boolean {
        return billFromDb == null || runtimeMode == RuntimeMode.RESET || (runtimeMode == RuntimeMode.UPDATE && billModifiedSinceLastTime(
            externalModTime,
            billFromDb))
    }

    /**
     * get the last mod time either from timestamp file, or from the updated at from bill in data.json
     */
    fun getBillExternalModifiedTime(lastModFile: File?, billFromFile: Bill): Date? {
        return if (lastModFile != null) {
            FileUtils.parseLastModDate(lastModFile)
        } else {
            billFromFile.getLastModified()
        }
    }

    fun loadData(onlyParseCongresNum: Collection<String>? = listOf("115")) {
        var countParsed = 0
        var countWritten = 0

        getWriter().before(this)

        val fileWalker = DataWalk(dataRoot, onlyParseCongresNum,
            object : AbstractJacksonParser<Bill>(clazz), Parser {
                override fun parseData(uniqueId: String, data: File,
                                       lastModified: File?) {

                    val bill = readMap(data)
                    var existingBill: Bill? = null
                    countParsed++

                    try {// TODO skip this query if in reset mode?
                        parent.queryCollection(collName, {
                            val found = find(
                                "{bill_id:${bill.bill_id.json} }".formatJson())
                            existingBill = found.first()
                        })
                    } catch (e: JSONParseException) {
                        logger.info { "Error $e" }
                    }
                    val externalModTime = getBillExternalModifiedTime(
                        lastModified, bill)
                    if (shouldUpdate(externalModTime, existingBill)) {
                        bill.setLastModified(externalModTime)
                        setCompoundText(bill)
                        getWriter().write(bill, collName, parent)
                        countWritten++
                    } else {
                        skippedWrite.add(bill)
                        logger.debug { "Skipping ${bill.bill_id} because db entry is up to date." }
                    }
                }

                override fun onComplete() {
                    getWriter().after(parent)
                    logger.info { "============================================================" }
                    logger.info { "== Finished parsing data. Stats:" }
                    if (runtimeMode == RuntimeMode.RESET) {
                        logger.info { "== Dropped: $droppedCount" }
                    }
                    logger.info { "== Parsed: $countParsed" }
                    logger.info { "== Wrote: $countWritten" }
                    logger.info { "== Skipped: ${skippedWrite.size}" }
                    if (testRun) {
                        logger.info { "== Test mode: no data was written to database." }
                    }
                    logger.info { "============================================================" }
                }
            })
        if (runtimeMode == RuntimeMode.RESET) {
            droppedCount = getCollection(collName)?.count() ?: 0
            getWriter().drop(collName, parent)
        }
        fileWalker.traverse()

    }

    fun setCompoundText(bill:Bill){
        DenormalizeBillText(bill).setDenormalizedText()
    }

    @Deprecated(
        "Collection should not be persisted, pass in collection name each call rather than save it.")
    override fun getCollectionName(): String {
        return collName
    }

}