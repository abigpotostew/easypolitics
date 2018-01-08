package bz.stew.bracken.ui.context

import bz.stewart.bracken.shared.web.AppServices

interface PageContext {
    val restServiceUrl:String
    val service:AppServices
    val windowPath:String
}

class MainPageContext(restServiceUrl:String, service: AppServices, windowPath:String): PageContext {
    override val restServiceUrl = restServiceUrl
    override val service: AppServices = service
    override val windowPath: String = windowPath
}