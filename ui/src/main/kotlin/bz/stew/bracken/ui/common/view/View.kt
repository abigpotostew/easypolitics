package bz.stew.bracken.view

import bz.stew.bracken.ui.extension.jquery.ext.JQuery
import bz.stew.bracken.ui.extension.jquery.ext.jQuery
import org.w3c.dom.HTMLElement

/**
 * Created by stew on 1/23/17.
 */

abstract class View {

    private var loading: Boolean = false
    var element: HTMLElement? = null

    fun setLoading(isLoading: Boolean) {
        this.loading = isLoading
    }

    fun getElementBySelector(selector: HtmlSelector): HTMLElement {
        return getJq(selector).get(0)
    }

    protected fun getJq(selector: HtmlSelector): JQuery {
        return jQuery(selector.text())
    }
}