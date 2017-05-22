package bz.stewart.bracken.shared.data.person

import bz.stewart.bracken.shared.data.EPTypeHelper
import bz.stewart.bracken.shared.data.VisibleType

/**
 * Created by stew on 1/23/17.
 */
enum class LegislatorRole(niceString: String, private val shortLabel: String,
                          private val shortCode: String) : VisibleType {
   NONE("None", "None", ""),
   SENATOR("Senator", "Sen.", "sen"),
   REPRESENTATIVE("Representative", "Rep.", "rep");

   val helper = EPTypeHelper(niceString)
   override fun lowercaseName(): String {
      return helper.lowercaseName()
   }

   override fun capitalizedName(): String {
      return helper.capitalizedName()
   }

   override fun niceFormat(): String {
      return helper.niceFormat()
   }

   override fun shortLabel(): String {
      return this.shortLabel
   }

   override fun shortCode(): String {
      return shortCode
   }
}