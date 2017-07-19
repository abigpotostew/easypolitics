package bz.stew.bracken.ui.view.html

/**
 * Created by stew on 3/5/17.
 */

enum class Classes(labelValue:String, addClasses: List<Classes> = emptyList()):CssClass {

   EMPTY(""),

   boots_hvr_fade("hvr-fade"),
   boots_card_header("card-header"),
   boots_card_block("card-block"),
   boots_card_text("card-text"),
   boots_card_subtitle("card-subtitle"),
   boots_card_title("card-title"),
   boots_card_footer("card-footer"),
   boots_card_img_top("card-img-top"),
   boots_tab_pane("tab-pane"),
   boots_tab_card("", listOf(boots_card_block,boots_tab_pane)),

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

   boots_dlHorizontal("row"),
   boots_dlHorizontalDt_small("col-sm-3"),
   boots_dlHorizontalDd_small("col-sm-9"),
   boots_colSm4("col-sm-4"),
   boots_colLg4("col-lg-4"),
   boots_colMd4("col-md-4"),
   boots_colMd6("col-md-6"),
   boots_4_6_12("col-sm-12", listOf(boots_colLg4, boots_colMd6)),


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
   val additionalClasses = if (addClasses.size>0) addClasses.joinToString(" ") else ""
   val fullLbl = "$labelValue $additionalClasses"
   override fun label(): String {
      return this.fullLbl
   }
   override fun toString(): String {
      return this.label()
   }
}

class ClassGroup(grouped :List<Classes>): CssClass{
   val groupedLabel = grouped.joinToString(" ")
   override fun label(): String {
      return groupedLabel
   }
}

fun cssClass(vararg grouped:Classes):CssClass{
   return ClassGroup(grouped.asList())
}

interface CssClass{
   fun label():String
}

