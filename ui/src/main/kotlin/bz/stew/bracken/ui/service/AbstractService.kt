package bz.stew.bracken.ui.service

import bz.stew.bracken.ui.util.JsonUtil

abstract class AbstractService<T> : Service<T> {
    override fun sendRequest(requestUrl: ServiceEndpoint, parser: Parser<T>, onDownload: (ServiceResponse<T>) -> Unit) {
        ServerRequestDispatcher().sendRequest(
                requestUrl.getUrl(),
                object : RequestCallback() {
                    override fun onLoad(response: String) {
                        val parsed = try {
                            val json = JsonUtil.parse(response)
                            parser.parse(json)
                        } catch (e: Exception) {
                            null
                        }
                        onDownload(ServiceResponse(parsed, response))
                    }
                }
        )
    }


}