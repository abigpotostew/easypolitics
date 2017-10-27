package bz.stew.bracken.ui.view.html.bootstrap.mixins

import bz.stew.bracken.ui.extension.kotlinx.ac
import bz.stew.bracken.ui.extension.kotlinx.set
import bz.stew.bracken.ui.view.html.Classes
import bz.stew.bracken.ui.view.html.SubTemplate
import bz.stew.bracken.ui.view.html.bootstrap.Bill
import kotlinx.html.FlowContent
import kotlinx.html.a
import kotlinx.html.div
import kotlinx.html.id
import kotlinx.html.li
import kotlinx.html.style
import kotlinx.html.ul

class BillExpandedView(private val template: Bill) : SubTemplate {
    private val billView = template.billView
    override fun renderIn(root: FlowContent) {
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
                                id = tabLabelId(i)
                                val tabContentId = tabId(i)
                                ac("nav-link ")// + id)//pizza
                                this.attributes.put("aria-controls", tabContentId)
                                if (i == 0) {
                                    ac("active")//only first tab is active
                                }
                                set("data-toggle", "tab")
                                set("role", "tab")
                                href = "#" + tabContentId
                                +tabNames[i]
                            }
                        }
                        ++i
                    }
                }
            }
            div {
                ac(Classes.boots_tab_content)
                val tabTemplates = arrayListOf<SubTemplate>(
                        BillOverview(template),
                        BillContact(sponsor),
                        BillDetailsTab(template),
                        Paragraph("pizza")
                )
                for (i in 0..3) {
                    div {
                        ac(Classes.boots_card_block, Classes.boots_tab_pane)
                        id = tabId(i)
                        attributes.put("aria-labelledby", tabLabelId(i))
                        if (i == 0) {
                            ac("active")
                            set("role", "tabpanel")
                        }
                        div {
                            ac(Classes.boots_container, Classes.billExpandedTabContent)
                            tabTemplates[i].renderIn(this)
                        }
                    }
                }
            }
        }
    }

    private fun tabLabelId(i: Int): String {
        return "bill-exp-nav-label-tab$i-${billView.selector().suffix()}"
    }

    private fun tabId(i: Int): String {
        return "bill-exp-nav-tab$i-${billView.selector().suffix()}"
    }
}