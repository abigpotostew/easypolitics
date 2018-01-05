package bz.stewart.bracken.db.bill.database.mongodb

import bz.stewart.bracken.db.BillArguments
import bz.stewart.bracken.db.RuntimeMode
import bz.stewart.bracken.db.SetupDbRuntime
import bz.stewart.bracken.db.bill.data.Bill
import bz.stewart.bracken.db.database.mongo.AbstractMongoDb
import bz.stewart.bracken.db.database.mongo.CollectionWriter
import bz.stewart.bracken.db.database.Transaction
import com.mongodb.MongoTimeoutException
import com.mongodb.MongoWriteException
import mu.KLogging
import java.io.File

/**
 * Created by stew on 3/12/17.
 */
class MongoTransaction(val args: BillArguments) : Transaction<Bill, AbstractMongoDb<Bill>> {
   companion object : KLogging()

   val data: File = args.data
   val test: Boolean = args.test
   val mode: RuntimeMode = args.mode
   val dbName: String = args.dbName
   val congressParseLimit:Set<Int> = args.onlyParseCongressNums.toSet()

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
      //val dbName = "congress1"
      val collName = "bills"
      db = BillJsonDataDatabase(data, dbName, collName, mode, test, writer)
      SetupDbRuntime.logger.info { "Loading bill into database@$dbName in collection@$collName with mode@$mode and test@$test" }
      db!!.openDatabase()
      startedTransaction = true
   }

   override fun execute() {
      if (!startedTransaction) {
         throw IllegalStateException("trying to run MongoTransaction before calling beginTransaction() or before starting successfully.")
      }
      try {
         //todo do the drop db here
         db!!.loadData( if (congressParseLimit.isEmpty()) null else congressParseLimit.map(Int::toString))//todo move this write logic to a writer
         //todo have loadData return the stats.
         //todo print the stats here
      }catch (e:RuntimeException){
         logger.error { "Runtime error, this should be fixed @ ${e}" }
      }catch(e:MongoWriteException){
         abort("Mongo write error: $e @ ${e}")
      }catch (e:MongoTimeoutException){
         logger.error { "Timeout while connecting to the database. Is mongod running? @ ${e}" }
         abort("Aborting due to mongod timeout @ ${e}")
      }
   }

   override fun endTransaction() {
      if (startedTransaction) {
         db!!.closeDatabase()
      }
      startedTransaction = false
   }

   override fun abort(msg:String) {
      logger.error { "Aborting transaction: $msg" }
      endTransaction()
      error("Aborting transaction.")
   }
}