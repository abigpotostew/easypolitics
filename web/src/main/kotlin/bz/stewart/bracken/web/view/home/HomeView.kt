package bz.stewart.bracken.web.view.home

import bz.stewart.bracken.web.html.ViewRender
import bz.stewart.bracken.web.service.WebPageContext
import bz.stewart.bracken.web.view.bootstrap.BootstrapNavConfig
import bz.stewart.bracken.web.view.bootstrap.CommonHeader
import kotlinx.html.FlowContent
import kotlinx.html.div

class HomeView(val debugText:String) : ViewRender {
    override fun renderIn(parent: FlowContent, context: WebPageContext) {
        //CommonHeader(BootstrapNavConfig()).renderIn(parent, context)
        parent.div {
            +debugText
        }
    }
}

