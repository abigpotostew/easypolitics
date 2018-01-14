package bz.stewart.bracken.web.html.config

import bz.stewart.bracken.web.html.ViewRender
import bz.stewart.bracken.web.service.WebPageContext
import kotlinx.html.DIV
import kotlinx.html.FlowContent
import kotlinx.html.HtmlBlockTag
import kotlin.reflect.KClass


abstract class FlowBlockViewRender<T : HtmlBlockTag>(type: KClass<T>, private val viewRender: ViewRender) : TagConfiguration<T>(type, emptyMap()) {
    fun renderView(parent: FlowContent, context: WebPageContext) {
        viewRender.renderIn(parent, context)
    }
}

class DivFlowViewRender(viewRender: ViewRender) : FlowBlockViewRender<DIV>(DIV::class, viewRender) {
    override fun doConfig(tag: DIV, context: WebPageContext) {

    }
}