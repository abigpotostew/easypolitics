package bz.stewart.bracken.shared.conf

interface EnvProperty : Property {

    fun getOrDefault(): String?

    fun getPropValue(): String?

    fun isEmpty(): Boolean
}