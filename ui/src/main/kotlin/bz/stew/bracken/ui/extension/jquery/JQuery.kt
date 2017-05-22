@file:Suppress("unused", "UNUSED_PARAMETER", "NOTHING_TO_INLINE")

package bz.stew.bracken.ui.extension.jquery

import jquery.JQuery
import org.w3c.dom.HTMLElement

/**
 * extensions for JQuery
 * Created by stew on 1/24/17.
 */


@Suppress("NOTHING_TO_INLINE")
inline fun JQuery.fadeIn(speed: String): JQuery = asDynamic().fadeIn(speed)

//hack!!
fun JQuery.get(idx: Int): HTMLElement {
    return this.asDynamic()[idx]
}

fun JQuery.hide(): JQuery = asDynamic().hide()
fun JQuery.hide(dur:Int): JQuery = asDynamic().hide(dur)
fun JQuery.hide(dur:String): JQuery = asDynamic().hide(dur)

inline fun JQuery.show(): JQuery = asDynamic().show()
inline fun JQuery.show(dur:Int): JQuery = asDynamic().show(dur)
inline fun JQuery.show(dur:String): JQuery = asDynamic().show(dur)


inline fun JQuery.append(jQuery: JQuery): JQuery = asDynamic().append(jQuery)


inline fun JQuery.children(jQuery: JQuery): JQuery = asDynamic().children(jQuery)

inline fun JQuery.children(): JQuery = asDynamic().children()

inline fun JQuery.children(selector: String): JQuery = asDynamic().children(selector)


inline fun JQuery.first(): HTMLElement = asDynamic().first()


inline fun JQuery.remove(): JQuery = asDynamic().remove()

inline fun JQuery.remove(selector: String): JQuery = asDynamic().remove(selector)


inline fun JQuery.css(prop:String, value:String):JQuery= asDynamic().css(prop, value)

inline fun JQuery.velocity(prop:String, opts:dynamic?):JQuery=asDynamic().velcity(prop,opts)

//@JsName("$")
//external fun jq(selector: String): JQuery = noImpl
//

/* backup of jquery stdlib
package jquery

import org.w3c.dom.Element

@Deprecated("JQuery is going to be removed from the standard library")
public external class JQuery() {
    public fun addClass(className: String): JQuery
    public fun addClass(f: Element.(Int, String) -> String): JQuery

    public fun attr(attrName: String): String
    public fun attr(attrName: String, value: String): JQuery

    public fun html(): String
    public fun html(s: String): JQuery
    public fun html(f: Element.(Int, String) -> String): JQuery


    public fun hasClass(className: String): Boolean
    public fun removeClass(className: String): JQuery
    public fun height(): Number
    public fun width(): Number

    public fun click(): JQuery

    public fun mousedown(handler: Element.(MouseEvent) -> Unit): JQuery
    public fun mouseup(handler: Element.(MouseEvent) -> Unit): JQuery
    public fun mousemove(handler: Element.(MouseEvent) -> Unit): JQuery

    public fun dblclick(handler: Element.(MouseClickEvent) -> Unit): JQuery
    public fun click(handler: Element.(MouseClickEvent) -> Unit): JQuery

    public fun load(handler: Element.() -> Unit): JQuery
    public fun change(handler: Element.() -> Unit): JQuery

    public fun append(str: String): JQuery
    public fun ready(handler: () -> Unit): JQuery
    public fun text(text: String): JQuery
    public fun slideUp(): JQuery
    public fun hover(handlerInOut: Element.() -> Unit): JQuery
    public fun hover(handlerIn: Element.() -> Unit, handlerOut: Element.() -> Unit): JQuery
    public fun next(): JQuery
    public fun parent(): JQuery
    public fun `val`(): String?
}

@Deprecated("JQuery is going to be removed from the standard library")
open public external class MouseEvent() {
    public val pageX: Double
    public val pageY: Double
    public fun preventDefault()
    public fun isDefaultPrevented(): Boolean
}

@Deprecated("JQuery is going to be removed from the standard library")
public external class MouseClickEvent() : MouseEvent {
    public val which: Int
}

@Deprecated("JQuery is going to be removed from the standard library")
@JsName("$")
public external fun jq(selector: String): JQuery
@Deprecated("JQuery is going to be removed from the standard library")
@JsName("$")
public external fun jq(selector: String, context: Element): JQuery
@Deprecated("JQuery is going to be removed from the standard library")
@JsName("$")
public external fun jq(callback: () -> Unit): JQuery
@Deprecated("JQuery is going to be removed from the standard library")
@JsName("$")
public external fun jq(obj: JQuery): JQuery
@Deprecated("JQuery is going to be removed from the standard library")
@JsName("$")
public external fun jq(el: Element): JQuery
@Deprecated("JQuery is going to be removed from the standard library")
@JsName("$")
public external fun jq(): JQuery

 */