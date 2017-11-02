package bz.stew.bracken.ui.common.query

import bz.stew.bracken.ui.service.ServiceEndpoint

abstract class BillDataServiceEndpoint(private val baseUrl: BillServiceEndpointTypes) : ServiceEndpoint {

    abstract fun getSearchParameters(): String

    final override fun getUrl(): String {
        return "${this.baseUrl}${getSearchParameters()}"
    }
}