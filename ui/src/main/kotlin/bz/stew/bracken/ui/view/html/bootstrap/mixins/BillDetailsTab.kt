package bz.stew.bracken.ui.view.html.bootstrap.mixins

import bz.stew.bracken.ui.extension.kotlinx.horzizontalDescriptionList
import bz.stew.bracken.ui.view.html.Classes
import bz.stew.bracken.ui.view.html.SubTemplate
import bz.stew.bracken.ui.view.html.bootstrap.Bill
import kotlinx.html.HtmlBodyTag
import kotlinx.html.li
import kotlinx.html.p
import kotlinx.html.ul

/**
 * Created by stew on 7/4/17.
 */
class BillDetailsTab(private val template: Bill) : SubTemplate {
   override fun renderIn(root: HtmlBodyTag) {
      val cosponsors = template.billView.billData.cosponsors
      root.p(Classes.billCosponsors) {
         val cosponsors: (HtmlBodyTag) -> Unit = {
            it.ul {
               for (l in cosponsors) {
                  li {
                     +l.getOfficialName()
                  }
               }
            }
         }
         this.horzizontalDescriptionList(mapOf(Pair("Cosponsored by:", cosponsors)))
      }
   }
}