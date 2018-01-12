package bz.stewart.bracken.web.view

import bz.stewart.bracken.web.extension.ac
import bz.stewart.bracken.web.html.ViewRender
import bz.stewart.bracken.web.service.WebPageContext
import kotlinx.html.HtmlBlockTag
import kotlinx.html.div
import kotlinx.html.id

class BillsMultiView : ViewRender {
    override fun renderIn(parent: HtmlBlockTag, context: WebPageContext) {
        parent.apply {
            div {
                this.id = "bills"
                ac("card-deck")
            }
        }
    }
}