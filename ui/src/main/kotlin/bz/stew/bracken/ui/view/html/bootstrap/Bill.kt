package bz.stew.bracken.ui.view.html.bootstrap

import bz.stew.bracken.ui.extension.kotlinx.ac
import bz.stew.bracken.ui.extension.kotlinx.horzizontalDescriptionList
import bz.stew.bracken.ui.model.types.bill.status.BillStatus
import bz.stew.bracken.ui.util.ui.UIFormatter
import bz.stew.bracken.ui.view.html.*
import bz.stew.bracken.ui.view.html.bootstrap.mixins.*
import bz.stew.bracken.ui.view.item.BillViewItem
import bz.stewart.bracken.shared.data.MajorStatus
import bz.stewart.bracken.shared.data.party.Party
import kotlinx.html.*

/**
 * Generates the html structure and data for a bill
 * Created by stew on 3/5/17.
 */

class Bill(val billView: BillViewItem) : Template {

   private fun mapPartyClass(party: Party): Classes {
      return when (party) {
         Party.DEMOCRAT -> Classes.partyDem
         Party.REPUBLICAN -> Classes.partyRep
         Party.INDEPENDENT -> Classes.partyInd
         else -> Classes.EMPTY
      }
   }

   override fun render(): HtmlRenderOutput {
      val template = this

      val billId = billView.selector().suffix()
      val bd = billView.billData
      val sponsor = bd.sponsor
      val sponsorName = bd.sponsor.getOfficialName()

      val introDate = UIFormatter.prettyDate(bd.intro_date)
      val lastUpdatedDateString = UIFormatter.prettyDate(bd.lastUpdatedDate())
      val status: BillStatus = bd.status
      val statusLabel: String = status.label()
      val shortTitle: String = bd.shortTitle
      val sponsorParty = bd.sponsor.getParty()
      val cosponsors = bd.cosponsors

      val gen = html {
         div(Classes.bill, {
            id = billId
            ac(mapPartyClass(sponsorParty))

            div {
               //todo make class groups tied to object model
               ac(Classes.card, Classes.billGridContent, Classes.boots_hvr_fade)
               h4 {
                  ac(Classes.billTitle, Classes.boots_card_header)
                  +billView.shortLabel()
               }
               div {
                  ac(Classes.boots_card_block)
                  h6 {
                     ac(Classes.billSponsor, Classes.boots_card_text,
                           Classes.boots_card_subtitle)
                     +sponsorName
                  }
//                  h7 {
//                     ac(Classes.billStatus, Classes.boots_card_text,
//                        Classes.boots_card_subtitle)
//                     +statusLabel
//                  }
                  p {
                     ac(Classes.billDescription, Classes.boots_card_text,
                           Classes.css_ml_ellipses)
                     +billView.trueTitle()
                  }
               }
               div {
                  ac(Classes.boots_card_footer, Classes.boots_row)
                  div {
                     ac(Classes.boots_col_md_6)
                     small {
                        ac(Classes.billDate, Classes.boots_text_muted)
                        +introDate
                     }
                  }
                  div {
                     ac(Classes.boots_col_md_6, Classes.boots_text_right)
                     small {
                        ac(Classes.billDate, Classes.boots_text_muted)
                        +lastUpdatedDateString
                     }
                  }
               }
            }
            //expanded section
            div {
               style = "display:none;"
               ac(Classes.card, Classes.billExpanded)
               div {
                  ac(Classes.boots_card_header)
                  ul {
                     ac("nav flex-column flex-sm-row nav-pills card-header-pills")
                     set("role", "tablist")
                     var i = 0
                     val tabNames = listOf<String>("Overview", "Contact", "Details",
                           //"Votes",
                           "Text")
                     (0..3).forEach {
                        li {
                           ac("flex-sm-fill text-sm-center nav-item")
                           a {
                              val id = tabId(i)
                              ac("nav-link " + id)
                              if (i == 0) {
                                 ac("active")//only first tab is active
                              }
                              set("data-toggle", "tab")
                              set("role", "tab")
                              href = DirectLink("#" + id)
                              +tabNames[i]
                           }
                        }
                        ++i
                     }

                  }
               }
               div(Classes.boots_tab_content, {
                  val tabTemplates = arrayListOf<SubTemplate>(
                         BillOverview(template) ,
                         BillContact(sponsor),
                         //detailsTabContent(it)
                           BillDetailsTab(template)
                        ,
                         Paragraph("pizza")
                  )
                  for (i in 0..3) {
                     div(Classes.boots_tab_card) {
                        id = tabId(i)
                        if (i == 0) {
                           ac("active")
                           set("role", "tabpanel")
                        }
                        div(Classes.boots_container){
                           ac(Classes.billExpandedTabContent)
                           tabTemplates[i].renderIn(this)
                        }
                     }
                  }
               })
            }
         })
      }

      //todo tracker
      //todo link
      return StandardHtmlRenderOutput(gen)
   }

   private fun tabId(i: Int): String {
      return "bill-exp-nav-tab$i-${billView.selector().suffix()}"
   }
}

private fun HtmlBodyTag.contactTabContent(template: Bill) {
   val sponsor = template.billView.billData.sponsor
   LegislatorProfile(sponsor).renderIn(this)
}


private fun HtmlBodyTag.textTabContent(template: Bill) {

   +"pizza"
}

//todo, there will be lots of these map to css class functions, need a place to put them
