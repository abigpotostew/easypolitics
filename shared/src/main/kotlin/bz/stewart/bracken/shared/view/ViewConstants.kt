package bz.stewart.bracken.shared.view

/**
 * Created by stew on 2/21/17.
 */
enum class ViewConstants(niceString: String, private val shortLabel:String): ViewConstant {
    Empty("", ""),
    Introduced("Introduced", "Introduced"),
    Failed("Failed", "Failed"),
    PassedHouse("Passed House", "P.H."),
    PassedSenate("Passed Senate", "P.H."),
    SignedByPresident("Signed by President", "Signed"),
    EnactedLaw("Enacted Law", "Enacted")
    ;





    private val helper = ViewConstantHelper(niceString)
    override fun lowercaseName(): String {
        return helper.lowercaseName()
    }
    override fun capitalizedName(): String {
        return helper.capitalizedName()
    }
    override fun niceFormat(): String {
        return helper.niceFormat()
    }
    override fun shortLabel():String{
        return shortLabel
    }
}