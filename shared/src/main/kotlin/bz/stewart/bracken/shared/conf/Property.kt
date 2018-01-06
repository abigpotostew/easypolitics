package bz.stewart.bracken.shared.conf

interface Property {
    val propName: String
    val defaultValue: String?
    val required: Boolean
}