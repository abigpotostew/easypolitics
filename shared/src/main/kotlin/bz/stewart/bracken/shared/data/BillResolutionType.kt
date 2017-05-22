package bz.stewart.bracken.shared.data

/**
 * Created by stew on 1/23/17.
 */
enum class BillResolutionType(niceString: String,
                              private val shortLabel: String, private val shortCode:String) : VisibleType {
    NONE("None",
         "None", ""),
    BILL("Bill",
         "Bill", "b"),
    RESOLUTION("Resolution",
               "Res.", "r");

    override fun shortCode(): String {
        return shortCode
    }

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
        return shortLabel
    }
}