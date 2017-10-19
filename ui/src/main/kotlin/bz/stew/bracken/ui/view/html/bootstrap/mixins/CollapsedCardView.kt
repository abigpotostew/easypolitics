package bz.stew.bracken.ui.view.html.bootstrap.mixins

import bz.stew.bracken.ui.extension.kotlinx.ac
import bz.stew.bracken.ui.util.ui.UIFormatter
import bz.stew.bracken.ui.view.html.Classes
import bz.stew.bracken.ui.view.html.SubTemplate
import bz.stew.bracken.ui.view.item.BillViewItem
import kotlinx.html.FlowContent
import kotlinx.html.div
import kotlinx.html.h4
import kotlinx.html.h6
import kotlinx.html.p
import kotlinx.html.small

class CollapsedCardView(private val billView: BillViewItem) : SubTemplate {
    override fun renderIn(root: FlowContent) {
        val billView = this.billView
        val bd = billView.billData
        //val sponsorName = bd.sponsor.getOfficialName()
        val introDate = UIFormatter.prettyDate(bd.intro_date)
        val lastUpdatedDateString = UIFormatter.prettyDate(bd.lastUpdatedDate())
        //val status: BillStatus = bd.currentStatus
        //val statusLabel: String = status.label()

        root.div {
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
                    +bd.sponsor.getFullTitle()
                }
                p {
                    ac(Classes.billDescription, Classes.boots_card_text)
                    +billView.trueTitle()
                }
                h6 {
                    ac(Classes.billTopSubject, Classes.boots_card_subtitle, Classes.boots_card_text, Classes.boots_text_muted)
                    +bd.subjectsTopTerm
                }
            }
            div {
                ac(Classes.boots_card_footer, Classes.boots_row)
                div {
                    ac(Classes.boots_col_md_6)
                    small {
                        ac(Classes.billDate, Classes.boots_text_muted)
                        +"Introduced: "
                        +introDate
                    }
                }
                div {
                    ac(Classes.boots_col_md_6, Classes.boots_text_right)
                    small {
                        ac(Classes.billDate, Classes.boots_text_muted)
                        +"Updated: "
                        +lastUpdatedDateString
                    }
                }
            }
        }
    }
}