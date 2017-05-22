package bz.stew.bracken.ui.view.html.bootstrap

import bz.stew.bracken.ui.view.html.Templates
import bz.stew.bracken.ui.view.item.BillViewItem

/**
 * Created by stew on 3/5/17.
 */
class BootstrapTemplates : Templates {
   override fun renderBill(bill: BillViewItem): String {
      return Bill(bill).render()
   }
}