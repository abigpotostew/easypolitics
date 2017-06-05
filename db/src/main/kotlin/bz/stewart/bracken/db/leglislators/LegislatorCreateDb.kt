package bz.stewart.bracken.db.leglislators

import bz.stewart.bracken.db.database.CollectionWriter
import bz.stewart.bracken.db.database.Database
import bz.stewart.bracken.db.leglislators.data.LegislatorData

/**
 * Created by stew on 6/4/17.
 */
class LegislatorCreateDb(dbName: String,
                         writer: CollectionWriter<LegislatorData, Database<LegislatorData>>) : LegislatorMongoDb(
      dbName, writer) {
   override fun getCollectionName(): String {
      return "current"
   }
}