package bz.stewart.bracken.db.database.index

import bz.stewart.bracken.db.database.DbItem
import bz.stewart.bracken.db.database.mongo.AbstractMongoDb
import com.mongodb.client.MongoCollection
import mu.KLogging

/**
 * Open db, sync index, then close.
 */
class SyncIndex<T : DbItem>(private val db: AbstractMongoDb<T>,
                            private val indexBuilder: (MongoCollection<T>) -> IndexedCollection<T>):IndexSyncAction {

    companion object : KLogging()

    override fun doSync(testMode:Boolean) {
        db.use {
            db.openDatabase()
            InlineSyncIndex(db, indexBuilder)
        }
    }
}