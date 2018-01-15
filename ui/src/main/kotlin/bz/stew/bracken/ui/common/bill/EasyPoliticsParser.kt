package bz.stew.bracken.ui.common.bill

import bz.stew.bracken.model.parse.bill.AbstractBillParser
import bz.stew.bracken.model.parse.bill.EasyPoliticsBillData
import bz.stewart.bracken.shared.data.person.Legislator

/**
 * Created by stew on 4/29/17.
 */
class EasyPoliticsParser : AbstractBillParser() {
    private val legislatorCache: MutableMap<String, Legislator> = mutableMapOf()

    override fun getBillsArray(json: dynamic): dynamic {
        return json.results
    }

    override fun getBillCount(json: dynamic): Int {
        @Suppress("UnsafeCastFromDynamic")
        return json.results.length
    }

    override fun parseBill(json: dynamic): BillData {
        return EasyPoliticsBillData(this).build(json)
    }

    fun legislatorCached(bioguideId: String): Legislator? {
        return legislatorCache[bioguideId]
    }

    fun saveLegislator(person: Legislator) {
        legislatorCache.put(person.getBioguideId(), person)
    }
}