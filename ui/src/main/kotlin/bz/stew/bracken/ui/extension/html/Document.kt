package bz.stew.bracken.ui.extension.html

import org.w3c.dom.DOMPointInit
import org.w3c.dom.Document
import org.w3c.dom.HTMLElement
import kotlin.browser.window

/**
 * Created by stew.bracken on 2/4/17.
 */


fun Document.elementFixedOffset(el:HTMLElement):DOMPointInit {
    val rect = el.getBoundingClientRect()
    val scrollLeft = if (window.pageXOffset!=0.0) window.pageXOffset else this.documentElement!!.scrollLeft
    val scrollTop = if (window.pageYOffset!=0.0) window.pageYOffset else this.documentElement!!.scrollTop

    return DOMPointInit(rect.left+scrollLeft, rect.top + scrollTop)
}