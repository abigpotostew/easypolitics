package bz.stewart.bracken.web.view.browse

import bz.stewart.bracken.shared.view.Classes
import bz.stewart.bracken.web.extension.ac
import bz.stewart.bracken.web.html.ViewRender
import bz.stewart.bracken.web.service.WebPageContext
import bz.stewart.bracken.web.view.BillsMultiView
import bz.stewart.bracken.web.view.ContentRoot
import kotlinx.html.*

class BrowseBillsView : ViewRender {
    override fun renderIn(parent: FlowContent, context: WebPageContext) {
        parent.span {
            ac(Classes.boots_card)
            val filters = listOf(PartyFilterForm(), FixedStatusFilterForm(), IntroDateFilter(), MajorActionFilterForm(), NextQueryFilterForm(), CountBillsFilterForm())
            ul {
                ac(Classes.BROWSE_FILTERS_LIST)
                style = "display: inline-block;"
                for (filter in filters) {
                    li {
                        filter.renderIn(this, context)
                    }
                }
            }
        }
        parent.div {
            ContentRoot(BillsMultiView()).renderIn(this, context)
        }
    }
}

