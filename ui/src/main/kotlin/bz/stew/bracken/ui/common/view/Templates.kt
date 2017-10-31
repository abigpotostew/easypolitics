package bz.stew.bracken.ui.common.view

import bz.stew.bracken.ui.pages.browse.view.BillViewItem

/**
 * Created by stew on 3/5/17.
 */
interface Templates {
   fun renderBill(bill: BillViewItem): HtmlRenderOutput
}