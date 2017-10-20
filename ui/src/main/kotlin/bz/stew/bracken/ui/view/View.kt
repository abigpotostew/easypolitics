package bz.stew.bracken.view

import bz.stew.bracken.ui.extension.jquery.ext.JQuery
import bz.stew.bracken.ui.extension.jquery.ext.jQuery
import bz.stew.bracken.ui.view.html.Identifier
import org.w3c.dom.HTMLElement

//import bz.stew.bracken.jquery

/**
 * Created by stew on 1/23/17.
 */

abstract class View(val rootElementSelector: HtmlSelector = HtmlSelector(Identifier.TAG, "body")) {

    private var loading: Boolean = false

    fun setLoading(isLoading: Boolean) {
        this.loading = isLoading
    }

    fun clearRoot() {
        getJq(rootElementSelector).children().remove()
    }

    fun getElement(selector: HtmlSelector): HTMLElement {
        return getJq(selector).get(0)
    }

    protected fun getJq(selector: HtmlSelector): JQuery {
        return jQuery(selector.text())
    }
}