package bz.stewart.bracken.rest.spark

import bz.stewart.bracken.rest.bills.BillDAO
import bz.stewart.bracken.shared.rest.RestServices
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import mu.KLogging
import spark.Request
import spark.Spark
import spark.Spark.port

class RestServiceRunner {

    val mapper = jacksonObjectMapper()
    val billDao = BillDAO(System.getProperty("bz.stewart.bracken.db.name"))

    companion object : KLogging()

    fun run() {
        port(8080)
        CorsFilter().apply()

        Spark.path("/api/v1", {
            Spark.get(RestServices.MULTI_BILLS.path) { req, response ->
                val routeContext = StandardRouteContext(req, response, mapper, billDao, logger)
                ExecuteRoute(MultipleBillRoute()).execute(routeContext)
            }
            Spark.get(RestServices.SINGLE_BILL.path) { req, response ->
                val routeContext = StandardRouteContext(req, response, mapper, billDao, logger)
                ExecuteRoute(SingleBillRoute()).execute(routeContext)
            }
        })
    }
}

fun Request.qp(key: String): String? = this.queryParams(key)