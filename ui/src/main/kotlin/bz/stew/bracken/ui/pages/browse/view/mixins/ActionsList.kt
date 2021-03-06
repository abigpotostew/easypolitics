package bz.stew.bracken.ui.pages.browse.view.mixins

import bz.stew.bracken.ui.common.bill.BillAction
import bz.stew.bracken.ui.common.view.SubTemplate
import bz.stew.bracken.ui.extension.kotlinx.DlTitleFunc
import bz.stew.bracken.ui.extension.kotlinx.HtmlFunc
import bz.stew.bracken.ui.extension.kotlinx.horzizontalDescriptionList
import bz.stew.bracken.ui.util.date.DateFormatter
import kotlinx.html.FlowContent
import kotlinx.html.p

class ActionsList(private val actionsList: Set<BillAction>) : SubTemplate {
    override fun renderIn(root: FlowContent) {
        val descriptionListMap: MutableMap<DlTitleFunc, HtmlFunc> = mutableMapOf()

        for (action in actionsList) {
            descriptionListMap.put({
                it.text(DateFormatter.prettyDate(action.actedAtDate()))
            }, {
                it.p {
                    +action.getText()
                }
            })
        }
        root.horzizontalDescriptionList(descriptionListMap)
    }
}