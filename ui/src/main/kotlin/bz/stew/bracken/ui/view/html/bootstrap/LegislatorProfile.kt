package bz.stew.bracken.ui.view.html.bootstrap

import bz.stew.bracken.ui.extension.kotlinx.horzizontalDescriptionList
import bz.stew.bracken.ui.view.html.SubTemplate
import bz.stewart.bracken.shared.data.person.Legislator
import kotlinx.html.*

/**
 * Created by stew on 7/4/17.
 */
class LegislatorProfile(private val legislator: Legislator) : SubTemplate {
   override fun renderIn(root: HtmlBodyTag) {
      val template = this
      val l = legislator
      root.p {
         p {
            +"This bill was sponsored by ${l.getFullTitle()}"
         }

         val contentMap = contentMap()
         this.horzizontalDescriptionList(content = contentMap)
//         template.renderTwitter(this)
//         template.renderPhone(this)
//         template.renderWebsite(this)
      }
//      val func: (HtmlBodyTag) -> Unit = { this::renderPhone }

   }

//   private fun renderDescList(root: HtmlBodyTag, content: Map<String, (HtmlBodyTag) -> Unit>) {
//      root.dl {
//         for ((k, v) in content) {
//            dt {
//               +k
//            }
//            dd {
//               v(this)
//            }
//         }
//      }
//   }

   private fun contentMap(): Map<String,(HtmlBodyTag) -> Unit> {
      val map = LinkedHashMap<String, (HtmlBodyTag) -> Unit>()
      map.put("Website", this::renderWebsite)
      map.put("Twitter", this::renderTwitter)
      map.put("Phone", this::renderPhone)
      return map
   }


   private fun renderWebsite(root: HtmlBodyTag) {
      val l = legislator
      if (!l.getWebsite().isNullOrBlank()) {
         root.p {
            a {
               +l.getWebsite()!!
               href = DirectLink(l.getWebsite()!!)
               target = "_blank"
            }
         }
      }
   }

   private fun renderTwitter(root: HtmlBodyTag) {
      val l = legislator
      if (!l.getTwitter().isNullOrBlank()) {
         root.p {
//            +"Twitter: "
            a {
               +"@"
               +l.getTwitter()
               href = DirectLink("http://www.twitter.com/${l.getTwitter()}")
               target = "_blank"
            }
         }
      }
   }

   private fun renderPhone(root: HtmlBodyTag) {
      val l = legislator
      if (!l.getPhoneNumber().isNullOrBlank()) {
         root.p {
//            +"Phone: "
            a {
               +l.getPhoneNumber()
               href = DirectLink("#")

            }
            +" looking for a "
            a {
               +"different number?"
               href = DirectLink("https://phonecongress.com/")
               target = "_blank"
            }
         }
      }
   }
}