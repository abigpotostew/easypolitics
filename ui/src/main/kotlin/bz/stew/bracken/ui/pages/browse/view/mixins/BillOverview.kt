package bz.stew.bracken.ui.pages.browse.view.mixins

import bz.stew.bracken.ui.extension.kotlinx.ac
import bz.stew.bracken.ui.extension.kotlinx.set
import bz.stew.bracken.ui.common.bill.status.BillStatus
import bz.stew.bracken.ui.util.ui.UIFormatter
import bz.stew.bracken.ui.common.view.Classes
import bz.stew.bracken.ui.common.view.SubTemplate
import bz.stew.bracken.ui.pages.browse.view.Bill
import bz.stewart.bracken.shared.data.MajorStatus
import kotlinx.html.FlowContent
import kotlinx.html.HtmlBlockTag
import kotlinx.html.a
import kotlinx.html.button
import kotlinx.html.div
import kotlinx.html.h5
import kotlinx.html.h6
import kotlinx.html.img
import kotlinx.html.p
import kotlinx.html.span

class BillOverview(private val template: Bill, enableTwitterImg: Boolean = false) : SubTemplate {
    private val showProfileImage = enableTwitterImg

    override fun renderIn(root: FlowContent) {
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
        root.div {
            ac(Classes.boots_row)
            div {
                ac(Classes.boots_col)
                h6 {
                    ac(Classes.billStatus, Classes.boots_card_text,
                            Classes.boots_card_subtitle)
                    +billTypeNiceFormat
                    +": "
                    +name
                }
                div {
                    //todo make this an h7
                    ac(Classes.billStatus, Classes.boots_card_text,
                            Classes.boots_card_subtitle)
                    +"Sponsor: "
                    +sponsorName
                }
                p {
                    ac(Classes.billDate, Classes.boots_card_text)
                    +"Introduced "
                    +introDate
                }
                p {
                    ac(Classes.billStatusDescription)
                    +statusDescr
                }
                p {
                    ac(Classes.billLinkContainer)
                    a {
                        target = "_blank"
                        href = link
                        +"View on official data source"
                    }
                }
                //todo the tracker thing here
                subtemplate.buildTracker(this)
            }
            div("col-3") {
                div {
                    ac(Classes.card, Classes.billExpandedSponsorData)
                    if (showProfileImage) {
                        img {
                            ac(Classes.billExpandedSponsorImg)
                            alt = "Bill sponsor"
                            src = billSponsorProfileImg
                        }
                    } else {
                        span {
                            ac(Classes.billExpandedSponsorImg)
                        }
                    }
                    div {
                        ac(Classes.boots_card_block)
                        h5 {
                            ac(Classes.billSponsor, Classes.boots_card_title)
                            +sponsorName
                        }
                        p {
                            ac(Classes.boots_card_text)
                            +"pizza"
                        }

                    }
                }
            }
        }
    }

    private fun buildTracker(root: HtmlBlockTag) {
        val lastMajorStatus = template.billView.billData.currentStatus.lastMajorStatus()
        //val lastMajorStatusIdx = lastMajorStatus.lowercaseName()

        val displayList = listOf(MajorStatus.INTRODUCED, MajorStatus.PASSED_HOUSE, MajorStatus.PASSED_SENATE,
                MajorStatus.SIGNED_PRESIDENT, MajorStatus.LAW)
        root.div {
            ac(Classes.billTracker)
            set("role", "group")
            (displayList).forEach {
                button {
                    ac(Classes.boots_secondary_button, mapMajorStatusClass(it))
                    if (it == lastMajorStatus) {
                        setMostRecentTrackerButton(this)//set("style", "background-color:yellow;")
                    }
                    +it.niceFormat()
                }
            }

        }
    }

    private fun setMostRecentTrackerButton(el: HtmlBlockTag) {
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