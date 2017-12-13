package bz.stewart.bracken.rest.route

import bz.stewart.bracken.rest.data.bills.BillDAO
import com.fasterxml.jackson.databind.ObjectMapper
import mu.KLogger
import spark.Request
import spark.Response

interface RouteContext {
    val objectMapper: ObjectMapper
    val billDAO: BillDAO
    val request: Request
    val response: Response
    val logger:KLogger
}