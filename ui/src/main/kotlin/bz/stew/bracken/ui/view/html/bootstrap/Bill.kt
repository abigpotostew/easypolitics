package bz.stew.bracken.ui.view.html.bootstrap

import bz.stew.bracken.ui.extension.kotlinx.ac
import bz.stew.bracken.ui.model.types.bill.status.BillStatus
import bz.stew.bracken.ui.util.ui.UIFormatter
import bz.stew.bracken.ui.view.html.Classes
import bz.stew.bracken.ui.view.html.Template
import bz.stew.bracken.ui.view.html.cssClass
import bz.stew.bracken.ui.view.item.BillViewItem
import bz.stewart.bracken.shared.data.MajorStatus
import bz.stewart.bracken.shared.data.party.Party
import kotlinx.html.*

/**
 * Created by stew on 3/5/17.
 */

class Bill(val billView: BillViewItem): Template(){

   private fun mapPartyClass(party:Party):Classes{
      return when(party){
         Party.DEMOCRAT->Classes.partyDem
         Party.REPUBLICAN->Classes.partyRep
         Party.INDEPENDENT->Classes.partyInd
         else -> Classes.EMPTY
      }
   }

   override fun render(): String {
      val template = this

      val billId = billView.selector().suffix()
      val bd = billView.billData
      val sponsorName = bd.sponsor.getOfficialName()
      val name = bd.title
      val introDate = UIFormatter.prettyDate(bd.intro_date)
      val status: BillStatus = bd.status
      val statusLabel: String = status.label()
      val statusDescr: String = status.description()
      val link: String = bd.link
      val sponsorParty = bd.sponsor.getParty() //TODO this is kinda janky using as a direct css identifier :(
      val billSponsorProfileImg = billView.sponsorImageUrl()

      val gen = html {
         div (Classes.bill,{
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
                  h7 {
                     ac(Classes.billStatus, Classes.boots_card_text,
                        Classes.boots_card_subtitle)
                     +statusLabel
                  }
                  p {
                     ac(Classes.billDescription, Classes.boots_card_text,
                        Classes.css_ml_ellipses)
                     +name
                  }
               }
               div {
                  ac(Classes.boots_card_footer)
                  small {
                     ac(Classes.billDate, Classes.boots_text_muted)
                     +introDate
                  }
               }
            }
            //expanded section
            div {
               style = "display:none;"
               ac(Classes.card, Classes.billExpanded)
               div{
                  ac(Classes.boots_card_header)
                  ul {
                     ac("nav nav-tabs  flex-column flex-sm-row card-header-tabs")
                     set("role","tablist")
                     var i = 0
                     val tabs = listOf<String>("Bill","Contact","Related Bills","Pizza")
                     (0..3).forEach {
                        li {
                           ac("flex-sm-fill text-sm-center nav-item")
                           a{
                              val id = tabId(i)
                              ac("nav-link "+id)
                              if(i==0){
                                 ac("active")//only first tab is active
                              }
                              set("data-toggle","tab")
                              set("role","tab")
                              href = DirectLink("#"+id)
                              +tabs[i]
                           }
                        }
                        ++i
                     }

                  }
               }
               div(Classes.boots_tab_content,{
                  div {
                     ac("tab-pane active card-block")
                     set("role","tabpanel")
                     id = tabId(0)
                     div(Classes.boots_container_fluid,{
                        div(Classes.boots_row,{
                           div(Classes.boots_col,{
                              h6 {
                                 ac(Classes.billStatus, Classes.boots_card_text,
                                    Classes.boots_card_subtitle)
                                 +sponsorName
                              }
                              p (cssClass(Classes.billDescription, Classes.boots_card_text),{
                                 +name
                              })
                              p (cssClass(Classes.billDate, Classes.boots_card_text),{
                                 +introDate
                              })
                              p (Classes.billStatusDescription,{
                                 +statusDescr
                              })
                              p (Classes.billLinkContainer,{
                                 a {
                                    target = "_blank"
                                    href = DirectLink(link)
                                    +"View on GovTrack.us"
                                 }
                              })
                              //todo the tracker thing here
                              buildTracker(template)
                           })
                           div("col-3",{
                              div(cssClass(Classes.card, Classes.billExpandedSponsorData),{
                                 img(Classes.billExpandedSponsorImg, {
                                    alt = "Bill sponsor"
                                    src = DirectLink(billSponsorProfileImg)
                                 })
                                 div(Classes.boots_card_block,{
                                    h5(cssClass(Classes.billSponsor, Classes.boots_card_title),{
                                       +sponsorName
                                    })
                                    p(Classes.boots_card_text,{
                                       +"pizza"
                                    })

                                 })
                              })
                           })
                        })
                     })
                  }
                  div(Classes.boots_tab_card,{
                     id = tabId(1)
                     +"pizza1"
                  })
                  div(Classes.boots_tab_card,{
                     id = tabId(2)
                     +"pizza2"
                  })
                  div(Classes.boots_tab_card,{
                     id = tabId(3)
                     +"pizza3"
                  })
               })
            }
         })
      }

      //todo tracker
      //todo link
      return gen
   }

   private fun tabId(i:Int):String{
      return "bill-exp-nav-tab$i-${billView.selector().suffix()}"
   }
}

//Do i have to make this an extension function??
private fun HtmlBodyTag.buildTracker(template: Bill){
   val lastMajorStatus = template.billView.billData.status.lastMajorStatus()
   //val lastMajorStatusIdx = lastMajorStatus.lowercaseName()

   val displayList = listOf(MajorStatus.INTRODUCED, MajorStatus.PASSED_HOUSE, MajorStatus.PASSED_SENATE,
                            MajorStatus.SIGNED_PRESIDENT, MajorStatus.LAW)
   div (Classes.billTracker, {
      set("role", "group")
      (displayList).forEach {
         button (cssClass(Classes.boots_secondary_button,mapMajorStatusClass(it)), {
            if(it==lastMajorStatus){
               setMostRecentTrackerButton()//set("style", "background-color:yellow;")
            }
            +it.niceFormat()
         })
      }

   })
}

private inline fun HtmlBodyTag.setMostRecentTrackerButton(){
   set("style", "background-color:yellow;")
}

//todo, there will be lots of these map to css class functions, need a place to put them
private fun mapMajorStatusClass(status: MajorStatus):Classes{
   return when (status) {
      MajorStatus.INTRODUCED -> Classes.trackerIntro
      MajorStatus.PASSED_HOUSE -> Classes.trackerHouse
      MajorStatus.PASSED_SENATE -> Classes.trackerSenate
      MajorStatus.SIGNED_PRESIDENT -> Classes.trackerPresident
      MajorStatus.LAW -> Classes.trackerLaw
      else -> Classes.EMPTY
   }
}