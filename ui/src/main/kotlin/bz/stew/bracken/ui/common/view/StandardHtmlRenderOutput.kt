package bz.stew.bracken.ui.common.view

import bz.stew.bracken.ui.common.view.HtmlRenderOutput
import org.w3c.dom.HTMLElement

/**
 * Created by stew on 7/4/17.
 */
class StandardHtmlRenderOutput(val domElement: HTMLElement) : HtmlRenderOutput {
    override fun getHtml(): HTMLElement {
        return domElement
    }

    override fun toString(): String {
        return domElement.toString()
    }
}