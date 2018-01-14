package bz.stewart.bracken.web.html

import bz.stewart.bracken.web.service.WebPageContext
import kotlinx.html.FlowContent

/**
 * Render some kotlinx html into the parent object.
 */
interface ViewRender {
    fun renderIn(parent: FlowContent, context: WebPageContext)
}