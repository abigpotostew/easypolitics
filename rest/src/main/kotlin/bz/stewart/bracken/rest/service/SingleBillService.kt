package bz.stewart.bracken.rest.service

import bz.stewart.bracken.rest.qp
import bz.stewart.bracken.rest.query.QueryResult
import bz.stewart.bracken.rest.query.SingleBillQuery
import bz.stewart.bracken.rest.query.emptyQueryResult
import bz.stewart.bracken.rest.route.RouteContext
import bz.stewart.bracken.rest.route.RouteService

class SingleBillService : RouteService<QueryResult> {
    override fun execute(context: RouteContext): QueryResult {
        context.response.type("application/json")
        val billId = context.request.qp("bill_id")
        if (billId == null) {
            throw IllegalQueryInput("Required")
        }
        //val number = context.request.qp("number") //this is ignored right now...
        context.logger.info { "Single bill query request: '$billId'" }
        val result = try {
            SingleBillQuery(billId, context.billDAO.mainDbInst).find()
        } catch (e: Exception) {
            emptyQueryResult()
        }
        return result
    }

    override fun onError(): Exception {
        return Exception("Unknown error searching for single bill")
    }
}