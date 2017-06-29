package bz.stew.bracken.ui.controller.bill.query

/**
 * Created by stew on 6/28/17.
 */
class BillRestQuery(val endpoint:String = "http://localhost:8080/api/v1/bills", val congress:Int?, val orderBy: OrderBy = OrderBy.ASCENDING_DATE, val limit :Int = 200) {
   override fun toString(): String {
      val congress:String = congress?.toString() ?: ""
      return "$endpoint?congress=$congress&order_by=$orderBy&limit=$limit"
   }
}