package bz.stewart.bracken.web.view.browse

import bz.stewart.bracken.web.extension.setId
import bz.stewart.bracken.web.html.ViewRender
import bz.stewart.bracken.web.service.WebPageContext
import kotlinx.html.*

class MajorActionFilterForm :ViewRender {
   override fun renderIn(parent: FlowContent, context: WebPageContext) {
      parent.form(classes = "form-inline mr-sm-2") {
         label(classes = "mr-sm-2") {
            attributes.put("for", "billFilter-lastmajorstatus")
            +"Major Action:"
         }
         select(classes = "custom-select form-inline mb-2 mr-sm-2 mb-sm-0") {
            setId("billFilter-lastmajorstatus")
            option {
               attributes.put("selected", "true")
               attributes.put("value", "0")
               +"All"
            }
         }
      }
   }
}