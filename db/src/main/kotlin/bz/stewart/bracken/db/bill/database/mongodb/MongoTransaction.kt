package bz.stewart.bracken.db.bill.database.mongodb

import bz.stewart.bracken.db.bill.RuntimeMode
import bz.stewart.bracken.db.bill.SetupBillRuntime
import bz.stewart.bracken.db.bill.data.Bill
import bz.stewart.bracken.db.database.DatabaseClient
import bz.stewart.bracken.db.database.Transaction
import bz.stewart.bracken.db.database.mongo.AbstractMongoDb
import bz.stewart.bracken.db.database.mongo.CollectionWriter
import bz.stewart.bracken.db.debug.DebugUtils
import com.mongodb.MongoClient
import com.mongodb.MongoCommandException
import com.mongodb.MongoTimeoutException
import com.mongodb.MongoWriteException
import mu.KLogging
import java.io.File

/**
 * Created by stew on 3/12/17.
 */
class MongoTransaction(val dbClient: DatabaseClient<MongoClient>,
                       val data: File,
                       val test: Boolean,
                       val mode: RuntimeMode,
                       val congressParseLimit: Set<Int>) : Transaction<Bill, AbstractMongoDb<Bill>> {
    companion object : KLogging()

    private var writer: CollectionWriter<Bill, AbstractMongoDb<Bill>>? = null
    private var db: BillJsonDataDatabase? = null
    private var startedTransaction: Boolean = false

    override fun setWriter(writer: CollectionWriter<Bill, AbstractMongoDb<Bill>>) {
        this.writer = writer
    }

    override fun beginTransaction() {
        val writer = when (test) {
            true -> emptyBillWriter()
            false -> SingleBillWriter()
        }
        val collName = "bills"
        db = BillJsonDataDatabase(this.data, this.dbClient, collName, mode, test, writer)
        SetupBillRuntime.logger.info { "Loading bill into database@${this.dbClient.databaseName} in collection@$collName with mode@$mode and test@$test" }
        db!!.openDatabase()
        startedTransaction = true
    }

    override fun execute() {
        if (!startedTransaction) {
            throw IllegalStateException("trying to run MongoTransaction before calling beginTransaction() or before starting successfully.")
        }
        try {
            //todo do the drop db here
            db!!.loadData(if (congressParseLimit.isEmpty()) null else congressParseLimit.map(Int::toString))//todo move this write logic to a writer
            //todo have loadData return the stats.
            //todo print the stats here
        } catch (e: MongoCommandException) {
            abort("Mongo exception: ${DebugUtils.stackTraceToString(e)}")
        } catch (e: MongoWriteException) {
            abort("Mongo write error: $e @ $e")
        } catch (e: MongoTimeoutException) {
            logger.error { "Timeout while connecting to the database. Is mongod running? @ $e" }
            abort("Aborting due to mongod timeout @ $e")
        } catch (e: RuntimeException) {
            abort("Unknown error, this should be fixed:\n\n ${DebugUtils.stackTraceToString(e)}")
        }
    }

    override fun endTransaction() {
        if (startedTransaction) {
            db!!.closeDatabase()
        }
        startedTransaction = false
    }

    override fun abort(msg: String) {
        logger.error { "Aborting transaction: $msg" }
        endTransaction()
        error("Aborting transaction.")
    }
}