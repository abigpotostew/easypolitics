package bz.stewart.bracken.web.view

import bz.stewart.bracken.web.html.ViewTemplate
import kotlinx.html.HtmlBlockTag
import kotlinx.html.br
import kotlinx.html.div

class BrowseBillsView : ViewTemplate {
    override fun renderIn(parent: HtmlBlockTag) {
        parent.div {

            NavBar().renderIn(this)
            br { }
            br { }
            br { }
            br { }
            br { }

            ContentRoot(BillsMultiView()).renderIn(this)
        }

    }
}

