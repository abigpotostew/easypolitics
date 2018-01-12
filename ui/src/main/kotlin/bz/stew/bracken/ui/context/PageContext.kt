package bz.stew.bracken.ui.context

import bz.stew.bracken.ui.util.log.LogLevel
import bz.stew.bracken.ui.util.log.Logger
import bz.stew.bracken.ui.util.log.PrintSaveLogger
import bz.stewart.bracken.shared.service.ServiceContext
import bz.stewart.bracken.shared.web.AppServices

interface PageContext : ServiceContext {
    val log: Logger
    val restServiceUrl: String
}

class ClientPageContext(restServiceUrl: String, service: AppServices, windowPath: String) : PageContext {
    override val restServiceUrl = restServiceUrl
    override val service: AppServices = service
    override val windowPath: String = windowPath
    override val log: Logger = PrintSaveLogger(LogLevel.INFO)
}