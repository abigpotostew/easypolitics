package bz.stewart.bracken.db.database.mongo

import bz.stewart.bracken.db.database.Database
import bz.stewart.bracken.db.database.DatabaseClient
import bz.stewart.bracken.db.database.DbItem
import com.mongodb.MongoClient

/**
 * Created by stew on 6/6/17.
 */
class DefaultMongoDb<T : DbItem>(dbClient: DatabaseClient<MongoClient>, _defaultCollection: String,
                                 clazz: Class<T>,
                                 writer: CollectionWriter<T, Database<T>> = emptyDatabaseWriter()) : AbstractMongoDb<T>(
    dbClient, clazz, writer) {
    private val defaultCollection = _defaultCollection
    override fun getCollectionName(): String {
        return defaultCollection
    }
}