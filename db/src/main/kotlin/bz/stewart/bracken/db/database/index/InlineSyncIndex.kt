package bz.stewart.bracken.db.database.index

import bz.stewart.bracken.db.database.DbItem
import bz.stewart.bracken.db.database.mongo.AbstractMongoDb
import com.mongodb.client.MongoCollection
import mu.KLogging

/**
 * Do not catch anything. Do not open or close. Assume the db is open. Just grab the collection and sync the index def.
 */
class InlineSyncIndex<T : DbItem>(private val db: AbstractMongoDb<T>,
                                  private val indexBuilder: (MongoCollection<T>) -> IndexedCollection<T>) :IndexSyncAction {

    companion object : KLogging()

    override fun doSync(testMode:Boolean) {
        val coll: MongoCollection<T> = db.getTargetCollection() ?: return
        val indexDef = indexBuilder(coll)
        for (idx in indexDef.indicies) {
            if (!indexDef.isInCollection(idx)) {
                logger.info { "Adding new index '${idx.name}' to collection ${db.getCollectionName()}." }
                if (!testMode) {
                    indexDef.addIndexToCollection(idx)
                }
            } else {
                logger.info { "Skipping '${idx.name}' because the collection already has this index." }
            }
        }
    }
}