package bz.stew.bracken.ui.common.query

enum class BillServiceEndpointTypes(val devUrl:String, val prodUrl:String="") {
    MULTI_BILL("http://localhost:8080/api/v1/bills"),
    SINGLE_BILL("http://localhost:8080/api/v1/bill"),
    ;
}