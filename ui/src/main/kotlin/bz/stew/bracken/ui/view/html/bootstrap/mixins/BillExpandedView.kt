package bz.stew.bracken.ui.view.html.bootstrap.mixins

import bz.stew.bracken.ui.extension.kotlinx.ac
import bz.stew.bracken.ui.view.html.Classes
import bz.stew.bracken.ui.view.html.SubTemplate
import bz.stew.bracken.ui.view.html.bootstrap.Bill
import kotlinx.html.DirectLink
import kotlinx.html.HtmlBodyTag
import kotlinx.html.a
import kotlinx.html.div
import kotlinx.html.li
import kotlinx.html.ul

class BillExpandedView(private val template: Bill) : SubTemplate {
    private val billView = template.billView
    override fun renderIn(root: HtmlBodyTag) {
        val sponsor = billView.billData.sponsor
        root.div {
            style = "display:none;"
            ac(Classes.card, Classes.billExpanded)
            div {
                ac(Classes.boots_card_header)
                ul {
                    ac("nav flex-column flex-sm-row nav-pills card-header-pills")
                    set("role", "tablist")
                    var i = 0
                    val tabNames = listOf<String>("Overview", "Contact", "Details", "Pizza")
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
                    BillOverview(template),
                    BillContact(sponsor),
                    BillDetailsTab(template),
                    Paragraph("pizza")
                )
                for (i in 0..3) {
                    div(Classes.boots_tab_card) {
                        id = tabId(i)
                        if (i == 0) {
                            ac("active")
                            set("role", "tabpanel")
                        }
                        div(Classes.boots_container) {
                            ac(Classes.billExpandedTabContent)
                            tabTemplates[i].renderIn(this)
                        }
                    }
                }
            })
        }
    }

    private fun tabId(i: Int): String {
        return "bill-exp-nav-tab$i-${billView.selector().suffix()}"
    }
}