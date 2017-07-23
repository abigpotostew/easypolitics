package bz.stew.bracken.ui.model.parse.bill.govtrack

import bz.stew.bracken.model.parse.bill.AbstractBillParser
import bz.stew.bracken.ui.model.Model
import bz.stew.bracken.ui.model.types.bill.BillData

/**
 * Created by stew on 4/29/17.
 */
class GovTrackBillParser (json: dynamic) : AbstractBillParser(json) {
   override fun getBillsArray(): dynamic {
      return json.objects
   }

   override fun getBillCount(): Int {
      return json.objects.length
   }

   override fun parseBill(bill: dynamic, model: Model): BillData {
      return BillDataGovTrack(model).build(bill)
   }
}