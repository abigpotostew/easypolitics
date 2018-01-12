package bz.stewart.bracken.web.view

import bz.stewart.bracken.web.html.ViewRender
import bz.stewart.bracken.web.service.WebPageContext
import kotlinx.html.HtmlBlockTag
import kotlinx.html.div
import kotlinx.html.h1

class PrintInputView(private val string: String) : ViewRender {
    override fun renderIn(parent: HtmlBlockTag, context: WebPageContext) {
        parent.h1 {
            div {
                +string
            }
        }
    }
}