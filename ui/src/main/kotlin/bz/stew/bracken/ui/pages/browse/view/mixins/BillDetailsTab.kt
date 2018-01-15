package bz.stew.bracken.ui.pages.browse.view.mixins

import bz.stew.bracken.ui.extension.kotlinx.ac
import bz.stewart.bracken.shared.view.Classes
import bz.stew.bracken.ui.common.view.SubTemplate
import bz.stew.bracken.ui.pages.browse.view.Bill
import kotlinx.html.FlowContent
import kotlinx.html.div
import kotlinx.html.p

/**
 * Created by stew on 7/4/17.
 */
class BillDetailsTab(private val template: Bill) : SubTemplate {
    override fun renderIn(root: FlowContent) {
        val cosponsors = template.billView.billData.cosponsors
        val billSubjects = template.billView.billData.subjects
        val actions = template.billView.billData.actions
        root.div{
            ac(Classes.boots_row)
            div{
                ac(Classes.billCosponsors, Classes.boots_colMd4)
                CosponsorDisplayList(cosponsors).renderIn(this)
            }
            div{
                ac(Classes.boots_colMd4)
                BillSubjectsDisplayList(billSubjects).renderIn(this)
            }
            div{
                ac(Classes.boots_colMd4)
                ActionsList(actions).renderIn(root)
            }
        }
        //todo show the history and amendments of the bill
    }
}