package bz.stew.bracken.ui.model.parse.bill.govtrack

import bz.stew.bracken.ui.extension.html.jsDate
import bz.stew.bracken.ui.model.types.bill.MajorAction
import kotlin.js.Date

/**
 * Created by stew on 2/13/17.
 */
private val dateRegex: Regex = Regex(
      "datetime\\.datetime\\((\\d{4}),\\s{0,1}(\\d{1,2}),\\s{0,1}(\\d{1,2})")

class GovTrackMajorAction(date: String,
                          private val id: Int = -1,
                          private val description: String = "none",
                          private val xml: String = "none") : MajorAction {

   private val date = resolveDate(date)

   private fun resolveDate(dateString: String): Date {
      val matches = dateRegex.findAll(dateString)
      var matchGroups: List<String> = matches.iterator().next().groupValues
      val year = matchGroups[1].toInt()
      val month = matchGroups[2].toInt() - 1
      val day = matchGroups[3].toInt()
      //val hour = matchGroups[4].toInt()
      val d = jsDate(year, month, day)
      return d
   }

   override fun id(): Int {
      return id
   }

   override fun description(): String {
      return description
   }

   override fun raw(): String {
      return xml
   }

   override fun date(): Date {
      return date
   }
}