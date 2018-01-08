package bz.stew.bracken.ui.common.query

import bz.stew.bracken.ui.context.PageContext
import bz.stew.bracken.ui.service.ServiceEndpoint

abstract class BillDataServiceEndpoint(override val context:PageContext, private val baseUrl: BillServiceEndpointTypes) : ServiceEndpoint {

    abstract fun getSearchParameters(): String

    final override fun getUrl(): String {
        val url = this.context.restServiceUrl//this.baseUrl.devUrl
        val finalUrl = "$url${baseUrl.path}${getSearchParameters()}"
        console.log("url: $url")
        console.log("service path: ${baseUrl.path}")
        console.log("search parms: ${getSearchParameters()}")
        return finalUrl
    }
}