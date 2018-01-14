package bz.stewart.bracken.web.view.browse

import bz.stewart.bracken.web.extension.setId
import bz.stewart.bracken.web.html.ViewRender
import bz.stewart.bracken.web.service.WebPageContext
import kotlinx.html.*

class CountBillsFilterForm : ViewRender {
    override fun renderIn(parent: FlowContent, context: WebPageContext) {
        parent.span(classes = "navbar-text") {
            setId("nav-bar-billCount")
            +"Showing 0 bills"
        }
    }
}