package bz.stewart.bracken.shared.view

/**
 * Created by stew on 2/21/17.
 */

class ViewConstantHelper(private val niceString: String) {

    val xmlName: String = niceString.toUpperCase().replace(' ',
                                                           '_')
    val caps: String = xmlName
    val lowerCase: String = caps.toLowerCase()

    fun lowercaseName(): String {
        return this.lowerCase
    }

    fun capitalizedName(): String {
        return this.caps
    }

    fun niceFormat(): String {
        return this.niceString
    }
}