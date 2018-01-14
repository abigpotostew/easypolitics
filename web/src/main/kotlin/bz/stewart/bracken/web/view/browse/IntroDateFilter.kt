package bz.stewart.bracken.web.view.browse

import bz.stewart.bracken.web.extension.setId
import bz.stewart.bracken.web.html.ViewRender
import bz.stewart.bracken.web.service.WebPageContext
import kotlinx.html.HtmlBlockTag
import kotlinx.html.form
import kotlinx.html.input
import kotlinx.html.label
import kotlinx.html.option
import kotlinx.html.select

class IntroDateFilter :ViewRender {
   override fun renderIn(parent: HtmlBlockTag, context: WebPageContext) {
      parent.form(classes = "form-inline mr-sm-2 text-white") {
         label(classes = "mr-sm-2 text-white") {
            attributes.put("for", "billFilter-dateintrostart")
            +"Introduced:"
         }
         input(classes = "form-inline form-control") {
            setId("billFilter-dateintrostart")
            attributes.put("type", "date")
         }
         label(classes = "mr-sm-2 text-white") {
            attributes.put("for", "billFilter-dateintroend")
            +"Introduced:"
         }
         input(classes = "form-inline form-control") {
            setId("billFilter-dateintroend")
            attributes.put("type", "date")
         }
      }
   }
}