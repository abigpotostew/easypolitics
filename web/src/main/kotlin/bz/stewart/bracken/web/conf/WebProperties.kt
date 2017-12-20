package bz.stewart.bracken.web.conf

import bz.stewart.bracken.shared.util.Property
import java.util.Properties

enum class WebProperties constructor(override val propName: String,
                                     override val defaultValue: String,
                                     override val required: Boolean = false) : Property {
    PORT("bz.stewart.bracken.web.port", "4567", true),
    // Internal files from the classpath
    INT_STATIC_FILES("bz.stewart.bracken.web.staticfiles", "static", false),
    // Optional path to external files outside of classpath, overrides the internal static file path
    EXT_STATIC_FILES("bz.stewart.bracken.web.externalstaticfiles", "", false),
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

fun asDefault(values:Array<WebProperties>): Properties {
    val props=Properties()
    for (value in values) {
        props.setProperty(value.propName, value.defaultValue)
    }
    return props
}