package bz.stewart.bracken.rest

import bz.stewart.bracken.shared.util.Property

enum class EnvProperties constructor(override val propName: String,
                                                   override val defaultValue: String,
                                                   override val required: Boolean = false) : Property {
    PORT("bz.stewart.bracken.rest.port", "8080", true),
    DB_NAME("bz.stewart.bracken.rest.database.name", "", true),
    ;

    override fun getOrDefault(): String {
        return getPropValue() ?: this.defaultValue
    }

    override fun getPropValue(): String? {
        return System.getProperty(this.propName)
    }

    override fun isEmpty(): Boolean {
        return getPropValue() == null
    }
}
