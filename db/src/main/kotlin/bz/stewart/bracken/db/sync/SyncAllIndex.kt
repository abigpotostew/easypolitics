package bz.stewart.bracken.db.sync

import bz.stewart.bracken.db.DbRuntime
import bz.stewart.bracken.db.bill.database.mongodb.ReadOnlyBillDatabase
import bz.stewart.bracken.db.bill.index.BillIndexDefinition
import bz.stewart.bracken.db.database.ClientBuilder
import bz.stewart.bracken.db.database.index.SyncIndex
import bz.stewart.bracken.db.database.mongo.emptyDatabaseWriter
import bz.stewart.bracken.db.leglislators.LegislatorCreateDb
import bz.stewart.bracken.db.leglislators.index.LegislatorIndexDefinition
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
        
        logger.info { "Syncing index for bill collection." }
        SyncIndex(ReadOnlyBillDatabase(client), { BillIndexDefinition(it) }).doSync(this.args.test)
        logger.info { "Syncing index for LegislatorData collection." }
        SyncIndex(LegislatorCreateDb(client, emptyDatabaseWriter()),
            { LegislatorIndexDefinition(it) }).doSync(this.args.test)

        logger.info { finalMessage() }
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