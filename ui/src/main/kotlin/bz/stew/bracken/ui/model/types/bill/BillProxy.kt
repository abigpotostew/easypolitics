package bz.stew.bracken.ui.model.types.bill

import bz.stew.bracken.ui.model.Model
import bz.stew.bracken.ui.model.ModelItem

/**
 * Created by stew.bracken on 2/22/17.
 */

class BillProxy(val uniqueId: Int, val context: Model) {
   fun getActualBill(): ModelItem {
      return context.getBillById(uniqueId)!!
   }
}