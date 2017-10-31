package bz.stew.bracken.ui.common.query

import bz.stew.bracken.ui.service.ServiceEndpoint

/**
 * Created by stew on 6/28/17.
 */
class BillRestQueryUrl(
      val endpoint: String = "http://localhost:8080/api/v1/bills",
      val congress: Int?,
      val orderBy: OrderBy = OrderBy.DESCENDING_DATE,
      val limit: Int = 200,
      val offset: Int = 0) : ServiceEndpoint {
    override fun getUrl(): String {
        val congress: String = congress?.toString() ?: ""
        return "$endpoint?congress=$congress&order_by=$orderBy&limit=$limit&offset=$offset"
    }

    override fun toString(): String {
        return getUrl()
    }

    fun nextPage(): BillRestQueryUrl {
        val nextOffset = limit + offset
        return BillRestQueryUrl(endpoint = endpoint,
              congress = congress,
              orderBy = orderBy,
              limit = limit,
              offset = nextOffset)
    }
}