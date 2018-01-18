package bz.stewart.bracken.db.database.index

import com.mongodb.BasicDBObject


interface IndexedField<T> {
    val name: String
    fun toBson(): BasicDBObject
}

abstract class IndexedKField<T>(val field: KField<T>) : IndexedField<T> {
}

class StandardIndexedField<T>(field: KField<T>, val sortDirection: IndexSortTypes) : IndexedKField<T>(field) {
    override val name = field.name + "_" + sortDirection.directionValue
    override fun toBson(): BasicDBObject {
        val keys = BasicDBObject()
        keys.put(this.field.name, this.sortDirection.directionValue)
        return keys
    }
}

class TextIndexedField<T>(field: KField<T>) : IndexedKField<T>(field) {
    override val name: String = field.name + "_" + "text"
    override fun toBson(): BasicDBObject {
        val keys = BasicDBObject()
        keys.put(this.field.name, "text")
        return keys
    }
}