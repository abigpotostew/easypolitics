package bz.stewart.bracken.db.database.index

import kotlin.reflect.KClass
import kotlin.reflect.KProperty1


interface KField<T> {
    val name: String
}

class SingleKField<T : Any>(field: KProperty1<T, Any>, clazz: KClass<T>) : KField<T> {
    override val name: String = field.getJsonPropertyNameDefault(clazz)
}

class NestedKField<T : Any, T2 : Any>(val first: KProperty1<T, Array<T2>>,
                                      val clazz: KClass<T>,
                                      val second: KProperty1<T2, Any>,
                                      val clazz2: KClass<T2>) : KField<T> {
    override val name: String = getNestedName()

    private fun getNestedName(): String {
        return first.getJsonPropertyNameDefault(clazz) + "." + second.getJsonPropertyNameDefault(clazz2)
    }
}