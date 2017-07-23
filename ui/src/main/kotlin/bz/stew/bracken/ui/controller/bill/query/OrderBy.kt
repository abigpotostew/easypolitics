package bz.stew.bracken.ui.controller.bill.query

/**
 * Created by stew on 6/28/17.
 */
enum class OrderBy(val orderStr: String) {
   ASCENDING_DATE("current_status_date_asc"), // oldest first
   DESCENDING_DATE("current_status_date_des"); // newest first

   override fun toString(): String {
      return orderStr
   }
}