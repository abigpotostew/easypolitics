package bz.stew.bracken.ui.common.model

import bz.stew.bracken.ui.common.bill.BillData

/**
 * Created by stew on 1/24/17.
 */
interface Model {
   /**
    * Parse json data and load into model. if append is false, replace the current model data with this. When true, add
    * the data.
    */
   fun loadBillData(data: dynamic, append: Boolean)

   fun getBillData(): List<BillData>
   //fun indexData()
   fun getBillById(uniqueId: Int): BillData?
   //fun invalidateIndex()
}