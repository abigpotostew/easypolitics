package bz.stew.bracken.model.parse.bill

import bz.stew.bracken.ui.common.bill.BillData
import bz.stew.bracken.ui.service.Parser

/**
 * Created by stew on 4/29/17.
 */
abstract class AbstractBillParser : Parser<BillData> {

   protected abstract fun getBillsArray(json: dynamic): dynamic
   protected abstract fun getBillCount(json: dynamic): Int
   protected abstract fun parseBill(json: dynamic): BillData


   override fun parse(json: dynamic): Collection<BillData> {
      val bills = getBillsArray(json)
      val n: Int = getBillCount(json)
      var i = 0
      val out = mutableListOf<BillData>()
      while (i < n) {
         try {
            val o: dynamic = bills[i]
            val bill: BillData = parseBill(o)
            out.add(bill)
         } catch (e: IllegalStateException) { //should only catch parse errors
            throw RuntimeException(
               "Issue parsing bill data, please improve this exception to see which parse failed: \n\t" + e.toString())
         }
         i++
      }

      return out
   }
}