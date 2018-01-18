package bz.stewart.bracken.db.database.index

import com.fasterxml.jackson.annotation.JsonProperty
import kotlin.reflect.KClass
import kotlin.reflect.KProperty1
import kotlin.reflect.full.findAnnotation

object JsonName{

}

fun <T : Any> KProperty1<T, *>.getJsonPropertyNameDefault(clazz:KClass<T>): String {
    //val jField = this.javaGetter?.getAnnotationsByType<JsonProperty>(JsonProperty::class.java)
    val propName = this.name
    val constructors = clazz.constructors
    val params = constructors.flatMap { it.parameters }
    val propParams = params.filter { it.name==propName }
    val propParamMaybe = propParams.filter { it.annotations.isNotEmpty() && it.findAnnotation<JsonProperty>() != null }
    val jsonAnnotation = if(propParamMaybe.isNotEmpty())  propParamMaybe.first().findAnnotation<JsonProperty>() else null
    return jsonAnnotation?.value ?: this.name
}