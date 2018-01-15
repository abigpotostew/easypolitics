package bz.stewart.bracken.db.database.index

import kotlin.reflect.KProperty1

interface IndexedField<T> {
    val name: String
    val type: T
}

class KIndexedField<T>(val field: KProperty1<T, Any>) : IndexedField<Int> {
    override val name: String = field.name
    override val type: Int
        get() = TODO()
}

interface KField<T> {
    val name: String
}

class SingleKField<T>(val field: KProperty1<T, Any>) : KField<T> {
    override val name: String = field.name
}

class NestedKField<T, T2, T3>(val first:KProperty1<T, T2>, val second:KProperty1<T2, T3>):KField<T> {
    override val name: String

    private fun getNestedName():String{
        // the fields cnames joined by period
    }
}