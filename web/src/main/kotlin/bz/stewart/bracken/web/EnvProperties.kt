package bz.stewart.bracken.web

import bz.stewart.bracken.shared.util.JavaProperty

enum class EnvProperties constructor(override val propName: String,
                                                   override val defaultValue: String,
                                                   override val required: Boolean = false) : JavaProperty {
    PORT("bz.stewart.bracken.web.port", "4567", true),
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
