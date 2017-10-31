package bz.stew.bracken.ui.pages.browse.view.mixins

import bz.stew.bracken.ui.common.view.SubTemplate
import kotlinx.html.FlowContent
import kotlinx.html.p

class Paragraph(private val text: String) : SubTemplate {
   override fun renderIn(root: FlowContent) {
      val txt = text
      root.p {
         +txt
      }
   }
}