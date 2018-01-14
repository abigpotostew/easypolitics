package bz.stewart.bracken.web.extension

import bz.stewart.bracken.shared.view.Classes
import kotlinx.html.CommonAttributeGroupFacade
import kotlinx.html.HtmlBlockTag
import kotlinx.html.classes
import kotlinx.html.id

fun HtmlBlockTag.ac(className: String) {
    this.classes = this.classes.plus(className)
}

fun HtmlBlockTag.ac(vararg classes: Classes) {
    this.classes = this.classes.plus(classes.joinToString(" ") { it.label() })
}

fun CommonAttributeGroupFacade.setId(id: String) {
    this.id = id
}