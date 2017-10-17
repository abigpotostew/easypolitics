package bz.stew.bracken.ui.view.html.bootstrap

import bz.stew.bracken.ui.extension.kotlinx.div
import bz.stew.bracken.ui.view.html.Classes
import bz.stew.bracken.ui.view.html.HtmlRenderOutput
import bz.stew.bracken.ui.view.html.Template
import bz.stew.bracken.ui.view.html.bootstrap.mixins.BillExpandedView
import bz.stew.bracken.ui.view.html.bootstrap.mixins.CollapsedCardView
import bz.stew.bracken.ui.view.item.BillViewItem
import bz.stewart.bracken.shared.data.party.Party
import kotlinx.html.dom.createTree
import kotlinx.html.id
import kotlin.browser.document

/**
 * Generates the html structure with content for a bill using bootstrap
 * Created by stew on 3/5/17.
 */
class Bill(val billView: BillViewItem) : Template {

    private fun mapPartyClass(party: Party): Classes {
        return when (party) {
            Party.DEMOCRAT -> Classes.partyDem
            Party.REPUBLICAN -> Classes.partyRep
            Party.INDEPENDENT -> Classes.partyInd
            else -> Classes.EMPTY
        }
    }

    override fun render(): HtmlRenderOutput {
        val template = this

        val billId = billView.selector().suffix()
        val bd = billView.billData

        val sponsorParty = bd.sponsor.getParty()


//        document.createTree().ul {
//            li {
//
//            }
//        }

        val gen = document.createTree().div(Classes.bill, mapPartyClass(sponsorParty)) {
            id = billId
            CollapsedCardView(billView).renderIn(this)
            //expanded section
            BillExpandedView(template).renderIn(this)
        }


//        val gen =
//
//            appendHTML(false).div(Classes.bill, {
//                id = billId
//                ac(mapPartyClass(sponsorParty))
//
//                CollapsedCardView(billView).renderIn(this)
//                //expanded section
//                BillExpandedView(template).renderIn(this)
//            })


        return StandardHtmlRenderOutput(gen)
    }
}