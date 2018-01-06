package bz.stewart.bracken.web.conf

import bz.stewart.bracken.shared.conf.EnvProperty
import bz.stewart.bracken.shared.conf.Property
import java.util.Properties

enum class WebDefaultProperties constructor(override val propName: String,
                                            override val defaultValue: String?,
                                            override val required: Boolean = false) : Property {
    PORT("bz.stewart.bracken.web.port", "4567", true),
    // Internal files from the classpath
    INT_STATIC_FILES("bz.stewart.bracken.web.staticfiles", "static", false),
    // Optional path to external files outside of classpath, overrides the internal static file path
    EXT_STATIC_FILES("bz.stewart.bracken.web.externalstaticfiles", null, false),
    ;

//    override fun getOrDefault(): String? {
//        return getPropValue() ?: this.defaultValue
//    }
//
//    override fun getPropValue(): String? {
//        return System.getProperty(this.propName)
//    }
//
//    override fun isEmpty(): Boolean {
//        return getPropValue() == null
//    }
}

fun asProperties(values: Array<WebDefaultProperties>): Properties {
    val props = Properties()
    for (value in values) {
        props.setProperty(value.propName, value.defaultValue)
    }
    return props
}

fun validateRequiredProperties(validateProps: Properties, propDefintions: Array<WebDefaultProperties>) {
    for (propDef in propDefintions) {
        if (validateProps.getProperty(propDef.propName) == null) {
            throw Exception("Missing required main property '${propDef.name}'")
        }
    }
}