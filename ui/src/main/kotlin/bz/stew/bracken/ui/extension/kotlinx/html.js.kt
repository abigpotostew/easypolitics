package bz.stew.bracken.ui.extension.kotlinx

import bz.stew.bracken.ui.view.html.ClassGroup
import bz.stew.bracken.ui.view.html.CssClass
import kotlinx.html.DIV
import kotlinx.html.TagConsumer
import kotlinx.html.attributesMapOf
import kotlinx.html.visitAndFinalize
import org.w3c.dom.HTMLDivElement
import org.w3c.dom.HTMLElement

//private inline fun <E : HTMLElement, T : HtmlBlockTag> contentTagConsume(tag: T, c: CssClass, contents: HtmlBlockTag.() -> Unit) : E {
//    //element.classList.add(c.label())
//    //element.contents()
//    tag.attributes.put("class", c.label())
//    tag.visitAndFinalize()
//    return element
//}

/**
 * Functions for the DSL for creating a dom HtmlElement when using document.createTree()
 */

fun TagConsumer<HTMLElement>.div(vararg c: CssClass,
                                 contents: DIV.() -> Unit): HTMLDivElement
        = DIV(attributesMapOf("class", ClassGroup(*c).label()), this).visitAndFinalize(this, contents) as HTMLDivElement