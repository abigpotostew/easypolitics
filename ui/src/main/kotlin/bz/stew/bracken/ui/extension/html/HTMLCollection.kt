package bz.stew.bracken.ui.extension.html

import org.w3c.dom.Element
import org.w3c.dom.HTMLCollection
import org.w3c.dom.HTMLElement
import org.w3c.dom.asList

/**
 * Created by stew.bracken on 1/29/17.
 */

fun HTMLCollection.each(block: Element.() -> Unit) {
    val len: Int = this.length
    for (i in 0..len) {
        var elm = this.item(i)
        elm?.block()
        //if (elm != null) elm.block()
    }
}

fun HTMLElement.eachChildClass(className: String,
                               block: Element.() -> Unit) {
    this.getElementsByClassName(className).each(block)

}

fun HTMLElement.eachChildId(idName: String,
                            block: (Element) -> Unit) {
    this.getElementsByTagName("*").asList().filter { it.id.equals(idName) }.map { block(it) }//(idName).each (block)

}

//fun HTMLCollection.asList():List<Element>{
//    val out=ArrayList<Element>()
//    this.each { out.add(it) }
//    return out
//}

fun Element.asList(): List<Element> {
    val out = ArrayList<Element>()
    out.add(this)
    return out
}

private val EMPTY_LIST = ArrayList<Element>()
fun Element.emptyList(): List<Element> {
    return EMPTY_LIST
}