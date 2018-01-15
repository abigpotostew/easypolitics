package bz.stewart.bracken.db

import bz.stewart.bracken.db.bill.database.mongodb.MongoTransaction
import bz.stewart.bracken.db.database.DatabaseClient
import bz.stewart.bracken.db.database.DbItem
import bz.stewart.bracken.db.database.TimedTransaction
import bz.stewart.bracken.db.database.mongo.DefaultMongoClient
import bz.stewart.bracken.db.database.mongo.RemoteMongoClient
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
        if (this.args.hostname != null && (this.args.username == null || this.args.password == null)) {
            return "Username (-u) and password (-p) are required when specifying the host (--host)"
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
        val host = this.args.hostname
        val port = this.args.port
        val user = this.args.username
        val pass = this.args.password
        if (host != null && user != null && pass != null) {
            logger.info { "Remote db connection: host = '$host', port = '$port', user = '$user'" }
            return RemoteMongoClient(host, port?.toInt(), this.args.dbName, user, pass.toCharArray())
        } else {
            logger.info { "Setting up DB client with all default settings." }
            return DefaultMongoClient(this.args.dbName)
        }
    }
}