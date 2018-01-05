package bz.stewart.bracken.db.database.mongo

import bz.stewart.bracken.db.database.Database
import bz.stewart.bracken.db.database.DbItem

/**
 * Created by stew on 6/6/17.
 */
class DefaultMongoDb<T : DbItem>(_databaseName: String, _defaultCollection: String,
                                 clazz: Class<T>,
                                 writer: CollectionWriter<T, Database<T>> = emptyDatabaseWriter()) : AbstractMongoDb<T>(
        _databaseName, clazz, writer) {
    private val defaultCollection = _defaultCollection
    override fun getCollectionName(): String {
        return defaultCollection
    }
}