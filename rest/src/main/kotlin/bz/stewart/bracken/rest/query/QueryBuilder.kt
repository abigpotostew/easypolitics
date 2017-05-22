package bz.stewart.bracken.rest.query

import bz.stewart.bracken.db.data.Bill
import bz.stewart.bracken.rest.bills.BillExample
import bz.stewart.bracken.rest.util.MathUtil
import com.mongodb.BasicDBObject
import com.mongodb.client.FindIterable
import com.mongodb.client.MongoCollection
import mu.KLogging
import org.bson.conversions.Bson

/**
 * Created by stew on 3/19/17.
 */
class QueryBuilder(private val collection: MongoCollection<Bill>,
                   private val exampleBill: BillExample,
                   private val orderBy: String,
                   limitIn: Int) {
   private val limit = MathUtil.clamp(limitIn, 0, 1000)

   private val SORT_DESCENDING = -1
   private val SORT_ASCENDING = 1

   companion object : KLogging()

   private fun queryBson(): BasicDBObject {
      val query = BasicDBObject()
      if(!exampleBill.hasEmptyCongress() ){//by congress number
         val congressPair = byCongress()
         query.put(congressPair.key, congressPair.value)
      }

      return query
//      return """{
//   ${byCongress()}
//}"""
   }

   private fun byCongress(): BsonPair {
//      return if (exampleBill.getCongress().isNullOrBlank()) {
//         NO_OP()
//      }
//      else {
         return BsonPair("congress",exampleBill.getCongress()!!)
         //"congress: ${exampleBill.getCongress()!!}"
//      }
   }

   private fun getSort(): Bson {
      val sortKey = "status_at"
      val sortOrder =  when (orderBy) {
         "-current_status_date" -> SORT_DESCENDING
         "+current_status_date" -> SORT_ASCENDING
         else -> SORT_DESCENDING
      }
      return BasicDBObject(sortKey, sortOrder)
   }

   fun find(): QueryResult {
      val queryOut: FindIterable<Bill>? = collection.find(queryBson())

      if (queryOut == null) {
         return QueryResultImpl(null, limit)
      }
      queryOut.limit(limit)

      queryOut.sort(getSort())

//      logger.info { "Started!!" }
//      val iter = queryOut!!.iterator()
//      while (iter.hasNext()){
//
//         try{
//            var b:Bill? = iter.next()
//            b = null
//         }catch (e:Exception){
//            logger.error { "couldn't do it man $e" }
//         }
//      }
//
//      logger.info { "Finished" }
      return QueryResultImpl(queryOut, limit)
   }
}

private fun NO_OP(): String {
   return ""
}


private class BsonPair(val key: String, val value:Any?){

}