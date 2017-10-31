package bz.stew.bracken.ui.pages.browse.model

import bz.stew.bracken.ui.common.model.Model
import bz.stew.bracken.ui.common.index.ALL_INDEX_DEFS
import bz.stew.bracken.ui.common.bill.BillData

/**
 * Created by stew on 4/29/17.
 */
class BillModelEasyPoliticsRest : Model<BillData> {
    private var indexStale: Boolean = false

    private var bills: Collection<BillData> = listOf()

    override fun loadBillData(data: Collection<BillData>, append: Boolean) {
        if (append) {
            bills = bills.plus(data)
            indexBills(data)
        } else {
            bills = data
            indexData()
        }
    }

    override fun getBillData(): List<BillData> {
        return bills.toList()
    }

    private fun indexBills(bills: Collection<BillData>) {
        for (idx in ALL_INDEX_DEFS) {
            idx.indexInstances(bills)
        }
    }

    private fun indexData() {
        indexBills(this.bills)
        indexStale = false
    }

    override fun getBillById(uniqueId: Int): BillData? {
        TODO()
    }

}