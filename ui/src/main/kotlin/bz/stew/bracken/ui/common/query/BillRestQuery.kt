package bz.stew.bracken.ui.common.query

/**
 * Created by stew on 6/28/17.
 */
class BillRestQuery(
      val endpoint: String = "http://localhost:8080/api/v1/bills",
      val congress: Int?,
      val orderBy: OrderBy = OrderBy.DESCENDING_DATE,
      val limit: Int = 200,
      val offset: Int = 0) {
    override fun toString(): String {
        val congress: String = congress?.toString() ?: ""
        return "$endpoint?congress=$congress&order_by=$orderBy&limit=$limit&offset=$offset"
    }

    fun nextPage(): BillRestQuery {
        val nextOffset = limit + offset
        return BillRestQuery(endpoint = endpoint,
              congress = congress,
              orderBy = orderBy,
              limit = limit,
              offset = nextOffset)
    }
}