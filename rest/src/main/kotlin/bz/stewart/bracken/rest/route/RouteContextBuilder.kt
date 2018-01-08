package bz.stewart.bracken.rest.route

import bz.stewart.bracken.rest.data.bills.BillDAO
import com.fasterxml.jackson.databind.ObjectMapper
import mu.KLogger
import spark.Request
import spark.Response

class RouteContextBuilder(private val objectMapper: ObjectMapper,
                          private val billDAO: BillDAO,
                          private val logger: KLogger) {
    fun build(request: Request, response: Response): RouteContext {
        return StandardRouteContext(request, response, this.objectMapper, this.billDAO, this.logger)
    }
}