package bz.stew.bracken.ui.common.service

import bz.stew.bracken.ui.common.bill.BillData
import bz.stew.bracken.ui.common.bill.EasyPoliticsParser
import bz.stew.bracken.ui.common.query.BillDataServiceEndpoint
import bz.stew.bracken.ui.service.AbstractService
import bz.stew.bracken.ui.service.ServiceResponse

class BillRestService : AbstractService<BillData>() {
    fun sendBillRequest(requestUrl: BillDataServiceEndpoint, onDownload: (ServiceResponse<BillData>) -> Unit) {
        sendRequest(requestUrl, EasyPoliticsParser(), {
            onDownload.invoke(it)
        })
    }
}