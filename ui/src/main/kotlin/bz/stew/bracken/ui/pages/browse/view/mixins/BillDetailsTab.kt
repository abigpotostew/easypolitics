package bz.stew.bracken.ui.pages.browse.view.mixins

import bz.stew.bracken.ui.extension.kotlinx.ac
import bz.stew.bracken.ui.common.view.Classes
import bz.stew.bracken.ui.common.view.SubTemplate
import bz.stew.bracken.ui.pages.browse.view.Bill
import kotlinx.html.FlowContent
import kotlinx.html.p

/**
 * Created by stew on 7/4/17.
 */
class BillDetailsTab(private val template: Bill) : SubTemplate {
    override fun renderIn(root: FlowContent) {
        val cosponsors = template.billView.billData.cosponsors
        val billSubjects = template.billView.billData.subjects
        val actions = template.billView.billData.actions
        root.p {
            ac(Classes.billCosponsors)
            CosponsorDisplayList(cosponsors).renderIn(this)

            BillSubjectsDisplayList(billSubjects).renderIn(this)

            ActionsList(actions).renderIn(root)
        }
        //todo show the history and amendments of the bill
    }
}