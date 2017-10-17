package bz.stew.bracken.ui.view.html.bootstrap.mixins

import bz.stew.bracken.ui.view.html.SubTemplate
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