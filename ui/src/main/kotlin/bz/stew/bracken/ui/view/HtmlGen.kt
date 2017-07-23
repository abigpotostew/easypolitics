package bz.stew.bracken.view

import jquery.JQuery
import jquery.jq

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
			return jq(str)
		}
		throw NullPointerException("HTML template with name '${name} doesn't exist.")
	}
}