package bz.stewart.bracken.shared.data

/**
 * hr, hres, hjres, hconres, s, sres, sjres, sconres
 * Created by stew on 1/23/17.
 */
enum class BillType(niceString: String, private val shortLabel:String, private val shortCode:String, val isBill:Boolean=false) : VisibleType {
    NONE("None", "None.", "", false),
    SENATE_BILL("Senate Bill", "S.", "s", true),
    HOUSE_BILL("House Bill", "H.R.", "hr", true),
    SENATE_RESOLUTION("Senate Resolution", "S.Res.", "sres"),
    HOUSE_RESOLUTION("House Resolution", "H.Res.", "hres"),
    SENATE_CONCURRENT_RESOLUTION("Senate Concurrent Resolution", "S.Con.Res", "sconres"),
    HOUSE_CONCURRENT_RESOLUTION("House Concurrent Resolution", "H.Con.Res.", "hconres"),
    SENATE_JOINT_RESOLUTION("Senate Joint Resolution", "S.J.Res.", "sjres"),
    HOUSE_JOINT_RESOLUTION("House Joint Resolution", "H.J.Res.", "hjres"),
    ;

    private val helper = EPTypeHelper(niceString)
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

   override fun shortCode(): String {
      return shortCode
   }

    override fun toString(): String {
        return shortCode
    }
}