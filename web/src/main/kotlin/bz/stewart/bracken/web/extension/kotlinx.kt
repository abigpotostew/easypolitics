package bz.stewart.bracken.web.extension

import kotlinx.html.CommonAttributeGroupFacade
import kotlinx.html.HtmlBlockTag
import kotlinx.html.classes
import kotlinx.html.id

fun HtmlBlockTag.ac(className: String) {
    this.classes = this.classes.plus(className)
}

fun CommonAttributeGroupFacade.setId(id: String) {
    this.id = id
}