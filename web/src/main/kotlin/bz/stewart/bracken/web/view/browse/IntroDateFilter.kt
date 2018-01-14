package bz.stewart.bracken.web.view.browse

import bz.stewart.bracken.web.extension.setId
import bz.stewart.bracken.web.html.ViewRender
import bz.stewart.bracken.web.service.WebPageContext
import kotlinx.html.*

class IntroDateFilter :ViewRender {
   override fun renderIn(parent: FlowContent, context: WebPageContext) {
      parent.form(classes = "form-inline mr-sm-2") {
         label(classes = "mr-sm-2") {
            attributes.put("for", "billFilter-dateintrostart")
            +"Introduced Start:"
         }
         input(classes = "form-inline form-control") {
            setId("billFilter-dateintrostart")
            attributes.put("type", "date")
         }
         label(classes = "mr-sm-2") {
            attributes.put("for", "billFilter-dateintroend")
            +"Introduced End:"
         }
         input(classes = "form-inline form-control") {
            setId("billFilter-dateintroend")
            attributes.put("type", "date")
         }
      }
   }
}