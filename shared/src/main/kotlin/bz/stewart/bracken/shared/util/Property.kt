package bz.stewart.bracken.shared.util

interface Property {
    val propName: String
    val defaultValue: String
    val required: Boolean

    fun getOrDefault(): String

    fun getPropValue(): String?

    fun isEmpty(): Boolean
}