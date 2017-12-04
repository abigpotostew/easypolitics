package bz.stew.bracken.ui.extension.html

import org.w3c.dom.HTMLElement

/**
 * Created by stew on 6/29/17.
 */
fun HTMLElement.removeAllChildrenNodes() {
    while (this.childElementCount > 0) {
        val n = this.firstElementChild
        if (n != undefined)
            this.removeChild(n)
    }
}