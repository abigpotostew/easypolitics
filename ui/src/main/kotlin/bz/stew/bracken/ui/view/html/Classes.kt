package bz.stew.bracken.ui.view.html

/**
 * Created by stew on 3/5/17.
 */

enum class Classes(labelValue: String, addClasses: List<Classes> = emptyList()) : CssClass {

    EMPTY(""),

    boots_hvr_fade("hvr-fade"),
    //boots_hvr_fade("hvr-bubble-float-bottom"),
    boots_card_header("card-header"),
    boots_card_block("card-block"),
    boots_card_text("card-text"),
    boots_card_subtitle("card-subtitle"),
    boots_card_title("card-title"),
    boots_card_footer("card-footer"),
    boots_card_img_top("card-img-top"),
    boots_tab_pane("tab-pane"),
    boots_tab_card("", listOf(boots_card_block, boots_tab_pane)),

    boots_text_muted("text-muted"),
    boots_text_right("text-right"),

    boots_tab_content("tab-content"),
    boots_container_fluid("container-fluid"),
    boots_row("row"),
    boots_col("col"),
    boots_col_md_6("col-md-6"),
    boots_button_group("btn-group"),
    boots_mr2("mr-2"),
    boots_secondary_button("btn btn-secondary"),

    attr_group("group"),

    //boots_(""),

    css_ml_ellipses("multiline-ellipses5"),

    boots_colSm1("col-sm-1"),
    boots_colSm2("col-sm-2"),
    boots_colSm3("col-sm-3"),
    boots_colSm4("col-sm-4"),
    boots_colSm5("col-sm-5"),
    boots_colSm6("col-sm-6"),
    boots_colSm7("col-sm-7"),
    boots_colSm8("col-sm-8"),
    boots_colSm9("col-sm-9"),
    boots_colSm10("col-sm-10"),
    boots_colSm11("col-sm-11"),
    boots_colSm12("col-sm-12"),
    boots_colMd1("col-md-1"),
    boots_colMd2("col-md-2"),
    boots_colMd3("col-md-3"),
    boots_colMd4("col-md-4"),
    boots_colMd5("col-md-5"),
    boots_colMd6("col-md-6"),
    boots_colMd7("col-md-7"),
    boots_colMd8("col-md-8"),
    boots_colMd9("col-md-9"),
    boots_colMd10("col-md-10"),
    boots_colMd11("col-md-11"),
    boots_colMd12("col-md-12"),
    boots_colLg1("col-lg-1"),
    boots_colLg2("col-lg-2"),
    boots_colLg3("col-lg-3"),
    boots_colLg4("col-lg-4"),
    boots_colLg5("col-lg-5"),
    boots_colLg6("col-lg-6"),
    boots_colLg7("col-lg-7"),
    boots_colLg8("col-lg-8"),
    boots_colLg9("col-lg-9"),
    boots_colLg10("col-lg-10"),
    boots_colLg11("col-lg-11"),
    boots_colLg12("col-lg-12"),
    boots_colXl1("col-xl-1"),
    boots_colXl2("col-xl-2"),
    boots_colXl3("col-xl-3"),
    boots_colXl4("col-xl-4"),
    boots_colXl5("col-xl-5"),
    boots_colXl6("col-xl-6"),
    boots_colXl7("col-xl-7"),
    boots_colXl8("col-xl-8"),
    boots_colXl9("col-xl-9"),
    boots_colXl10("col-xl-10"),
    boots_colXl11("col-xl-11"),
    boots_colXl12("col-xl-12"),
    boots_12_6_4("col-sm-12", listOf(boots_colLg4, boots_colMd6)),

    partyRep("party-republican"),
    partyDem("party-democrat"),
    partyInd("party-independent"),

    trackerIntro("billTracker-intro"),
    trackerHouse("billTracker-house"),
    trackerSenate("billTracker-senate"),
    trackerPresident("billTracker-president"),
    trackerLaw("billTracker-law"),

    bill("bill"),
    card("card"),
    billGridContent("billGridContent"),
    billTitle("billTitle"),
    billSponsor("billSponsor"),
    billStatus("billStatus"),
    billDescription("billDescription"),
    billTopSubject("billTopSubject"),
    billDate("billDate"),
    billExpanded("billExpanded"),
    billStatusDescription("billStatusDescription"),
    billLinkContainer("billLinkContainer"),
    billExpandedSponsorData("billExpandedSponsorData"),
    billTracker("billTracker", listOf(boots_button_group, boots_mr2)),

    billCosponsors("billCosponsors"),
    billExpandedSponsorImg("billExpandedSponsorImg", listOf(boots_card_img_top)),

    voteTable("voteTable"),
    voteCell("voteCell"),
    voteDem("voteDem"),
    voteRep("voteRep"),
    boots_container("container"),

    billExpandedTabContent("billExpandedTabContent"),

    voteInd("voteInd"),
    //CLASS_BILL_(""),
    ;

    val lbl = labelValue
    val additionalClasses = if (addClasses.size > 0) addClasses.joinToString(" ") else ""
    val fullLbl = "$labelValue $additionalClasses"
    override fun label(): String {
        return this.fullLbl
    }

    override fun toString(): String {
        return this.label()
    }
}

class ClassGroup(grouped: List<CssClass>) : CssClass {
    constructor(vararg classes: CssClass) : this(classes.toList())

    private val groupedLabel = grouped.joinToString(" ")
    override fun label(): String {
        return groupedLabel
    }
}

interface CssClass {
    fun label(): String
}

fun cssClass(vararg grouped: Classes): CssClass {
    return ClassGroup(grouped.asList())
}
