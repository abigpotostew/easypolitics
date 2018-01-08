package bz.stew.bracken.ui.pages.singlebill.controller

import bz.stew.bracken.ui.common.bill.BillData
import bz.stew.bracken.ui.common.controller.PageController
import bz.stew.bracken.ui.common.query.SingleBillRestQueryUrl
import bz.stew.bracken.ui.common.service.BillRestService
import bz.stew.bracken.ui.common.view.BillViewItem
import bz.stew.bracken.ui.context.PageContext
import bz.stew.bracken.ui.pages.singlebill.model.SingleBillModel
import bz.stew.bracken.ui.pages.singlebill.view.SingleBillView
import bz.stew.bracken.view.HtmlSelector

class SingleBillController(rootElmt: HtmlSelector,
                           private val billId: String,
                           pageContext: PageContext)
    : PageController<SingleBillView, BillData>(SingleBillView(rootElmt), SingleBillModel(), pageContext) {

    private val requestService = BillRestService()

    private fun inflateUi() {
        //fill in data for a template, pass that into this.view
        this.view.constructBillView(BillViewItem(this.model.getBillData()[0]))
    }

    override fun init(callback: () -> Unit) {
        val url = SingleBillRestQueryUrl(this.context, billId = this.billId)
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