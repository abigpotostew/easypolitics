package bz.stewart.bracken.shared.data.party

import bz.stewart.bracken.shared.data.BadStateException
import bz.stewart.bracken.shared.data.EPTypeHelper
import bz.stewart.bracken.shared.data.VisibleType

/**
 * warning, these names are used as strings during parse:( be careful.
 * Created by stew on 1/23/17.
 */
enum class Party(niceString: String, private val shortCode:String) : VisibleType {
    NONE("None", ""),DEMOCRAT("Democrat", "dem"), DEMON("Demon", "demon"), REPUBLICAN("Republican", "rep"), INDEPENDENT("Independent", "ind");

    val helper = EPTypeHelper(niceString)
    override fun lowercaseName(): String {
        return helper.lowercaseName()
    }

    override fun capitalizedName(): String {
        return helper.capitalizedName()
    }

    override fun niceFormat(): String {
        return helper.niceFormat()
    }

    override fun shortLabel(): String {
        throw BadStateException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun toString(): String {
        return "Party: "+niceFormat()
    }

    override fun shortCode(): String {
        return shortCode
    }
}