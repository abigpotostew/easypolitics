package bz.stew.bracken.ui.model

import bz.stew.bracken.ui.model.index.ALL_INDEX_DEFS
import bz.stew.bracken.ui.model.parse.bill.EasyPoliticsParser
import bz.stew.bracken.ui.model.types.bill.BillData

/**
 * Created by stew on 4/29/17.
 */
class BillModelEasyPoliticsRest : Model {
   private var bills: Collection<BillData> = listOf()


   override fun loadBillData(data: dynamic) {
      bills = EasyPoliticsParser(data).parse(this)
   }

   override fun getBillData(): List<BillData> {
      return bills.toList()
   }

   override fun indexData() {
      for (idx in ALL_INDEX_DEFS) {
         idx.indexInstances(this.bills)
      }
   }

   override fun getBillById(uniqueId: Int): BillData? {
      TODO()
   }

}