package bz.stewart.bracken.db.database.index

import kotlin.reflect.KClass
import kotlin.reflect.KProperty1

enum class IndexSortTypes(val directionValue: Int) {
    ASCENDING(1),
    DESCENDING(-1)
    ;
}

object IndexFieldBuilder {
    fun <T : Any> create(field: KProperty1<T, Any>, clazz: KClass<T>, sortDirection: IndexSortTypes): IndexedField<T> {
        val kField = SingleKField(field, clazz)
        val index = StandardIndexedField(kField, sortDirection)
        return index
    }

    fun <T : Any, T2 : Any> createText(first: KProperty1<T, Array<T2>>,
                                       clazz: KClass<T>,
                                       second: KProperty1<T2, Any>,
                                       clazz2: KClass<T2>): IndexedField<T> {
        val kField = NestedKField(first, clazz, second, clazz2)
        val index = TextIndexedField(kField)
        return index
    }
}