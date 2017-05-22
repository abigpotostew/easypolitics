package bz.stewart.bracken.shared.data

/**
 * Created by stew.bracken on 1/28/17.
 */
class EPTypeHelper(private val niceString: String) {

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