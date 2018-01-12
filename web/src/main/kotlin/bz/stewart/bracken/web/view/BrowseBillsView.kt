package bz.stewart.bracken.web.view

import bz.stewart.bracken.web.html.ViewRender
import bz.stewart.bracken.web.service.WebPageContext
import bz.stewart.bracken.web.view.bootstrap.BootstrapNavConfig
import bz.stewart.bracken.web.view.bootstrap.CommonHeader
import kotlinx.html.HtmlBlockTag
import kotlinx.html.br
import kotlinx.html.div

class BrowseBillsView : ViewRender {
    override fun renderIn(parent: HtmlBlockTag, context: WebPageContext) {
        //CommonHeader(BootstrapNavConfig().getNavs(context)).renderIn(parent, context)
        parent.div {
            NavBar().renderIn(this, context)

            br { }
            br { }
            br { }
            br { }
            br { }

            ContentRoot(BillsMultiView()).renderIn(this, context)
        }


    }
}

