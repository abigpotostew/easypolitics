package bz.stewart.bracken.db.leglislators

import bz.stewart.bracken.db.bill.database.mongodb.AbstractMongoDb
import bz.stewart.bracken.db.database.CollectionWriter
import bz.stewart.bracken.db.database.Database

/**
 * Created by stew on 6/4/17.
 */
abstract class LegislatorMongoDb(dbName: String,
                        collWriter: CollectionWriter<LegislatorData, Database<LegislatorData>>) : AbstractMongoDb<LegislatorData>(
      "", LegislatorData::class.java, collWriter) {
}