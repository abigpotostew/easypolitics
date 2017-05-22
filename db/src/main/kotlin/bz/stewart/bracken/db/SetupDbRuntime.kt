package bz.stewart.bracken.db

import bz.stewart.bracken.db.database.DbItem
import bz.stewart.bracken.db.database.TimedTransaction
import bz.stewart.bracken.db.database.mongodb.MongoTransaction
import mu.KLogging
import java.io.File

/**
 * Created by stew on 3/11/17.
 */
class SetupDbRuntime<Bill : DbItem>(var args: Arguments) : DbRuntime<Bill> {

   companion object : KLogging()

   val data: File = args.data
   val test: Boolean = args.test
   val mode: RuntimeMode = args.mode

   val transaction = TimedTransaction(MongoTransaction(args))

   private fun getArgError():String? {
      if (!data.exists()){
         return "Data folder does not exist @ '$data'"
      }
      if(!data.canRead()) {
         return "Cannot read data directory @ '$data'"
      }
      if (mode == RuntimeMode.NONE){
         return "Bad runtime mode of $mode"
      }
      return null
   }

   override fun validateArgs() {
      val error:String?= getArgError() ?: return
      throw IllegalArgumentException(error)
   }

   override fun run() {
      transaction.beginTransaction()
      transaction.execute()
      transaction.endTransaction()

      logger.info { "Transaction complete! - $transaction" }
   }

}