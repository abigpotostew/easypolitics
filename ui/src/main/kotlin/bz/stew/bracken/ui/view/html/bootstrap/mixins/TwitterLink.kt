package bz.stew.bracken.ui.view.html.bootstrap.mixins

import bz.stew.bracken.ui.extension.html.LinkTarget
import bz.stew.bracken.ui.view.html.SubTemplate
import bz.stewart.bracken.shared.data.person.Legislator
import kotlinx.html.DirectLink
import kotlinx.html.HtmlBlockTag
import kotlinx.html.HtmlBodyTag
import kotlinx.html.a

class TwitterLink(private val legislator: Legislator, private val target: LinkTarget = LinkTarget.BLANK) : SubTemplate {
    override fun renderIn(root: HtmlBlockTag) {
        val twitter = legislator.getTwitter()
        val linkTarget = target
        root.a {
            +"@"
            +twitter
            href = DirectLink("http://www.twitter.com/$twitter")
            target = linkTarget.htmlValue()
        }
    }
}