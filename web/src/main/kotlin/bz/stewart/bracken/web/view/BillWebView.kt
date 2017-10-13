package bz.stewart.bracken.web.view

import bz.stewart.bracken.web.html.ViewTemplate
import kotlinx.html.HtmlBlockTag
import kotlinx.html.div
import kotlinx.html.h1

class BillWebView(private val id: String) : ViewTemplate {
    override fun renderIn(parent: HtmlBlockTag) {
        parent.h1 {
            div {
                +"bill $id"
            }
        }
    }
}