package bz.stew.bracken.ui.extension.kotlinx

import bz.stewart.bracken.shared.view.Classes
import bz.stewart.bracken.shared.view.CssClass
import kotlinx.html.CommonAttributeGroupFacade
import kotlinx.html.FlowContent
import kotlinx.html.HTMLTag
import kotlinx.html.HtmlBlockTag
import kotlinx.html.Tag
import kotlinx.html.classes
import kotlinx.html.dd
import kotlinx.html.dl
import kotlinx.html.dt

typealias HtmlFunc = (HtmlBlockTag) -> Unit
typealias DlTitleFunc = (HTMLTag) -> Unit

fun CommonAttributeGroupFacade.ac(newClass: String) {
    this.classes = this.classes.plus(newClass)
}

fun CommonAttributeGroupFacade.ac(id: Classes) {
    this.ac(id.label())
}

fun CommonAttributeGroupFacade.ac(vararg ts: String) {
    for (clazz in ts) {
        this.ac(clazz)
    }
}

fun CommonAttributeGroupFacade.ac(vararg ts: Classes) {
    for (id in ts) {
        this.ac(id.label())
    }
}

fun FlowContent.horzizontalDescriptionList(content: Map<String, HtmlFunc>, dlClasses: Classes?) {
    dl {
        if (dlClasses != null) {
            ac(dlClasses)
        }
        for ((titleString, dataFunc) in content) {
            dt {
                //this.ac(Classes.boots_colSm2, Classes.boots_colXl1)
                +titleString
            }
            dd {
                ac(Classes.boots_colSm10, Classes.boots_colXl11)
                dataFunc(this)
            }
        }
    }
}

fun FlowContent.horzizontalDescriptionList(content: Map<DlTitleFunc, HtmlFunc>) {
    dl {
        ac(Classes.boots_row)
        for ((titleFunc, dataFunc) in content) {
            dt {
                //ac(Classes.boots_colSm2, Classes.boots_colXl1)
                titleFunc(this)
            }
            dd {
                ac(Classes.boots_colSm12, Classes.boots_colXl12)
                dataFunc(this)
            }
        }
    }
}

private fun HTMLTag.classTag(c: CssClass) {
    this.attributes.put("class", c.label())
}

private fun <T : HTMLTag> contentTag(tag: T, c: CssClass, contents: T.() -> Unit) {
    tag.classTag(c)
    tag.contents()
}

inline fun <T : Tag> T.set(attribute: String, value: String) {
    this.attributes.put(attribute, value)
}
/*
fun FlowContent.dt(vararg c: CssClass,
                   contents: DT.() -> Unit) = contentTag(DT(emptyMap(), this.consumer), ClassGroup(*c), contents)

fun FlowContent.dl(vararg c: CssClass,
                   contents: DL.() -> Unit) = contentTag(DL(emptyMap(), this.consumer), ClassGroup(*c), contents)

fun FlowContent.div(vararg c: CssClass,
                    contents: DIV.() -> Unit) = contentTag(DIV(emptyMap(), this.consumer), ClassGroup(*c), contents)

fun FlowContent.table(vararg c: CssClass,
                      contents: TABLE.() -> Unit) = contentTag(TABLE(emptyMap(), this.consumer), ClassGroup(*c), contents)

fun TR.td(vararg c: CssClass,
             contents: TD.() -> Unit) = contentTag(TD(emptyMap(), this.consumer), ClassGroup(*c), contents)

fun TABLE.tr(vararg c: CssClass,
                   contents: TR.() -> Unit) = contentTag(TR(emptyMap(), this.consumer), ClassGroup(*c), contents)

fun FlowContent.p(vararg c: CssClass,
             contents: P.() -> Unit) = contentTag(P(emptyMap(), this.consumer), ClassGroup(*c), contents)

fun FlowContent.img(vararg c: CssClass,
                    contents: IMG.() -> Unit) = contentTag(IMG(emptyMap(), this.consumer), ClassGroup(*c), contents)

fun FlowContent.h5(vararg c: CssClass,
                    contents: H5.() -> Unit) = contentTag(H5(emptyMap(), this.consumer), ClassGroup(*c), contents)

fun FlowContent.button(vararg c: CssClass,
                   contents: BUTTON.() -> Unit) = contentTag(BUTTON(emptyMap(), this.consumer), ClassGroup(*c), contents)
*/