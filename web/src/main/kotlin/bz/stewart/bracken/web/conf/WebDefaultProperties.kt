package bz.stewart.bracken.web.conf

import bz.stewart.bracken.shared.conf.Property

enum class WebDefaultProperties constructor(override val propName: String,
                                            override val defaultValue: String?,
                                            override val required: Boolean = false) : Property {
    PORT("bz.stewart.bracken.web.port", "4567", true),
    // Internal files from the classpath
    INT_STATIC_FILES("bz.stewart.bracken.web.staticfiles", "static", false),
    // Optional path to external files outside of classpath, overrides the internal static file path
    EXT_STATIC_FILES("bz.stewart.bracken.web.externalstaticfiles", null, false),
    ;
}

