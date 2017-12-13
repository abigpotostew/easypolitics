package bz.stewart.bracken.rest.service

import bz.stewart.bracken.rest.data.bills.BillExample
import bz.stewart.bracken.rest.query.BillQueryBuilder
import bz.stewart.bracken.rest.query.QueryResult
import bz.stewart.bracken.rest.query.emptyQueryResult
import bz.stewart.bracken.rest.route.RouteService
import bz.stewart.bracken.rest.route.RouteContext
import bz.stewart.bracken.rest.qp
import bz.stewart.bracken.shared.data.BadStateException
import bz.stewart.bracken.shared.data.BillType
import bz.stewart.bracken.shared.data.TypeHelperDefaults

class MultipleBillService : RouteService<QueryResult> {
    override fun execute(context: RouteContext): QueryResult {
        val req = context.request
        val response = context.response
        response.type("application/json")
        val number = req.qp("number")
        val billId = req.qp("bill_id")
        val billType = try {
            TypeHelperDefaults.defaultBillTypeMatcher(req.qp("bill_type") ?: "")
        } catch (e: BadStateException) {
            BillType.NONE
        }
        val congress = req.qp("congress")?.toInt() ?: -1
        val orderBy = req.qp("order_by") ?: "-current_status_date"
        val limit = req.qp("limit")?.toInt() ?: 100
        val offset = req.qp("offset")?.toInt() ?: 0

        val queryExample = BillExample(
                billNumber = number,
                bill_id = billId,
                bill_type = billType,
                congressNum = congress
        )
        val result = try {
            BillQueryBuilder(context.billDAO.mainDbInst, queryExample, orderBy,
                    limit, offset).find()
        } catch (e: Exception) {
            emptyQueryResult()
        }
        return result
    }

    override fun onError(): Exception {
        return Exception("Error searching for multiple bills")
    }
}