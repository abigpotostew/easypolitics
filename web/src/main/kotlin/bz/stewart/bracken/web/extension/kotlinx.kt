package bz.stewart.bracken.web.extension

import kotlinx.html.BUTTON
import kotlinx.html.ButtonType
import kotlinx.html.CommonAttributeGroupFacade
import kotlinx.html.DIV
import kotlinx.html.FlowContent
import kotlinx.html.HtmlBlockTag
import kotlinx.html.classes
import kotlinx.html.id

fun HtmlBlockTag.ac(className: String) {
    this.classes = this.classes.plus(className)
}

//public fun kotlinx.html.FlowContent.div(classes: kotlin.String? /* = compiled code */,
// block: kotlinx.html.DIV.() -> kotlin.Unit /* = compiled code */): kotlin.Unit { /* compiled code */ }
fun FlowContent.div(classes: String? = null, id: String? = null, block: DIV.() -> Unit) {
    val newDiv = DIV(attrMap(classes), this.consumer)
    if (id != null) newDiv.id = id
    block.invoke(newDiv)
}

fun FlowContent.button(classes: String? = null,
                       id: String? = null,
                       btnType: ButtonType? = null,
                       block: BUTTON.() -> Unit) {
    val element = BUTTON(attrMap(classes), this.consumer)
    if (btnType != null) element.type = btnType
    if (id != null) element.id = id
    block.invoke(element)
}

fun attrMap(clazz: String? = null): Map<String, String> {
    val out = mutableMapOf<String, String>()
    if (clazz != null) {
        out.put("class", clazz)
    }
    return out
}

fun CommonAttributeGroupFacade.setId(id: String) {
    this.id = id
}