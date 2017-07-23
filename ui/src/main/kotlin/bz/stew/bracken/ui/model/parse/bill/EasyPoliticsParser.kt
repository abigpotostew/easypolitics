package bz.stew.bracken.ui.model.parse.bill

import bz.stew.bracken.model.parse.bill.AbstractBillParser
import bz.stew.bracken.model.parse.bill.EasyPoliticsBillData
import bz.stew.bracken.ui.model.Model
import bz.stew.bracken.ui.model.types.bill.BillData
import bz.stewart.bracken.shared.data.person.Legislator

/**
 * Created by stew on 4/29/17.
 */
class EasyPoliticsParser(json: dynamic) : AbstractBillParser(json) {
   private val legislatorCache: MutableMap<String, Legislator> = mutableMapOf()

   override fun getBillsArray(): dynamic {
      return this.json.results
   }

   override fun getBillCount(): Int {
      return this.json.results.length
   }

   override fun parseBill(bill: dynamic, model: Model): BillData {
      return EasyPoliticsBillData(this).build(bill)
   }

   fun legislatorCached(bioguideId: String): Legislator? {
      return legislatorCache[bioguideId]
   }

   fun saveLegislator(person: Legislator) {
      legislatorCache.put(person.getBioguideId(), person)
   }
}