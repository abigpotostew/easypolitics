package bz.stewart.bracken.web.view

import bz.stewart.bracken.web.extension.ac
import bz.stewart.bracken.web.html.ViewRender
import bz.stewart.bracken.web.service.WebPageContext
import kotlinx.html.FlowContent
import kotlinx.html.div
import kotlinx.html.id

/**
 * Creates a root div element and renders the contents within. The #root element is expected by the UI
 * runtime.
 */
class ContentRoot(private val content: ViewRender? = null) : ViewRender {
    override fun renderIn(parent: FlowContent, context: WebPageContext) {
        parent.div {
            this.id = "root"
            ac("container")

            content?.renderIn(this, context)
        }
    }
}