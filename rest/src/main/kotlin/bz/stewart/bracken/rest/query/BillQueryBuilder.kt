package bz.stewart.bracken.rest.query

import bz.stewart.bracken.rest.bills.BillDelegated
import bz.stewart.bracken.rest.bills.BillExample
import bz.stewart.bracken.rest.util.MathUtil
import com.mongodb.BasicDBObject
import mu.KLogging
import org.bson.conversions.Bson

/**
 * Created by stew on 3/19/17.
 */
class BillQueryBuilder(private val db: MainDbAccess,
                       private val exampleBill: BillExample,
                       private val orderBy: String,
                       limitIn: Int,
                       offset: Int) {

   private val MAX_OFFSET = 100000
   private val MAX_RETURNED = 1000

   private val limit = MathUtil.clamp(limitIn, 0, MAX_RETURNED)
   private val offset = MathUtil.clamp(offset, 0, MAX_OFFSET)

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
         " current_status_date" -> SORT_ASCENDING
         else -> SORT_DESCENDING
      }
      return BasicDBObject(sortKey, sortOrder)
   }

   /**
    * execute the query (unvalidated at this point) with request limit and sort
    * TODO validate input first
    */
   fun find():QueryResult{
      val queryRes :Collection<BillDelegated> = db.standardBillQuery(queryBson(), limit, getSort(), offset)
      return BasicQueryResult(queryRes, limit)
   }


   /*fun find(): QueryResult {

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
   }*/
}

private fun NO_OP(): String {
   return ""
}


private class BsonPair(val key: String, val value:Any?){

}