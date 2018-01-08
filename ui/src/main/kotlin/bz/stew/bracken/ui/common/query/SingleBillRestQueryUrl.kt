package bz.stew.bracken.ui.common.query

import bz.stew.bracken.ui.context.PageContext

class SingleBillRestQueryUrl(context:PageContext,val billId: String) : BillDataServiceEndpoint(context, BillServiceEndpointTypes.SINGLE_BILL) {
    override fun getSearchParameters(): String {
        return "?bill_id=${this.billId}"
    }
}