package bz.stewart.bracken.shared.web

/**
 * URL routes for the webapp shared by ui and server.
 * This does not support wildcard paths at the moment
 */
enum class AppServices(val path: String) {
    MAIN("/"),
    RESPOND("/respond/:id"),
    SINGLE_BILL("/bill/:id"),
    BROWSE_BILL("/browse"),
    ;
}