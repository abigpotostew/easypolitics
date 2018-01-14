package bz.stewart.bracken.web.view.browse

import bz.stewart.bracken.web.extension.setId
import bz.stewart.bracken.web.html.ViewRender
import bz.stewart.bracken.web.service.WebPageContext
import kotlinx.html.*

class PartyFilterForm:ViewRender {
   override fun renderIn(parent: FlowContent, context: WebPageContext) {
      parent.form(classes = "mr-sm-2") {
         label(classes = "mr-sm-2") {
            attributes.put("for", "billFilter-party")
            +"Party:"
         }
         select(classes = "custom-select form-inline mb-2 mr-sm-2 mb-sm-0") {
            setId("billFilter-party")
            option {
               attributes.put("selected", "true")
               attributes.put("value", "NONE")
               +"All"
            }
            option {
               attributes.put("value", "DEMOCRAT")
               +"Democrat"
            }
            option {
               attributes.put("value", "REPUBLICAN")
               +"Republican"
            }
            option {
               attributes.put("value", "INDEPENDENT")
               +"Independent"
            }

         }
      }
   }
}