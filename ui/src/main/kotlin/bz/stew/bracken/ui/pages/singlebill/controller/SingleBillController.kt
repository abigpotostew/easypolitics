package bz.stew.bracken.ui.pages.singlebill.controller

import bz.stew.bracken.ui.common.bill.BillData
import bz.stew.bracken.ui.common.controller.PageController
import bz.stew.bracken.ui.common.query.SingleBillRestQueryUrl
import bz.stew.bracken.ui.common.service.BillRestService
import bz.stew.bracken.ui.pages.singlebill.model.SingleBillModel
import bz.stew.bracken.ui.pages.singlebill.view.SingleBillView
import bz.stew.bracken.view.HtmlSelector

class SingleBillController(rootElmt: HtmlSelector,
                           private val billId: String) : PageController<SingleBillView, BillData>(SingleBillView(
        rootElmt), SingleBillModel()) {

    private val requestService = BillRestService()

    private fun inflateUi(){

    }

    override fun init(callback: () -> Unit) {
        val url = SingleBillRestQueryUrl(billId = this.billId)
        this.view.setLoading(true)
        this.requestService.sendBillRequest(url, {
            if (it.response == null) {
                console.error("error downloading bill with id: ${this.billId}")
                error("error")
            }
            this.model.loadBillData(it.response, false)
            inflateUi()
            this.view.setLoading(false)

            callback.invoke()
        })
    }
}