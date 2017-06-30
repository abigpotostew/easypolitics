package bz.stew.bracken.ui.model

import bz.stew.bracken.ui.model.index.ALL_INDEX_DEFS
import bz.stew.bracken.ui.model.parse.bill.govtrack.GovTrackBillParser
import bz.stew.bracken.ui.model.types.bill.BillData

/**
 * Model for loading data from gov track
 * Created by stew on 1/24/17.
 */
class BillModelGovTrack : Model {


   private val bills: MutableMap<Int, BillData> = mutableMapOf()
   private var latestBillData: dynamic = null

   override fun loadBillData(data: dynamic, append: Boolean) {

      val parsed = GovTrackBillParser(data).parse(this)
      parsed.forEach { bills.put(it.uniqueId, it) }

      this.latestBillData = data;
      indexData()
   }

   override fun getBillData(): List<BillData> {
      return this.bills.values.toList()
   }

   private fun indexData() {
      for (idx in ALL_INDEX_DEFS) {
         idx.indexInstances(this.bills.values)
      }
      //STATUS_INDEX.indexInstances(this.bills.values)
   }

   override fun getBillById(uniqueId: Int): BillData? {
      return this.bills[uniqueId]
   }
}