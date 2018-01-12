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

class SingleBillController(private val rootElmt: HtmlSelector,
                           private val billId: String,
                           pageContext: PageContext)
    : PageController<SingleBillView, BillData>(SingleBillView(), SingleBillModel(), pageContext) {

    private val requestService = BillRestService()

    private fun inflateUi(showError: Boolean) {
        if (!showError) {
            this.view.constructBillView(BillViewItem(this.model.getBillData()[0]))
        } else {
            this.view.showBillNotFound(this.billId)
        }
        getElementBySelector(rootElmt).appendChild(this.view.element!!)
    }

    override fun init(callback: () -> Unit) {
        val url = SingleBillRestQueryUrl(this.context, billId = this.billId)
        this.view.setLoading(true)
        this.requestService.sendBillRequest(url, {
            if (it.isEmpty()) {
                this.context.log.error("Error downloading bill with id: ${this.billId}")
            } else {
                this.model.loadBillData(it.response, false)
            }
            inflateUi(it.isEmpty())

            this.view.setLoading(false)

            callback.invoke()
        })
    }
}