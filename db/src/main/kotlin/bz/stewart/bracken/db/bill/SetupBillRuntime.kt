package bz.stewart.bracken.db.bill

import bz.stewart.bracken.db.DbRuntime
import bz.stewart.bracken.db.bill.database.mongodb.MongoTransaction
import bz.stewart.bracken.db.database.ClientBuilder
import bz.stewart.bracken.db.database.DatabaseClient
import bz.stewart.bracken.db.database.TimedTransaction
import com.mongodb.MongoClient
import mu.KLogging

/**
 * Created by stew on 3/11/17.
 */
class SetupBillRuntime(private val args: BillArguments) : DbRuntime {

    companion object : KLogging()

    private fun getArgError(): String? {
        if (!this.args.data.exists()) {
            return "Data folder does not exist @ '${this.args.data}'"
        }
        if (!this.args.data.canRead()) {
            return "Cannot read data directory @ '${this.args.data}'"
        }
        if (this.args.mode == RuntimeMode.NONE) {
            return "Bad runtime mode of ${this.args.mode}"
        }
        if (!args.hasValidClientArgs()) {
            return args.getInvalidClientArgsMessage()
        }
        return null
    }

    override fun validateArgs() {
        val error: String? = getArgError() ?: return
        throw IllegalArgumentException(error)
    }

    override fun run() {
        val dbClient = createClient()
        val transaction = TimedTransaction(MongoTransaction(dbClient,
            this.args.data,
            this.args.test,
            this.args.mode,
            this.args.onlyParseCongressNums.toSet()))

        transaction.beginTransaction()
        transaction.execute()
        transaction.endTransaction()

        logger.info { "Transaction complete! - $transaction" }
    }

    private fun createClient(): DatabaseClient<MongoClient> {
        return ClientBuilder(this.args.dbName,
            this.args.hostname,
            this.args.port,
            this.args.username,
            this.args.password).createClient()
    }
}