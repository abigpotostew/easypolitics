package bz.stewart.bracken.db.database

import bz.stewart.bracken.db.database.mongo.DefaultMongoClient
import bz.stewart.bracken.db.database.mongo.RemoteMongoClient
import com.mongodb.MongoClient
import mu.KLogging

class ClientBuilder(val dbName: String,
                    private val host: String? = null,
                    val port: String? = null,
                    val user: String? = null,
                    val pass: String? = null) {

    companion object : KLogging()

    fun createClient(): DatabaseClient<MongoClient> {
        return if (this.host != null && this.user != null && this.pass != null) {
            logger.info { "Remote db connection: host = '${this.host}', port = '${this.port}', user = '${this.user}'" }
            RemoteMongoClient(this.host, this.port?.toInt(), this.dbName, this.user, this.pass.toCharArray())
        } else {
            logger.info { "Setting up DB client with all default settings." }
            DefaultMongoClient(this.dbName)
        }
    }
}