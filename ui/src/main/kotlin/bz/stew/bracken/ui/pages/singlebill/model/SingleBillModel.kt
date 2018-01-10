package bz.stew.bracken.ui.pages.singlebill.model

import bz.stew.bracken.ui.common.bill.BillData
import bz.stew.bracken.ui.common.model.Model

class SingleBillModel : Model<BillData> {

   private var singleBill: BillData? = null

   override fun loadBillData(data: Collection<BillData>, append: Boolean) {
      if (data.isEmpty()) {
         console.warn("SingleBillModel: bills collection is empty")
         return
      }
      singleBill = data.iterator().next()
   }

   override fun getBillData(): List<BillData> {
      val theBill = this.singleBill
      return if (theBill != null) {
         listOf(theBill)
      } else {
         emptyList()
      }
   }

   override fun getBillById(uniqueId: Int): BillData? {
      TODO("getBillById not implemented")
   }
}