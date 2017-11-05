package bz.stew.bracken.ui

import bz.stew.bracken.ui.api.BrowseRuntime
import bz.stew.bracken.ui.api.SingleBillRuntime
import bz.stew.bracken.ui.util.RouteMatch
import bz.stew.bracken.ui.util.RouteMatch.pathVariableMap
import bz.stewart.bracken.shared.web.AppServices
import kotlin.browser.window

/**
 * Created by stew on 1/23/17.
 */
fun main(args: Array<String>) {
    console.log("Hello from easypolitics ui")
    try {
        val windowPath = window.location.pathname
        val service = RouteMatch.matchRoute(windowPath)
        if (service != null) {
            executeService(windowPath, service)
        }

    } catch (e: Exception) {
        console.log(e)
    }
}

private fun executeService(pathname: String, service: AppServices) {
    val pathVariables = pathVariableMap(pathname, service)
    when (service) {
        AppServices.MAIN -> console.log("Main")
        AppServices.RESPOND -> console.log("Respond: " + pathVariables["id"])
        AppServices.SINGLE_BILL -> SingleBillRuntime(pathVariables["id"]!!).execute()
        AppServices.BROWSE_BILL -> BrowseRuntime().execute()
    }
}