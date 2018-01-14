package bz.stewart.bracken.web.view.browse

import bz.stewart.bracken.web.extension.setId
import bz.stewart.bracken.web.html.ViewRender
import bz.stewart.bracken.web.service.WebPageContext
import kotlinx.html.*

class NextQueryFilterForm : ViewRender {
    override fun renderIn(parent: FlowContent, context: WebPageContext) {
        parent.input (classes = "btn btn-primary btn-sm") {
            setId("loadNextPageBtn")
            type=InputType.button
            value="Load Next Page"

        }
    }
}