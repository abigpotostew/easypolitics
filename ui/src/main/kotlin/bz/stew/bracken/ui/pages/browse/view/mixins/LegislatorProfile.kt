package bz.stew.bracken.ui.pages.browse.view.mixins

import bz.stew.bracken.ui.extension.kotlinx.ac
import bz.stew.bracken.ui.extension.kotlinx.horzizontalDescriptionList
import bz.stewart.bracken.shared.view.Classes
import bz.stew.bracken.ui.common.view.SubTemplate
import bz.stewart.bracken.shared.data.person.Legislator
import kotlinx.html.*

/**
 * Created by stew on 7/4/17.
 */
class LegislatorProfile(private val legislator: Legislator) : SubTemplate {
   override fun renderIn(root: FlowContent) {
      val l = legislator
      root.p  {
         ac(Classes.boots_row)
         p {
            +"This bill was sponsored by ${l.getFullTitle()}"
         }

         val contentMap = contentMap()
         this.horzizontalDescriptionList(content = contentMap, dlClasses = null)
      }
   }

   private fun contentMap(): Map<String, (HtmlBlockTag) -> Unit> {
      val map = LinkedHashMap<String, (HtmlBlockTag) -> Unit>()
      map.put("Website", this::renderWebsite)
      map.put("Twitter", this::renderTwitter)
      map.put("Phone", this::renderPhone)
      return map
   }

   private fun renderWebsite(root: HtmlBlockTag) {
      val l = legislator
      if (!l.getWebsite().isNullOrBlank()) {
         root.p {
            a {
               +l.getWebsite()!!
               href = l.getWebsite()!!
               target = "_blank"
            }
         }
      }
   }

   private fun renderTwitter(root: HtmlBlockTag) {
      val l = legislator
      if (!l.getTwitter().isBlank()) {
         root.p {
            TwitterLink(l).renderIn(this)
         }
      }
   }

   private fun renderPhone(root: HtmlBlockTag) {
      val l = legislator
      if (!l.getPhoneNumber().isBlank()) {
         root.p {
            a {
               +l.getPhoneNumber()
               href = "#"

            }
            +" looking for a "
            a {
               +"different number?"
               href = "https://phonecongress.com/"
               target = "_blank"
            }
         }
      }
   }
}