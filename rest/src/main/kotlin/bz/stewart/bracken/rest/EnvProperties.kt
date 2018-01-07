package bz.stewart.bracken.rest

import bz.stewart.bracken.shared.conf.EnvProperty

enum class EnvProperties constructor(override val propName: String,
                                                   override val defaultValue: String,
                                                   override val required: Boolean = false) : EnvProperty {
    PORT("bz.stewart.bracken.rest.port", "8080", true),
    DB_NAME("bz.stewart.bracken.rest.database.name", "", true),
    REST_PROPS_FILE("bz.stewart.bracken.rest.properties", "", true)
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
