package bz.stewart.bracken.db

import bz.stewart.bracken.db.bill.data.Bill
import bz.stewart.bracken.db.bill.database.mongodb.ReadOnlyDatabase
import bz.stewart.bracken.db.bill.index.BillIndexDefinition
import bz.stewart.bracken.db.database.ClientBuilder
import com.mongodb.client.MongoCollection
import mu.KLogging

class SyncAllIndex : DbRuntime {

    companion object : KLogging()

    override fun validateArgs() {
        return
    }

    override fun run() {
        val client = ClientBuilder("congress2").createClient()
        val db = ReadOnlyDatabase(client, "bills")
        db.use {
            db.openDatabase()
            val coll: MongoCollection<Bill> = db.getTargetCollection() ?: return

            val billIndex = BillIndexDefinition(coll)

            for (idx in billIndex.indicies) {
                if (!billIndex.isInCollection(idx)) {
                    billIndex.addIndexToCollection(idx)
                } else {
                    logger.info { "Skipping '${idx.name}' because the collection already has this index." }
                }
            }
        }
    }
}