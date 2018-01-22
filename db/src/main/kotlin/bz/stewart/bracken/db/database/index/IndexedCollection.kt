package bz.stewart.bracken.db.database.index

import bz.stewart.bracken.db.database.DbItem
import com.mongodb.client.MongoCollection

interface IndexedCollection<T: DbItem> {
    val collection: MongoCollection<T>
    val indicies: Set<IndexedField<T>>

    //fun createBson(field: IndexedField<T>): Bson

    fun isInCollection(indexedField: IndexedField<T>): Boolean

    fun addIndexToCollection(indexedField: IndexedField<T>): Boolean
}

abstract class AbstractIndexedCollection<T: DbItem>(override val collection: MongoCollection<T>) : IndexedCollection<T> {


    override fun isInCollection(indexedField: IndexedField<T>): Boolean {
        val indexes = collection.listIndexes()
        return indexes.any { it["name"] == indexedField.name }
    }

    override fun addIndexToCollection(indexedField: IndexedField<T>): Boolean {
        this.collection.createIndex(indexedField.toBson())
        return true
    }
}