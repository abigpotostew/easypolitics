package bz.stew.bracken.ui.common.model

import bz.stew.bracken.ui.common.bill.BillData

/**
 * Created by stew on 1/24/17.
 */
interface Model<T : ModelItem> {
    /**
     * Load data into model. if append is false, replace the current model data with this. When true, add
     * the data.
     */
    fun loadBillData(data: Collection<T>, append: Boolean)

    fun getBillData(): List<BillData>

    fun getBillById(uniqueId: Int): BillData?
}