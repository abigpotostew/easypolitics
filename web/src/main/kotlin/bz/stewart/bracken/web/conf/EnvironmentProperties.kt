package bz.stewart.bracken.web.conf

import bz.stewart.bracken.shared.util.Property

enum class EnvironmentProperties(override val propName: String,
                                 override val defaultValue: String,
                                 override val required: Boolean = false):Property {
    PROGRAM_PROPS_FILE("bz.stewart.bracken.web.properties", "", true)
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