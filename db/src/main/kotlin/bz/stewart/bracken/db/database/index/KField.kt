package bz.stewart.bracken.db.database.index

import com.fasterxml.jackson.annotation.JsonProperty
import kotlin.reflect.KClass
import kotlin.reflect.KProperty1
import kotlin.reflect.full.findAnnotation

/**
 * These help connect an actual kotlin property to an index using reflection.
 */
interface KField<T> {
    val name: String
}

class SingleKField<T : Any>(field: KProperty1<T, Any>, clazz: KClass<T>) : KField<T> {
    override val name: String = field.getJsonPropertyNameDefault(clazz)
}

class NestedKField<T : Any, T2 : Any>(val first: KProperty1<T, T2>,
                                      val clazz: KClass<T>,
                                      val second: KProperty1<T2, Any>,
                                      val clazz2: KClass<T2>) : KField<T> {
    override val name: String = getNestedName()

    private fun getNestedName(): String {
        return first.getJsonPropertyNameDefault(clazz) + "." + second.getJsonPropertyNameDefault(clazz2)
    }
}

/**
 * For a nested index where both returns are nullable prop defined like A.getB():B? and B.getC():C?
 */
class NestedNullableKField<T : Any, RNullable : Any?, RSame : Any>(val first: KProperty1<T, RNullable>,
                                      val clazz: KClass<T>,
                                      val second: KProperty1<RSame, Any?>,
                                      val clazz2: KClass<RSame>) : KField<T> {
    override val name: String = getNestedName()

    private fun getNestedName(): String {
        return first.getJsonPropertyNameDefault(clazz) + "." + second.getJsonPropertyNameDefaultNullable(clazz2)
    }
}

class NestedArrayKField<T : Any, T2 : Any>(val first: KProperty1<T, Array<T2>>,
                                           val clazz: KClass<T>,
                                           val second: KProperty1<T2, Any>,
                                           val clazz2: KClass<T2>) : KField<T> {
    override val name: String = getNestedName()

    private fun getNestedName(): String {
        return first.getJsonPropertyNameDefault(clazz) + "." + second.getJsonPropertyNameDefault(clazz2)
    }
}

/**
 * Get the property name or the name on the associated [JsonProperty] annotation on the constructor parameter
 * corresponding to the kotlin property if it exists. This might not work if the annotation is defined on something other
 * than the constructor parameter.
 */
fun <T : Any> KProperty1<T, *>.getJsonPropertyNameDefault(clazz: KClass<T>): String {
    val propName = this.name
    val constructors = clazz.constructors
    val params = constructors.flatMap { it.parameters }
    val propParams = params.filter { it.name == propName }
    val propParamMaybe = propParams.filter { it.annotations.isNotEmpty() && it.findAnnotation<JsonProperty>() != null }
    val jsonAnnotation = if (propParamMaybe.isNotEmpty()) propParamMaybe.first().findAnnotation<JsonProperty>() else null
    return jsonAnnotation?.value ?: this.name
}

/**
 * Name as [getJsonPropertyNameDefault()] But this allows nullable properties. make Sure T and T2 are actually the same
 * class but T2 is the nullable one.
 */
fun <T : Any, T2:Any?> KProperty1<T2, *>.getJsonPropertyNameDefaultNullable(clazz: KClass<T>): String {
    val propName = this.name
    val constructors = clazz.constructors
    val params = constructors.flatMap { it.parameters }
    val propParams = params.filter { it.name == propName }
    val propParamMaybe = propParams.filter { it.annotations.isNotEmpty() && it.findAnnotation<JsonProperty>() != null }
    val jsonAnnotation = if (propParamMaybe.isNotEmpty()) propParamMaybe.first().findAnnotation<JsonProperty>() else null
    return jsonAnnotation?.value ?: this.name
}