package bz.stewart.bracken.db.leglislators

import bz.stewart.bracken.db.database.mongo.AbstractMongoDb
import bz.stewart.bracken.db.database.mongo.CollectionWriter
import bz.stewart.bracken.db.database.Database
import bz.stewart.bracken.db.leglislators.data.LegislatorData

/**
 * Created by stew on 6/4/17.
 */
abstract class LegislatorMongoDb(dbName: String,
                                 collWriter: CollectionWriter<LegislatorData, Database<LegislatorData>>) : AbstractMongoDb<LegislatorData>(
      dbName, LegislatorData::class.java, collWriter) {
}