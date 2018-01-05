package bz.stewart.bracken.db.database.mongo

import bz.stewart.bracken.db.database.DatabaseClient
import com.mongodb.MongoClient
import com.mongodb.MongoCredential
import com.mongodb.ServerAddress
import org.litote.kmongo.KMongo

class RemoteMongoClient(private val host: String,
                        private val port: Int?,
                        override val databaseName: String,
                        private val username: String,
                        private val password: CharArray) : DatabaseClient<MongoClient> {

    override fun createClient(): MongoClient {
        val clientCreds = MongoCredential.createCredential(username, databaseName, password)
        val address = if (port != null) {
            ServerAddress(host, port)
        } else {
            ServerAddress(host)
        }
        return KMongo.createClient(address, listOf(clientCreds))
    }
}