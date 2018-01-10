package bz.stew.bracken.ui

import bz.stew.bracken.ui.api.BrowseRuntime
import bz.stew.bracken.ui.api.SingleBillRuntime
import bz.stew.bracken.ui.context.MainPageContext
import bz.stew.bracken.ui.context.PageContext
import bz.stew.bracken.ui.service.RequestCallback
import bz.stew.bracken.ui.service.ServerRequestDispatcher
import bz.stew.bracken.ui.util.RouteMatch
import bz.stew.bracken.ui.util.RouteMatch.pathVariableMap
import bz.stewart.bracken.shared.web.AppServices
import kotlin.browser.window

/**
 * Created by stew on 1/23/17.
 */
fun main(args: Array<String>) {
    console.log("Hello from easypolitics ui")
    ServerRequestDispatcher().sendRequest("/serviceurl", object : RequestCallback() {
        override fun onLoad(response: String) {
            routeAndExecute(response)
        }
        override fun onError() {
            console.log("Error retrieving the service url :(")
        }
    })
}

private fun routeAndExecute(restServiceUrl:String){
    try {
        val windowPath = window.location.pathname
        val service = RouteMatch.matchRoute(windowPath)
        if (service != null) {
            val pageContext = MainPageContext(restServiceUrl, service, windowPath)
            executeService(pageContext)
        }
    } catch (e: Exception) {
        console.log(e)
    }
}

private fun executeService(pageContext: PageContext) {
    val pathVariables = pathVariableMap(pageContext.windowPath, pageContext.service)
    when (pageContext.service) {
        AppServices.MAIN -> console.log("Main")
        AppServices.RESPOND -> console.log("Respond: " + pathVariables["id"])
        AppServices.SINGLE_BILL -> SingleBillRuntime(pathVariables["id"]!!).execute(pageContext)
        AppServices.BROWSE_BILL -> BrowseRuntime().execute(pageContext)
        AppServices.SERVICE_URL -> console.error("Error UI can't resolve the current page service from the URL.")
    }
}