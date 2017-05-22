package bz.stew.bracken.ui.view.html

import jquery.JQuery

/**
 * Created by stew.bracken on 1/28/17.
 */
enum class Identifier(private val prefix: String) {
    ID("#"), CLASS("."), TAG("");

    fun getPrefix(): String {
        return this.prefix
    }

    override fun toString(): String {
        return "HtmlIdentifier [" + this.name + "]"
    }
}