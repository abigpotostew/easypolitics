package bz.stew.bracken.ui.controller.bill.query

/**
 * Created by stew on 6/28/17.
 */
enum class OrderBy(val orderStr:String) {
   ASCENDING_DATE("+current_status_date"), // newest first
   DESCENDING_DATE("-current_status_date"); //oldest first

   override fun toString(): String {
      return orderStr
   }
}