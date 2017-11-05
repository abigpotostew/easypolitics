package bz.stew.bracken.ui.pages.singlebill.view

import bz.stew.bracken.ui.extension.kotlinx.div
import bz.stew.bracken.ui.common.view.BillViewItem
import bz.stew.bracken.view.HtmlSelector
import bz.stew.bracken.view.View
import kotlinx.html.dom.createTree
import kotlinx.html.h4
import kotlinx.html.id
import kotlin.browser.document

class SingleBillView(rootElmt: HtmlSelector) :View(rootElmt) {

    fun constructBillView(bill: BillViewItem) {
        document.createTree().div{
            id=bill.billData.officialId()
            h4 {
                bill.trueTitle()
            }

        }
    }


}