package bz.stew.bracken.ui.common.query

class SingleBillRestQueryUrl(val billId: String) : BillDataServiceEndpoint(BillServiceEndpointTypes.SINGLE_BILL) {
    override fun getSearchParameters(): String {
        return "?bill_id=${this.billId}"
    }
}