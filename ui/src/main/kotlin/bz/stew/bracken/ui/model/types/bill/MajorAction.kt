package bz.stew.bracken.ui.model.types.bill

import bz.stew.bracken.ui.extension.html.jsDate
import kotlin.js.Date

/**
 * Created by stew on 2/13/17.
 */
interface MajorAction {
   fun id(): Int;
   fun description(): String;
   fun raw(): String;
   fun date(): Date;
}

private val emptyAction = object : MajorAction {
   private val date = jsDate(1990, 10, 23)
   override fun id(): Int {
      return 0
   }

   override fun description(): String {
      return "pizza"
   }

   override fun raw(): String {
      return ""
   }

   override fun date(): Date {
      return date
   }
}

fun emptyMajorAction(): MajorAction {
   //val dt1 = Date(1990,10,23)
   val dt2 = jsDate(1990, 10, 23)
   val dt3 = jsDate()

   //val dtt1 : Date = dt1
   val dtt2: Date = dt2
   val dtt3: Date = dt3

   return emptyAction
}