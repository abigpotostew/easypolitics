package bz.bracken.stewart.db.mongodb

import bz.stewart.bracken.db.data.Bill
import bz.stewart.bracken.db.database.mongodb.BillMongoDb
import mu.KLogging

/**
 * Created by stew on 3/9/17.
 */
class DebugDatabase(dbName: String = "congress1"
                   ) : BillMongoDb(dbName) {
   override fun getCollectionName(): String {
      return "bills115"
   }

   companion object: KLogging()

   fun testDbConnection() {
      logger.info{"============================================================"}
      logger.info{"Testing DB - Start"}
      logger.info{"============================================================"}

      this.queryCollection(getCollectionName(), {
         val res: Bill? = find()?.first()
         if (res?.bill_id == "hconres1-115") {
            logger.info{"SUCCESS -- queried test data"}
         }
         //println(res)
      })
      logger.info{"============================================================"}
      logger.info{"Testing DB - End"}
      logger.info{"============================================================"}
   }
}