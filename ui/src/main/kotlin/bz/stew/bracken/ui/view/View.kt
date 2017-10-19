package bz.stew.bracken.view

import bz.stew.bracken.ui.extension.jquery.children
import bz.stew.bracken.ui.extension.jquery.get
import bz.stew.bracken.ui.extension.jquery.remove
import bz.stew.bracken.ui.view.html.Identifier
import jquery.JQuery
import jquery.jq
import org.w3c.dom.HTMLElement

//import bz.stew.bracken.jquery

/**
 * Created by stew on 1/23/17.
 */

abstract class View(val rootElementSelector: HtmlSelector = HtmlSelector(Identifier.TAG, "body")) {

    private var loading: Boolean = false
    protected val htmlGen: HtmlGen = HtmlGen()

    fun setLoading(isLoading: Boolean) {
        this.loading = isLoading
    }

    fun saveElementTemplate(templateName: String,
                            contentsOf: HtmlSelector) {
        this.htmlGen.addElement(templateName,
                getJq(contentsOf).html())
    }

    fun clearRoot() {
        getJq(rootElementSelector).children().remove()
    }

    fun getElement(selector: HtmlSelector): HTMLElement {
        return getJq(selector).get(0)
    }

    protected fun getJq(selector: HtmlSelector): JQuery {
        return jq(selector.text())
    }
}