package bz.stew.bracken.ui.common.bill

import bz.stew.bracken.ui.extension.html.jsParseDate
import kotlin.js.Date

/**
 * Created by stew on 5/4/17.
 */
class EasyPoliticsMajorAction(private val descript: String, val actionType: String, _dateTime: String) : MajorAction {

   private val dateTime: Date = jsParseDate(_dateTime)
   override fun id(): Int {
      return 0
   }

   override fun description(): String {
      return descript
   }

   override fun raw(): String {
      TODO()
   }

   override fun date(): Date {
      return dateTime
   }
}