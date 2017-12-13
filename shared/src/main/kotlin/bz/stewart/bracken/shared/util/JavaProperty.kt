package bz.stewart.bracken.shared.util

interface JavaProperty {
    val propName: String
    val defaultValue: String
    val required: Boolean

    fun getOrDefault(): String

    fun getPropValue(): String?

    fun isEmpty(): Boolean
}