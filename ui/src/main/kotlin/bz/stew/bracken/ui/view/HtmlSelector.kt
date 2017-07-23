package bz.stew.bracken.view

import bz.stew.bracken.ui.view.html.Identifier
import jquery.JQuery
import org.w3c.dom.Element
import org.w3c.dom.asList
import kotlin.browser.document

/**
 * Immutable selector
 * Created by stew.bracken on 1/28/17.
 */
class HtmlSelector(private val identifier: Identifier = Identifier.TAG,
                   private val selectorText: String = "") {

    private val textCache: String = identifier.getPrefix() + selectorText

    override fun toString(): String {
        return this.text()
    }

    fun text(): String {
        return textCache
    }

    /**
     * The class '.' or id '#' prefix
     */
    fun prefix(): String {
        return identifier.getPrefix()
    }

    /**
     *  The actual custom identifying text.
     *  @return if selector is #element1, returns "element1"
     */
    fun suffix(): String {
        return selectorText
    }

    fun addToJqElement(obj: JQuery) {
        when (this.identifier) {
            Identifier.ID -> obj.attr("id", suffix())
            Identifier.CLASS -> obj.addClass(suffix())
            Identifier.TAG -> throw UnsupportedOperationException("Can't add a tag to a class.")
        }
    }

    fun getElements(): List<Element> {
        return when (this.identifier) {
            Identifier.CLASS -> {
                return document.getElementsByClassName(suffix()).asList()
            }
            Identifier.ID -> {
                val elm: Element? = document.getElementById(suffix())
                if (elm != null) {
                    return MutableList<Element>(1, { elm }).toList()
                }
                return ArrayList<Element>() //empty list
            }
            Identifier.TAG -> {
                document.getElementsByTagName(suffix()).asList()
            }
        }
    }
}