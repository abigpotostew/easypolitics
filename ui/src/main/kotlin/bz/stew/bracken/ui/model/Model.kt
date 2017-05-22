package bz.stew.bracken.ui.model

import bz.stew.bracken.ui.model.types.bill.BillData

/**
 * Created by stew on 1/24/17.
 */
interface Model {
    fun loadBillData(data: dynamic)
    fun getBillData(): List<BillData>
    fun indexData()
    fun getBillById(uniqueId:Int):BillData?
}