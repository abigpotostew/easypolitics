package bz.stewart.bracken.db.leglislators

import bz.stewart.bracken.db.database.DatabaseClient
import bz.stewart.bracken.db.database.mongo.AbstractMongoDb
import bz.stewart.bracken.db.database.mongo.CollectionWriter
import bz.stewart.bracken.db.leglislators.data.LegislatorData
import com.mongodb.MongoClient

/**
 * Created by stew on 6/4/17.
 */
class LegislatorCreateDb(client: DatabaseClient<MongoClient>,
                         writer: CollectionWriter<LegislatorData, AbstractMongoDb<LegislatorData>>)
   : LegislatorMongoDb(client, writer) {
   override fun getCollectionName(): String {
      return "legislators"
   }
}