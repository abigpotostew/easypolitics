package bz.stewart.bracken.rest

import bz.stewart.bracken.rest.conf.RuntimeContext
import bz.stewart.bracken.rest.data.bills.BillDAO
import bz.stewart.bracken.rest.http.CorsFilter
import bz.stewart.bracken.rest.route.ExecuteRoute
import bz.stewart.bracken.rest.route.RouteContextBuilder
import bz.stewart.bracken.rest.route.StandardRouteContext
import bz.stewart.bracken.rest.service.HomeService
import bz.stewart.bracken.rest.service.MultipleBillService
import bz.stewart.bracken.rest.service.SingleBillService
import bz.stewart.bracken.shared.rest.RestServices
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import mu.KLogging
import spark.Request
import spark.Spark
import spark.Spark.port

class RestServiceRunner(context: RuntimeContext) {

    companion object : KLogging()

    private val runtimeContext = context
    private val mapper = jacksonObjectMapper()
    private val billDao: BillDAO = BillDAO(context.client)

    fun run() {
        port(this.runtimeContext.servingPort)
        CorsFilter().apply()

        logger.info { "Starting Easypolitics Rest, serving on port ${this.runtimeContext.servingPort}" }

        val routeContextCreator = RouteContextBuilder(this.mapper,this.billDao,logger)

        Spark.path("/api/v1", {
            Spark.get(RestServices.HOME.path) {req, response ->
                val routeContext = routeContextCreator.build(req, response)
                ExecuteRoute(HomeService()).execute(routeContext)
            }
            Spark.get(RestServices.MULTI_BILLS.path) { req, response ->
                val routeContext = routeContextCreator.build(req, response)
                ExecuteRoute(MultipleBillService()).execute(routeContext)
            }
            Spark.get(RestServices.SINGLE_BILL.path) { req, response ->
                val routeContext = routeContextCreator.build(req, response)
                ExecuteRoute(SingleBillService()).execute(routeContext)
            }
        })
        Spark.get(RestServices.HOME.path) {req, response ->
            val routeContext = routeContextCreator.build(req, response)
            ExecuteRoute(HomeService()).execute(routeContext)
        }
    }
}

fun Request.qp(key: String): String? = this.queryParams(key)