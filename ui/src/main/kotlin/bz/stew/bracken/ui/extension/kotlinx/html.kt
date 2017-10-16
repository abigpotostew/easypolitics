package bz.stew.bracken.ui.extension.kotlinx

import bz.stew.bracken.ui.view.html.ClassGroup
import bz.stew.bracken.ui.view.html.Classes
import bz.stew.bracken.ui.view.html.CssClass
import kotlinx.html.CommonAttributeGroupFacade
import kotlinx.html.DL
import kotlinx.html.DT
import kotlinx.html.FlowContent
import kotlinx.html.HTMLTag
import kotlinx.html.HtmlBlockTag
import kotlinx.html.classes
import kotlinx.html.dd
import kotlinx.html.dt

fun CommonAttributeGroupFacade.ac(newClass: String) {
    this.classes = this.classes.plus(newClass)
}

fun CommonAttributeGroupFacade.ac(id: Classes) {
    this.ac(id.lbl)
}

fun CommonAttributeGroupFacade.ac(vararg ts: String) {
    for (clazz in ts) {
        this.ac(clazz)
    }
}

fun CommonAttributeGroupFacade.ac(vararg ts: Classes) {
    for (id in ts) {
        this.ac(id.lbl)
    }
}

typealias HtmlFunc = (HTMLTag) -> Unit

fun HtmlBlockTag.horzizontalDescriptionList(content: Map<String, HtmlFunc>) {
    dl(Classes.boots_row) {
        for ((titleString, dataFunc) in content) {
            dt {
                this.ac(Classes.boots_colSm2, Classes.boots_colXl1)
                +titleString
                //this.text(titleString)
            }
            dd {
                ac(Classes.boots_colSm10, Classes.boots_colXl11)
                dataFunc(this)
            }
        }
    }
}

fun HtmlBlockTag.horzizontalDescriptionList(content: Map<HtmlFunc, HtmlFunc>) {
    dl(Classes.boots_row) {
        for ((titleFunc, dataFunc) in content) {
            dt {
                ac(Classes.boots_colSm2, Classes.boots_colXl1)
                titleFunc(this)
            }
            dd {
                ac(Classes.boots_colSm10, Classes.boots_colXl11)
                dataFunc(this)
            }
        }
    }
}

private fun HTMLTag.classTag(c: CssClass) {
    this.attributes.put("class", c.label())
}

private inline fun <T : HTMLTag> contentTag(tag: T, c: CssClass, contents: T.() -> Unit) {
    tag.classTag(c)
    tag.contents()
}

fun FlowContent.dt(vararg c: CssClass,
                   contents: DT.() -> Unit) = contentTag(DT(emptyMap(), this.consumer), ClassGroup(*c), contents)

fun FlowContent.dl(vararg c: CssClass,
                   contents: DL.() -> Unit) = contentTag(DL(emptyMap(), this.consumer), ClassGroup(*c), contents)