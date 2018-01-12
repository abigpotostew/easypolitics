package bz.stewart.bracken.web.html

import bz.stewart.bracken.web.service.WebPageContext
import kotlinx.html.HtmlBlockTag

interface ViewRender {
    fun renderIn(parent: HtmlBlockTag, context: WebPageContext)
}