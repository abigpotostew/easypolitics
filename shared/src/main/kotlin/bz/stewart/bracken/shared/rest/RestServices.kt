package bz.stewart.bracken.shared.rest

/**
 * URL routes for the rest server.
 * This does not support wildcard paths at the moment
 */
enum class RestServices(val path: String) {
    MULTI_BILLS("/bills"),
    SINGLE_BILL("/bill"),
    ;
}