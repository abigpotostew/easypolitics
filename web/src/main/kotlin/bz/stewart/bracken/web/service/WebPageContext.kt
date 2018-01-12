package bz.stewart.bracken.web.service

import bz.stewart.bracken.shared.service.ServiceContext
import bz.stewart.bracken.shared.web.AppServices

class WebPageContext(routePath:String, service: AppServices): ServiceContext {

    override val service: AppServices = service
    override val windowPath: String = routePath
}