package bz.stewart.bracken.db.bill.database.mongodb

import bz.stewart.bracken.db.database.DatabaseClient
import com.mongodb.MongoClient

/**
 * Created by stew on 4/1/17.
 */
class ReadOnlyDatabase(dbClient: DatabaseClient<MongoClient>, private val collName: String
                           ) : BillMongoDb(dbClient) {
   override fun getDbName(): String {
      return super.getDbName()
   }

   override fun getCollectionName(): String {
      return collName
   }

}