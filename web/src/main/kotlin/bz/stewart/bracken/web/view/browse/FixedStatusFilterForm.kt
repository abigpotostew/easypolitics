package bz.stewart.bracken.web.view.browse

import bz.stewart.bracken.web.extension.setId
import bz.stewart.bracken.web.html.ViewRender
import bz.stewart.bracken.web.service.WebPageContext
import kotlinx.html.HtmlBlockTag
import kotlinx.html.form
import kotlinx.html.label
import kotlinx.html.option
import kotlinx.html.select

class FixedStatusFilterForm :ViewRender{
   override fun renderIn(parent: HtmlBlockTag, context: WebPageContext) {
      parent.form(classes = "form-inline mr-sm-2 text-white") {
         label(classes = "mr-sm-2 text-white") {
            attributes.put("for", "billFilter-fixedstatus")
            +"Status:"
         }
         select(classes = "custom-select form-inline mb-2 mr-sm-2 mb-sm-0") {
            setId("billFilter-fixedstatus")
            option {
               attributes.put("selected", "true")
               attributes.put("value", "0")
               +"All"
            }
         }
      }
   }
}