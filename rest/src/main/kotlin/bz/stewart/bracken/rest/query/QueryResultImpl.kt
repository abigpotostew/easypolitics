package bz.stewart.bracken.rest.query

import bz.stewart.bracken.db.bill.data.Bill
import bz.stewart.bracken.rest.data.bills.BillDelegated
import bz.stewart.bracken.rest.data.bills.toPublicBillCollection
import com.fasterxml.jackson.annotation.JsonPropertyOrder
import com.mongodb.client.FindIterable

/**
 * Created by stew on 3/13/17.
 */
class QueryResultImpl(mongoQueryResult: FindIterable<Bill>?, limit: Int) :QueryResult{
   private var queryMeta = Meta(limit, 0, mongoQueryResult?.count() ?: 0)
   private var resultsColl :Collection<BillDelegated>? = toPublicBillCollection(
         mongoQueryResult?.take(queryMeta.limit) ?: emptyList())

   override var meta
      get() = queryMeta
      set(v) { queryMeta = v }

   override var results
      get() = resultsColl
      set(v) { resultsColl = v }
}

class BasicQueryResult(results:Collection<BillDelegated>?, limit:Int?, offset:Int = 0):QueryResult{
   override var meta: Meta = Meta(limit ?: 0, offset, results?.count() ?: 0)
   override var results: Collection<BillDelegated>? = results
}

fun emptyQueryResult():QueryResult{
   return BasicQueryResult(results = null, limit=null)
}

@JsonPropertyOrder("meta", "results")
interface QueryResult {
   var meta :Meta
   var results :Collection<BillDelegated>?
}

@Suppress("unused")
class Meta(_limit: Int, offset: Int, total: Int) {
   val limit = clamp(_limit, 1, 500)
   val offset = offset
   val total_count = total

   private fun clamp(v: Int, lo: Int, hi: Int): Int {
      return when {
         v < lo -> lo
         v > hi -> hi
         else -> v
      }
   }
}
