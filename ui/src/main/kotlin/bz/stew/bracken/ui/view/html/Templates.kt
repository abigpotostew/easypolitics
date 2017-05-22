package bz.stew.bracken.ui.view.html

import bz.stew.bracken.ui.view.item.BillViewItem

/**
 * Created by stew on 3/5/17.
 */
interface Templates {
   fun renderBill(bill: BillViewItem): String
}