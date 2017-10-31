package bz.stew.bracken.ui.common.service

import bz.stew.bracken.ui.common.bill.BillData
import bz.stew.bracken.ui.common.bill.EasyPoliticsParser
import bz.stew.bracken.ui.common.query.BillRestQueryUrl
import bz.stew.bracken.ui.service.AbstractService
import bz.stew.bracken.ui.service.ServiceResponse

class BillRestService : AbstractService<BillData>() {
    var lastSuccessfulQuery: BillRestQueryUrl? = null
        private set(value) {
            field = value
        }
    var inProgressQuery: BillRestQueryUrl? = null
        private set(value) {
            field = value
        }

    fun sendBillRequest(requestUrl: BillRestQueryUrl, onDownload: (ServiceResponse<BillData>) -> Unit) {
        inProgressQuery = requestUrl
        sendRequest(requestUrl, EasyPoliticsParser(), {
            lastSuccessfulQuery = inProgressQuery
            inProgressQuery = null
            onDownload.invoke(it)
        })
    }
}