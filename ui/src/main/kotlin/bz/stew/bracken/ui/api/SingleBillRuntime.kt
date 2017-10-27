package bz.stew.bracken.ui.api

import bz.stew.bracken.ui.controller.bill.BillController
import bz.stew.bracken.ui.controller.bill.query.BillRestQuery
import bz.stew.bracken.ui.model.BillModelEasyPoliticsRest
import bz.stew.bracken.ui.view.html.Identifier
import bz.stew.bracken.view.HtmlSelector

class SingleBillRuntime(val billId:String) :RuntimeUi{
    override fun execute() {
        val rootElement = HtmlSelector(Identifier.ID,"bills")
        val controller = BillController(rootElement, BillModelEasyPoliticsRest())
        controller.view.setLoading(true)

        controller.downloadBillsLoadData(
                BillRestQuery(congress = 115, limit = 50),
                //"http://localhost:8080/api/v1/bills?congress=115&order_by=-current_status_date&limit=200", //use BillModelEasyPoliticsRest
                //"https://www.govtrack.us/api/v2/bill?congress=115&order_by=-current_status_date&limit=2",
                {
                    controller.startupSetupUi()
                    controller.view.setLoading(false)
                    println("done loading")
                })
    }
}