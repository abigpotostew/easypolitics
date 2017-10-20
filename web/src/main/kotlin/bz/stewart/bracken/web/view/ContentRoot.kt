package bz.stewart.bracken.web.view

import bz.stewart.bracken.web.extension.ac
import bz.stewart.bracken.web.html.ViewTemplate
import kotlinx.html.HtmlBlockTag
import kotlinx.html.div
import kotlinx.html.id

class ContentRoot(val content: ViewTemplate) : ViewTemplate {
    override fun renderIn(parent: HtmlBlockTag) {
        parent.div(classes = "container-fluid") {
            this.id = "root"
            ac("container-fluid")

            content.renderIn(this)
        }
    }
}