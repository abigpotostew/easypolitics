package bz.stew.bracken.ui.pages.browse.view.mixins

import bz.stew.bracken.ui.common.view.SubTemplate
import bz.stewart.bracken.shared.data.person.Legislator
import kotlinx.html.FlowContent

class BillContact(private val sponsor: Legislator) : SubTemplate {
   override fun renderIn(root: FlowContent) {
      LegislatorProfile(sponsor).renderIn(root)
   }
}