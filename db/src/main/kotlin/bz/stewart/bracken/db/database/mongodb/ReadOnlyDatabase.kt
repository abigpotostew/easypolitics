package bz.stewart.bracken.db.database.mongodb

/**
 * Created by stew on 4/1/17.
 */
class ReadOnlyDatabase(dbName: String, private val collName: String
                           ) : BillMongoDb(dbName) {
   override fun getDbName(): String {
      return super.getDbName()
   }

   override fun getCollectionName(): String {
      return collName
   }

}