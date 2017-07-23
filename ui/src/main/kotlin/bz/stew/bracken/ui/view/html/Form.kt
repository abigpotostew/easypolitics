package bz.stew.bracken.ui.view.html

import org.w3c.dom.Element
import kotlin.browser.document

/**
 * Created by stew on 2/8/17.
 */
enum class Form {
    Option {
        override fun generateHtml(innerHtml: String, options: Map<String, String>): Element {
            val out = document.createElement("option")
            for (pair in options.entries) {
                out.setAttribute(pair.key, pair.value)
            }
            out.innerHTML = innerHtml
            return out
        }
    },

    ;

    abstract fun generateHtml(innerHtml: String, options: Map<String, String>): Element
}