package bz.stewart.bracken.db.database.index

import kotlin.reflect.KClass
import kotlin.reflect.KProperty1

enum class IndexSortTypes(val directionValue: Int) {
    ASCENDING(1),
    DESCENDING(-1)
    ;
}

object IndexFieldBuilder {
    fun <T : Any> create(field: KProperty1<T, Any>, clazz: KClass<T>,
                         sortDirection: IndexSortTypes): IndexedField<T> {
        val kField = SingleKField(field, clazz)
        return StandardIndexedField(kField, sortDirection)
    }

    fun <T : Any, T2 : Any> create(first: KProperty1<T, T2>,
                                   firstContextClass: KClass<T>,
                                   second: KProperty1<T2, Any>,
                                   secondContextClass: KClass<T2>,
                                   sortDirection: IndexSortTypes): IndexedField<T> {
        val kField = NestedKField(first, firstContextClass, second, secondContextClass)
        return StandardIndexedField(kField, sortDirection)
    }

    fun <T : Any, T2 : Any> createArr(first: KProperty1<T, Array<T2>>,
                                      clazz: KClass<T>,
                                      second: KProperty1<T2, Any>,
                                      clazz2: KClass<T2>,
                                      sortDirection: IndexSortTypes): IndexedField<T> {
        val kField = NestedArrayKField(first, clazz, second, clazz2)
        return TextIndexedField(kField)
    }

    fun <T : Any, T2Nullable : Any?, T2Same : Any> createNullable(first: KProperty1<T, T2Nullable>,
                                                                  clazz: KClass<T>,
                                                                  second: KProperty1<T2Same, Any?>,
                                                                  clazz2: KClass<T2Same>,
                                                                  sortDirection: IndexSortTypes): IndexedField<T> {
        val kField = NestedNullableKField(first, clazz, second, clazz2)
        return StandardIndexedField(kField, sortDirection)
    }

    /**
     * Create text index on a second level nested field.
     */
    fun <T : Any, T2 : Any> createText(first: KProperty1<T, Array<T2>>,
                                       clazz: KClass<T>,
                                       second: KProperty1<T2, Any>,
                                       clazz2: KClass<T2>): IndexedField<T> {
        val kField = NestedArrayKField(first, clazz, second, clazz2)
        return TextIndexedField(kField)
    }

    /**
     * Create text index for an array property
     */
    fun <T : Any, T2 : Any> createText(first: KProperty1<T, Array<T2>>,
                                       clazz: KClass<T>): IndexedField<T> {
        val kField = SingleKField(first, clazz)
        return TextIndexedField(kField)
    }

    /**
     * Create text index for an array property
     */
    fun <T : Any> createSingleText(first: KProperty1<T, Any>,
                                   clazz: KClass<T>): IndexedField<T> {
        val kField = SingleKField(first, clazz)
        return TextIndexedField(kField)
    }
}