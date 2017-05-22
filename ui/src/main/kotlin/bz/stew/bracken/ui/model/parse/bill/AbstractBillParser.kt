package bz.stew.bracken.model.parse.bill

import bz.stew.bracken.ui.model.Model
import bz.stew.bracken.ui.model.types.bill.BillData

/**
 * Created by stew on 4/29/17.
 */
abstract class AbstractBillParser(_json: dynamic) {

   protected val json:dynamic = _json

   protected abstract fun getBillsArray(): dynamic
   protected abstract fun getBillCount(): Int
   protected abstract fun parseBill(bill: dynamic, model: Model): BillData

   fun parse(model: Model): Collection<BillData> {
      val bills = getBillsArray()
      val n: Int = getBillCount()
      var i: Int = 0
      val out = mutableListOf<BillData>()
      while (i < n) {
         try {
            val o: dynamic = bills[i]
            val bill: BillData = parseBill(o, model)//BillDataGovTrack(this).build(o)
            out.add(bill)
//            bills.put(o.id,
//                      bill)
         } catch (e: IllegalStateException) { //should only catch parse errors
            throw RuntimeException(
                  "Issue parsing bill data, please improve this exception to see which parse failed: \n\t" + e.toString())
         }
         i++
      }

      return out
   }
}