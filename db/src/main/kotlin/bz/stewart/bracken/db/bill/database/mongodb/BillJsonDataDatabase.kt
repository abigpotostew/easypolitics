package bz.stewart.bracken.db.bill.database.mongodb

import bz.stewart.bracken.db.RuntimeMode
import bz.stewart.bracken.db.bill.data.Bill
import bz.stewart.bracken.db.database.CollectionWriter
import bz.stewart.bracken.db.database.Database
import bz.stewart.bracken.db.file.DataWalk
import bz.stewart.bracken.db.file.FileUtils
import bz.stewart.bracken.db.file.parse.AbstractJacksonParser
import bz.stewart.bracken.db.file.parse.Parser
import com.mongodb.util.JSONParseException
import mu.KLogging
import org.litote.kmongo.find
import org.litote.kmongo.formatJson
import org.litote.kmongo.json
import java.io.File
import java.util.*

/**
 * Created by stew on 3/9/17.
 */
class BillJsonDataDatabase(val dataRoot: File, dbName: String, private val collName: String,
                           val runtimeMode: RuntimeMode = RuntimeMode.NONE, val testRun: Boolean = true,
                           writer: BillWriter) : BillMongoDb(dbName, writer as CollectionWriter<Bill, Database<Bill>>) {

   companion object : KLogging()

   val parent = this
   var droppedCount:Long = 0
   val skippedWrite:MutableCollection<Bill> = mutableListOf()

   val MIN_DATE: Date = Date(Long.MIN_VALUE)

   fun billModifiedSinceLastTime(externalModTime: Date?, billFromDb: Bill): Boolean {
//      val externalModifiedTime = if (lastModFile != null) {
//         FileUtils.parseLastModDate(lastModFile)
//      }
//      else if (billFromFile.getLastModified() != null) {
//         billFromFile.getLastModified()
//      }
//      else {
//         MIN_DATE
//      }
      val externalModifiedTime = externalModTime ?: MIN_DATE
      val internalLastModifiedTime = billFromDb.getLastModified() ?: throw RuntimeException(
            "Bill from database shouldn't have a null modified date: $billFromDb")
      return externalModifiedTime!!.after(internalLastModifiedTime)

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
      val externalModifiedTime: Date? = if (lastModFile != null) {
         FileUtils.parseLastModDate(lastModFile)
      }
      else {
         billFromFile.getLastModified()
      }
      return externalModifiedTime
   }

   fun loadData(onlyParseCongresNum: Collection<String>? = listOf("115")) {

      //val collection = getCollection(collName)

      var countParsed: Int = 0
      var countWritten: Int = 0

      getWriter().before(this)

      val fileWalker = DataWalk(dataRoot, onlyParseCongresNum, object : AbstractJacksonParser<Bill>(clazz), Parser {
         override fun parseData(uniqueId: String, data: File, lastModified: File?) {

            val bill = readMap(data)
            var existingBill: Bill? = null
            countParsed++

            try {// TODO skip this query if in reset mode?
               parent.queryCollection(collName, {
                  val found = find("{getBillId:${bill.bill_id.json} }".formatJson())
                  existingBill = found.first()
               })
            } catch (e: JSONParseException) {
               logger.info { "Error $e" }
            }
            val externalModTime = getBillExternalModifiedTime(lastModified, bill)
            if (shouldUpdate(externalModTime, existingBill)) {
               //if((runtimeMode==RuntimeMode.UPDATE && shouldUpdate(lastModified,bill,existingBill)) || runtimeMode==RuntimeMode.RESET ){
               //need to do the lastmodified logic here
               bill.setLastModified(externalModTime)
               getWriter().write(bill, collName, parent)
               countWritten++
            }
            else {
               skippedWrite.add(bill)
               logger.debug { "Skipping ${bill.bill_id} because db entry is up to date." }
            }
         }

         override fun onComplete() {
            getWriter().after(parent)
//            println("============================================================")
//            println("== Parsed: $countParsed\n== Wrote: $countWritten")
//            println("============================================================")
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
      //try {
      if (runtimeMode == RuntimeMode.RESET) {
         //writer.before(this)
         droppedCount = getCollection(collName)?.count() ?: 0
         getWriter().drop(collName, parent)
         //writer.after(this)
      }
      fileWalker.traverse()
      //}
      // catch (e: Exception) {
      //   logger.error { e.toString() }
//       finally {
//         closeDatabase()
//      }

   }

   @Deprecated("Collection should not be persisted, pass in collection name each call rather than save it.")
   override fun getCollectionName(): String {
      return collName
   }

}