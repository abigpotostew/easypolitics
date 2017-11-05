package bz.stew.bracken.ui.pages.browse.view

import bz.stew.bracken.ui.common.view.BillViewItem
import bz.stew.bracken.ui.common.view.HtmlRenderOutput
import bz.stew.bracken.ui.common.view.Templates

/**
 * Created by stew on 3/5/17.
 */
class BootstrapTemplates : Templates {
   override fun renderBill(bill: BillViewItem): HtmlRenderOutput {
      return Bill(bill).render()
   }
}