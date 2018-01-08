package bz.stew.bracken.ui.common.query

import bz.stew.bracken.ui.context.PageContext

/**
 * Created by stew on 6/28/17.
 */
class BillRestQueryUrl(
    pageContext: PageContext,
    val congress: Int?,
    val orderBy: OrderBy = OrderBy.DESCENDING_DATE,
    val limit: Int = 200,
    val offset: Int = 0) : BillDataServiceEndpoint(pageContext, BillServiceEndpointTypes.MULTI_BILL) {

    override fun getSearchParameters(): String {
        val congress: String = congress?.toString() ?: ""
        return "?congress=$congress&order_by=$orderBy&limit=$limit&offset=$offset"
    }

    override fun toString(): String {
        return getUrl()
    }

    fun nextPage(): BillRestQueryUrl {
        val nextOffset = limit + offset
        return BillRestQueryUrl(
            pageContext = this.context,
            congress = congress,
            orderBy = orderBy,
            limit = limit,
            offset = nextOffset)
    }
}