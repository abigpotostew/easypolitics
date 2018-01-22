package bz.stewart.bracken.db.sync

import bz.stewart.bracken.db.DbRuntime
import bz.stewart.bracken.db.bill.database.mongodb.ReadOnlyBillDatabase
import bz.stewart.bracken.db.bill.index.BillIndexDefinition
import bz.stewart.bracken.db.database.ClientBuilder
import bz.stewart.bracken.db.database.DbItem
import bz.stewart.bracken.db.database.index.IndexedCollection
import bz.stewart.bracken.db.database.mongo.AbstractMongoDb
import bz.stewart.bracken.db.database.mongo.emptyDatabaseWriter
import bz.stewart.bracken.db.leglislators.LegislatorCreateDb
import bz.stewart.bracken.db.leglislators.index.LegislatorIndexDefinition
import com.mongodb.client.MongoCollection
import mu.KLogging

class SyncAllIndex(val args: SyncIndexArgs) : DbRuntime {

    companion object : KLogging()

    override fun validateArgs() {
        if (!args.hasValidClientArgs()) {
            throw IllegalArgumentException(args.getInvalidClientArgsMessage())
        }
    }

    override fun run() {
        val client = ClientBuilder(args).createClient()

//        syncBillIndexes(client)
//        syncLegislatorsIndexes(client)

        logger.info { "Syncing index for bill collection." }
        syncIndexGeneric(ReadOnlyBillDatabase(client), { BillIndexDefinition(it) })
        logger.info { "Syncing index for LegislatorData collection." }
        syncIndexGeneric(LegislatorCreateDb(client, emptyDatabaseWriter()), { LegislatorIndexDefinition(it) })

        logger.info { finalMessage() }
    }

//    private fun syncBillIndexes(client: DatabaseClient<MongoClient>) {
//        // Connect to the 'bills' collection.
//        val db = ReadOnlyBillDatabase(client)
//        db.use {
//            db.openDatabase()
//            val coll: MongoCollection<Bill> = db.getTargetCollection() ?: return
//
//            val billIndex = BillIndexDefinition(coll)
//
//            for (idx in billIndex.indicies) {
//                if (!billIndex.isInCollection(idx)) {
//                    logger.info { "Adding new index '${idx.name}' to collection ${db.getCollectionName()}." }
//                    if (!this.args.test) {
//                        billIndex.addIndexToCollection(idx)
//                    }
//                } else {
//                    logger.info { "Skipping '${idx.name}' because the collection already has this index." }
//                }
//            }
//        }
//    }
//
//    private fun syncLegislatorsIndexes(client: DatabaseClient<MongoClient>) {
//        val db = LegislatorCreateDb(client, emptyDatabaseWriter())
//        db.use {
//            db.openDatabase()
//            val coll = db.getTargetCollection() ?: return
//            val index = LegislatorIndexDefinition(coll)
//            for (idx in index.indicies) {
//
//            }
//        }
//    }

    private fun <T : DbItem> syncIndexGeneric(db: AbstractMongoDb<T>,
                                              indexBuilder: (MongoCollection<T>) -> IndexedCollection<T>) {
        db.use {
            db.openDatabase()
            val coll: MongoCollection<T> = db.getTargetCollection() ?: return
            val indexDef = indexBuilder(coll)
            for (idx in indexDef.indicies) {
                if (!indexDef.isInCollection(idx)) {
                    logger.info { "Adding new index '${idx.name}' to collection ${db.getCollectionName()}." }
                    if (!this.args.test) {
                        indexDef.addIndexToCollection(idx)
                    }
                } else {
                    logger.info { "Skipping '${idx.name}' because the collection already has this index." }
                }
            }
        }
    }

    private fun finalMessage(): String {
        val out = "Sync all index complete."
        return if (this.args.test) {
            out + " Test mode enabled. No database modifications were made."
        } else {
            out
        }
    }
}