package bz.stew.bracken.ui.util

import bz.stewart.bracken.shared.web.AppServices

object RouteMatch {
    /**
     * Assumes the path already matches the service
     */
    fun pathVariableMap(pathname: String, service: AppServices): Map<String, String> {
        val out = mutableMapOf<String, String>()
        val urlList = convertPathToList(pathname)
        val serviceList = convertPathToList(service.absoluteUrlPath)
        for (i in (0 until urlList.size)) {
            val urlItem = urlList[i]
            val serviceItem = serviceList[i]
            if (serviceItem.subSequence(0, 1) == ":") {
                out.put(serviceItem.subSequence(1, serviceItem.length).toString(), urlItem)
            }
        }
        return out
    }

    /**
     * Does NOT support wildcard at the moment
     * @param pathname should be value of window.location.pathname excluding domain and query parms
     */
    fun matchRoute(pathname: String): AppServices? {
        for (service in AppServices.values()) {
            if (matchSpecificRoute(pathname, service)) {
                return service
            }
        }
        return null
    }

    private fun matchSpecificRoute(pathname: String, service: AppServices): Boolean {
        if (pathname == service.absoluteUrlPath) {
            return true
        }
        val urlList = convertPathToList(pathname)
        val serviceList = convertPathToList(service.absoluteUrlPath)

        if (urlList.size != serviceList.size) {
            return false
        }
        for (i in (0 until urlList.size)) {
            val urlItem = urlList[i]
            val serviceItem = serviceList[i]
            if (serviceItem.subSequence(0, 1) != ":" && urlItem != serviceItem) {
                return false
            }
        }

        return true
    }

    private fun convertPathToList(path: String): List<String> {
        return path.split('/').filter { it.isNotEmpty() }
    }
}