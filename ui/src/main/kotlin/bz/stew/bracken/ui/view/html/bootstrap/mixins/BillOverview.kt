package bz.stew.bracken.ui.view.html.bootstrap.mixins

import bz.stew.bracken.ui.extension.kotlinx.ac
import bz.stew.bracken.ui.model.types.bill.status.BillStatus
import bz.stew.bracken.ui.util.ui.UIFormatter
import bz.stew.bracken.ui.view.html.Classes
import bz.stew.bracken.ui.view.html.SubTemplate
import bz.stew.bracken.ui.view.html.bootstrap.Bill
import bz.stew.bracken.ui.view.html.cssClass
import bz.stewart.bracken.shared.data.MajorStatus
import kotlinx.html.DirectLink
import kotlinx.html.HtmlBodyTag
import kotlinx.html.a
import kotlinx.html.button
import kotlinx.html.div
import kotlinx.html.h5
import kotlinx.html.h6
import kotlinx.html.h7
import kotlinx.html.img
import kotlinx.html.p
import kotlinx.html.span

class BillOverview(private val template: Bill, enableTwitterImg: Boolean = false) : SubTemplate {
    private val showProfileImage = enableTwitterImg
    override fun renderIn(root: HtmlBodyTag) {
        val subtemplate = this
        val billView = template.billView
        val bd = billView.billData
        val sponsor = bd.sponsor
        val sponsorName = sponsor.getFullTitle(true)
        val name = bd.officialTitle
        val introDate = UIFormatter.prettyDate(bd.intro_date)
        val status: BillStatus = bd.currentStatus
        val statusDescr: String = status.description()
        val billSponsorProfileImg = billView.sponsorImageUrl()
        val link: String = bd.link
        val billTypeNiceFormat = bd.officialId(true)

        //div(Classes.boots_container, {
        root.div(Classes.boots_row, {
            div(Classes.boots_col, {
                h6 {
                    ac(Classes.billStatus, Classes.boots_card_text,
                        Classes.boots_card_subtitle)
                    +billTypeNiceFormat
                    +": "
                    +name
                }
                h7 {
                    ac(Classes.billStatus, Classes.boots_card_text,
                        Classes.boots_card_subtitle)
                    +"Sponsor: "
                    +sponsorName
                }
                p(cssClass(Classes.billDate, Classes.boots_card_text), {
                    +"Introduced "
                    +introDate
                })
                p(Classes.billStatusDescription, {
                    +statusDescr
                })
                p(Classes.billLinkContainer, {
                    a {
                        target = "_blank"
                        href = DirectLink(link)
                        +"View on official data source"
                    }
                })
                //todo the tracker thing here
                subtemplate.buildTracker(this)
            })
            div("col-3", {
                div(cssClass(Classes.card, Classes.billExpandedSponsorData), {
                    if (showProfileImage) {
                        img(Classes.billExpandedSponsorImg, {
                            alt = "Bill sponsor"
                            src = DirectLink(billSponsorProfileImg)
                        })
                    } else {
                        span {
                            ac(Classes.billExpandedSponsorImg)
                        }
                    }
                    div(Classes.boots_card_block, {
                        h5(cssClass(Classes.billSponsor, Classes.boots_card_title), {
                            +sponsorName
                        })
                        p(Classes.boots_card_text, {
                            +"pizza"
                        })

                    })
                })
            })
        })
    }

    private fun buildTracker(root: HtmlBodyTag) {
        val lastMajorStatus = template.billView.billData.currentStatus.lastMajorStatus()
        //val lastMajorStatusIdx = lastMajorStatus.lowercaseName()

        val displayList = listOf(MajorStatus.INTRODUCED, MajorStatus.PASSED_HOUSE, MajorStatus.PASSED_SENATE,
            MajorStatus.SIGNED_PRESIDENT, MajorStatus.LAW)
        root.div(Classes.billTracker, {
            set("role", "group")
            (displayList).forEach {
                button(cssClass(Classes.boots_secondary_button, mapMajorStatusClass(it)), {
                    if (it == lastMajorStatus) {
                        setMostRecentTrackerButton(this)//set("style", "background-color:yellow;")
                    }
                    +it.niceFormat()
                })
            }

        })
    }

    private fun setMostRecentTrackerButton(el: HtmlBodyTag) {
        el.set("style", "background-color:yellow;")
    }

    private fun mapMajorStatusClass(status: MajorStatus): Classes {
        return when (status) {
            MajorStatus.INTRODUCED -> Classes.trackerIntro
            MajorStatus.PASSED_HOUSE -> Classes.trackerHouse
            MajorStatus.PASSED_SENATE -> Classes.trackerSenate
            MajorStatus.SIGNED_PRESIDENT -> Classes.trackerPresident
            MajorStatus.LAW -> Classes.trackerLaw
            else -> Classes.EMPTY
        }
    }
}