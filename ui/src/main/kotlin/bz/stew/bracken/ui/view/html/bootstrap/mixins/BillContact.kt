package bz.stew.bracken.ui.view.html.bootstrap.mixins

import bz.stew.bracken.ui.view.html.SubTemplate
import bz.stewart.bracken.shared.data.person.Legislator
import kotlinx.html.HtmlBodyTag

class BillContact(private val sponsor: Legislator) : SubTemplate {
   override fun renderIn(root: HtmlBodyTag) {
      LegislatorProfile(sponsor).renderIn(root)
   }
}