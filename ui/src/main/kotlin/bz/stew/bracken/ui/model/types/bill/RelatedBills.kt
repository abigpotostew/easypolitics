package bz.stew.bracken.ui.model.types.bill

/**
 * Created by stew.bracken on 2/22/17.
 */
class RelatedBills {
    private val relatedBills: MutableMap<String, MutableSet<BillProxy>> = mutableMapOf()
    fun add(relation: String,
             bill: BillProxy) {
        var coll = relatedBills.get(relation)
        if (coll == null) {
            coll = mutableSetOf()
            relatedBills.put(relation, coll)
        }
        coll!!.add(bill)
    }
}