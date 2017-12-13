package bz.stewart.bracken.rest

import bz.stewart.bracken.rest.data.bills.BillDAO
import bz.stewart.bracken.rest.http.CorsFilter
import bz.stewart.bracken.rest.route.ExecuteRoute
import bz.stewart.bracken.rest.route.StandardRouteContext
import bz.stewart.bracken.rest.service.MultipleBillService
import bz.stewart.bracken.rest.service.SingleBillService
import bz.stewart.bracken.shared.rest.RestServices
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import mu.KLogging
import spark.Request
import spark.Spark
import spark.Spark.port

class RestServiceRunner {

    val mapper = jacksonObjectMapper()
    val billDao = BillDAO(EnvProperties.DB_NAME.getOrDefault())

    companion object : KLogging()

    fun run() {
        port(EnvProperties.PORT.getOrDefault().toInt())
        CorsFilter().apply()

        Spark.path("/api/v1", {
            Spark.get(RestServices.MULTI_BILLS.path) { req, response ->
                val routeContext = StandardRouteContext(req,
                        response,
                        mapper,
                        billDao,
                        logger)
                ExecuteRoute(MultipleBillService()).execute(routeContext)
            }
            Spark.get(RestServices.SINGLE_BILL.path) { req, response ->
                val routeContext = StandardRouteContext(req,
                        response,
                        mapper,
                        billDao,
                        logger)
                ExecuteRoute(SingleBillService()).execute(routeContext)
            }
        })
    }
}

fun Request.qp(key: String): String? = this.queryParams(key)