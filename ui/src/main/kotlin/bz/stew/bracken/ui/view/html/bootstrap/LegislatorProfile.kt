package bz.stew.bracken.ui.view.html.bootstrap

import bz.stew.bracken.ui.view.html.SubTemplate
import bz.stewart.bracken.shared.data.person.Legislator
import kotlinx.html.DirectLink
import kotlinx.html.HtmlBodyTag
import kotlinx.html.a
import kotlinx.html.p

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
         template.renderTwitter(this)
         template.renderPhone(this)
         template.renderWebsite(this)
      }
   }

   private fun renderWebsite(root: HtmlBodyTag) {
      val l = legislator
      if (!l.getWebsite().isNullOrBlank()) {
         root.p {
            a {
               +"Official Website"
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
            +"Twitter: "
            a {
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
            a {
               +l.getPhoneNumber()
               href = DirectLink("#")
            }
         }
      }
   }
}