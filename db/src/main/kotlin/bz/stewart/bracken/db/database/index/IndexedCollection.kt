package bz.stewart.bracken.db.database.index

import com.mongodb.client.MongoCollection

interface IndexedCollection<T> {
    val collection: MongoCollection<T>
    val indicies: Set<IndexedField<T>>

    //fun createBson(field: IndexedField<T>): Bson

    fun isInCollection(indexedField: IndexedField<T>): Boolean

    fun addIndexToCollection(indexedField: IndexedField<T>): Boolean
}

abstract class AbstractIndexedCollection<T>(override val collection: MongoCollection<T>) : IndexedCollection<T> {


    override fun isInCollection(indexedField: IndexedField<T>): Boolean {
        val idxs = collection.listIndexes()
        return idxs.any { it.get("name") == indexedField.name }
    }

    override fun addIndexToCollection(indexedField: IndexedField<T>): Boolean {
        this.collection.createIndex(indexedField.toBson())
        return true
    }
}