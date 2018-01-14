package bz.stewart.bracken.web.view.browse

import bz.stewart.bracken.web.extension.setId
import bz.stewart.bracken.web.html.ViewRender
import bz.stewart.bracken.web.service.WebPageContext
import kotlinx.html.HtmlBlockTag
import kotlinx.html.form
import kotlinx.html.label
import kotlinx.html.option
import kotlinx.html.select

class PartyFilterForm:ViewRender {
   override fun renderIn(parent: HtmlBlockTag, context: WebPageContext) {
      parent.form(classes = "mr-sm-2 text-white") {
         label(classes = "mr-sm-2 text-white") {
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