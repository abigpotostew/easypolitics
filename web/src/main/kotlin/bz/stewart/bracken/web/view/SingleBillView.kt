package bz.stewart.bracken.web.view

import bz.stewart.bracken.web.html.ViewRender
import bz.stewart.bracken.web.service.WebPageContext
import bz.stewart.bracken.web.view.bootstrap.BootstrapNavConfig
import bz.stewart.bracken.web.view.bootstrap.CommonHeader
import kotlinx.html.FlowContent

class SingleBillView : ViewRender {
    override fun renderIn(parent: FlowContent, context: WebPageContext) {
        //CommonHeader(BootstrapNavConfig()).renderIn(parent, context)
        ContentRoot().renderIn(parent, context)
    }
}