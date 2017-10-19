package bz.stew.bracken.view

import bz.stew.bracken.ui.extension.jquery.ext.JQuery
import bz.stew.bracken.ui.extension.jquery.ext.jQuery


/**
 * Created by stew on 1/24/17.
 */
class HtmlGen {

	val templates: MutableMap<String, String> = mutableMapOf()

	fun addElement(name: String, htmlString: String) {
		this.templates[name] = htmlString
	}

	/**
	 * builds the template using jquery
	 */
	fun buildElement(name: String) : JQuery {
		val str : String? = this.templates[name]
		if (str != null ) {
			return jQuery(str)
		}
		throw NullPointerException("HTML template with name '${name} doesn't exist.")
	}
}