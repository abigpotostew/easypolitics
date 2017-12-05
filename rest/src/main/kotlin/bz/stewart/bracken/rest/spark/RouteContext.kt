package bz.stewart.bracken.rest.spark

import bz.stewart.bracken.rest.bills.BillDAO
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