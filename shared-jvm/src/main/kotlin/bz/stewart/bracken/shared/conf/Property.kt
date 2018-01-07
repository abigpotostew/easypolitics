package bz.stewart.bracken.shared.conf

import java.util.Properties

interface Property {
    /**
     * The key name used to set this property
     */
    val propName: String
    val defaultValue: String?
    val required: Boolean
}

fun asProperties(values: List<Property>): Properties {
    val props = Properties()
    for (value in values) {
        props.setProperty(value.propName, value.defaultValue)
    }
    return props
}