package bz.stewart.bracken.web.service

import bz.stewart.bracken.shared.web.AppServices
import spark.Request
import spark.Response

class WebContextBuilder {
    fun build(request: Request, response: Response, service: AppServices): WebPageContext {
        return WebPageContext(request.pathInfo(), service)
    }
}