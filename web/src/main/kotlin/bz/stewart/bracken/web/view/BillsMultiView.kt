package bz.stewart.bracken.web.view

import bz.stewart.bracken.web.extension.ac
import bz.stewart.bracken.web.html.ViewTemplate
import kotlinx.html.HtmlBlockTag
import kotlinx.html.div
import kotlinx.html.id

class BillsMultiView : ViewTemplate {
    override fun renderIn(parent: HtmlBlockTag) {
        parent.apply {
            div {
                this.id = "bills"
                ac("card-deck")
            }
        }
    }
}