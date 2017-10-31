package bz.stew.bracken.ui.pages.browse.model

import bz.stew.bracken.ui.common.model.Model
import bz.stew.bracken.ui.common.index.ALL_INDEX_DEFS
import bz.stew.bracken.ui.common.bill.EasyPoliticsParser
import bz.stew.bracken.ui.common.bill.BillData

/**
 * Created by stew on 4/29/17.
 */
class BillModelEasyPoliticsRest : Model {
    private var indexStale: Boolean = false

    private var bills: Collection<BillData> = listOf()

    override fun loadBillData(data: dynamic, append: Boolean) {
        val newData = EasyPoliticsParser(data).parse(this)
        if (append) {
            bills = bills.plus(newData)
            indexBills(newData)
        } else {
            bills = newData
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