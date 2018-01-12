package bz.stew.bracken.ui.pages.singlebill.view

import bz.stew.bracken.ui.common.view.BillViewItem
import bz.stew.bracken.ui.extension.kotlinx.div
import bz.stew.bracken.ui.pages.browse.view.mixins.BillOverview
import bz.stew.bracken.view.View
import kotlinx.html.dom.createTree
import kotlinx.html.h4
import kotlinx.html.id
import kotlin.browser.document

class SingleBillView : View() {

   fun constructBillView(bill: BillViewItem) {
      this.element = document.createTree().div {
         id = bill.billData.officialId()
         BillOverview(bill).renderIn(this)
      }
   }

   fun showBillNotFound(billId: String) {
      this.element = document.createTree().div {
         h4 {
            +"Sorry, a bill '$billId' could not be found. Try again using format: '[bill type abbreviated][bill number]-[congress number]'."
         }
      }
   }
}