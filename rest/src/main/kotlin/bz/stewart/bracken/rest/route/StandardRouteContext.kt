package bz.stewart.bracken.rest.route

import bz.stewart.bracken.rest.data.bills.BillDAO
import com.fasterxml.jackson.databind.ObjectMapper
import mu.KLogger
import spark.Request
import spark.Response

class StandardRouteContext(override val request: Request,
                           override val response: Response,
                           override val objectMapper: ObjectMapper,
                           override val billDAO: BillDAO,
                           override val logger: KLogger) : RouteContext