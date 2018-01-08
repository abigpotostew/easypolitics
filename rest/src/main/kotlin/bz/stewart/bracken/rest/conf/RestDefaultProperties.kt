package bz.stewart.bracken.rest.conf

import bz.stewart.bracken.shared.conf.Property

enum class RestDefaultProperties constructor(override val propName: String,
                                             override val defaultValue: String?,
                                             override val required: Boolean = false) : Property {
    EXT_PORT("bz.stewart.bracken.rest.port", "8080", true),

    DB_NAME("bz.stewart.bracken.rest.dbname", "--database", true),

    DB_HOSTNAME("bz.stewart.bracken.rest.dbhostname", "", false),
    DB_PORT("bz.stewart.bracken.rest.dbport", "", false),
    DB_USERNAME("bz.stewart.bracken.rest.dbuser", "", false),
    DB_PASSWORD("bz.stewart.bracken.rest.dbpass", "", false)
    ;
}