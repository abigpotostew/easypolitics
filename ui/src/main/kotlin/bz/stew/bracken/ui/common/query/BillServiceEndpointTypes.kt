package bz.stew.bracken.ui.common.query

enum class BillServiceEndpointTypes(val path: String, val devUrl: String, val prodUrl: String = "") {
    MULTI_BILL("/api/v1/bills",
        "http://localhost:8080/api/v1/bills",
        "http://easypoliticsrest.bracken.bz/api/v1/bills"),
    SINGLE_BILL("/api/v1/bill",
        "http://localhost:8080/api/v1/bill",
        "http://easypoliticsrest.bracken.bz/api/v1/bill"),
    ;
}