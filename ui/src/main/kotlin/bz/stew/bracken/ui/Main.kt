package bz.stew.bracken.ui

import bz.stew.bracken.ui.controller.bill.BillController
import bz.stew.bracken.ui.controller.bill.query.BillRestQuery
import bz.stew.bracken.ui.model.BillModelEasyPoliticsRest
import bz.stew.bracken.ui.view.html.Identifier
import bz.stew.bracken.view.HtmlSelector

/**
 * Created by stew on 1/23/17.
 */
//@native("$") val jquery : dynamic = noImpl

@JsName("EasypoliticsUiApiBrowse")
external fun apiDoBrowse(): Boolean = definedExternally

@JsName("EasypoliticsUiApiSingleBill")
external fun apiDoSingleBillView(): String? = definedExternally

fun main(args: Array<String>) {

    try {
        if (apiDoBrowse()) {
            executeBrowse()
        } else if (apiDoSingleBillView() != null) {
            executeSingleBill(apiDoSingleBillView()!!)
        }
    } catch (e: Exception) {
        console.log(e)
    }

}

fun executeBrowse() {
    val rootElement = HtmlSelector(Identifier.ID, "bills")
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

fun executeSingleBill(billId:String){
    val rootElement = HtmlSelector(Identifier.ID,"root")
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
