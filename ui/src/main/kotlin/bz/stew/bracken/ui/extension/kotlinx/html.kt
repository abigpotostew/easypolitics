package bz.stew.bracken.ui.extension.kotlinx

import bz.stew.bracken.ui.view.html.Classes
import kotlinx.html.HtmlBodyTag
import kotlinx.html.dd
import kotlinx.html.dl
import kotlinx.html.dt

fun HtmlBodyTag.ac(newClass: String) {
    this.addClass(newClass)
}

fun HtmlBodyTag.ac(id: Classes) {
    this.addClass(id.lbl)
}

fun HtmlBodyTag.ac(vararg ts: String) {
    for (clazz in ts) {
        this.addClass(clazz)
    }
}

fun HtmlBodyTag.ac(vararg ts: Classes) {
    for (id in ts) {
        this.addClass(id.lbl)
    }
}

typealias HtmlFunc = (HtmlBodyTag) -> Unit

fun HtmlBodyTag.horzizontalDescriptionList(content: Map<String, HtmlFunc>) {
    dl(Classes.boots_row) {
        for ((titleString, dataFunc) in content) {
            dt {
                ac(Classes.boots_colSm2, Classes.boots_colXl1)
                +titleString
            }
            dd {
                ac(Classes.boots_colSm10, Classes.boots_colXl11)
                dataFunc(this)
            }
        }
    }
}

fun HtmlBodyTag.horzizontalDescriptionList(content: Map<HtmlFunc, HtmlFunc>) {
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