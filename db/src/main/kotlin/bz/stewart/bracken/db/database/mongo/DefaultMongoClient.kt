package bz.stewart.bracken.db.database.mongo

import bz.stewart.bracken.db.database.DatabaseClient
import com.mongodb.MongoClient
import org.litote.kmongo.KMongo

class DefaultMongoClient(override val databaseName: String) :DatabaseClient<MongoClient> {
    override fun createClient(): MongoClient {
        return KMongo.createClient()
    }
}