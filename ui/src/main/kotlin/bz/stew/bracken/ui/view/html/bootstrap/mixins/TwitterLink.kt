package bz.stew.bracken.ui.view.html.bootstrap.mixins

import bz.stew.bracken.ui.extension.html.LinkTarget
import bz.stew.bracken.ui.view.html.SubTemplate
import bz.stewart.bracken.shared.data.person.Legislator
import kotlinx.html.FlowContent
import kotlinx.html.a

class TwitterLink(private val legislator: Legislator, private val target: LinkTarget = LinkTarget.BLANK) : SubTemplate {
    override fun renderIn(root: FlowContent) {
        val twitter = legislator.getTwitter()
        val linkTarget = target
        root.a {
            +"@"
            +twitter
            href = "http://www.twitter.com/$twitter"
            target = linkTarget.htmlValue()
        }
    }
}