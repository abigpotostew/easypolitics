package bz.stewart.bracken.rest.query

import bz.stewart.bracken.db.bill.data.Bill
import bz.stewart.bracken.db.database.mongo.CompositeMongoDb
import bz.stewart.bracken.db.leglislators.data.LegislatorData
import bz.stewart.bracken.rest.data.bills.BillDelegated
import bz.stewart.bracken.rest.data.bills.toPublicBillCollection
import bz.stewart.bracken.rest.util.MathUtil
import com.mongodb.BasicDBObject
import com.mongodb.client.FindIterable
import org.bson.conversions.Bson
import org.litote.kmongo.MongoOperator
import org.litote.kmongo.find
import org.litote.kmongo.formatJson

class MainDbAccess(_databaseName: String) :
      CompositeMongoDb<Bill, LegislatorData>(
            _databaseName,
            Bill::class.java,
            "bills",
            LegislatorData::class.java,
            "legislators"),
      StandardDbAccess {
   override fun standardBillQuery(query: BasicDBObject,
                                  limitRequest: Int?,
                                  sortRequest: Bson?,
                                  offset: Int
                                 ): QueryResult {

      val limit = if (limitRequest==null) 1 else MathUtil.clamp(limitRequest, 0, 1000)
      val queryBillOut = queryBills(query, limit, sortRequest, offset)
      if (queryBillOut == null) {
         return BasicQueryResult(null, limit, offset)
      }
      val peopleMap = peopleQueryToMap(queryBillOut)

      val finalResult :Collection<BillDelegated> = toPublicBillCollection(queryBillOut.take(limit), peopleMap)
      return BasicQueryResult(finalResult, limit, offset)
      //return toPublicBillCollection(, peopleMap)
   }

   private fun peopleQueryToMap(bills: FindIterable<Bill>): Map<String, LegislatorData> {
      val allPeople: Set<String> = collectPeople(bills)
      val peopleQuery = getPeopleQuery(allPeople)
      val db = getSecondDb()
      val queryPeopleOut: FindIterable<LegislatorData>? = getSecondDb().queryCollection(
            db.getCollectionName(), {
         find(peopleQuery)
      })
      if (queryPeopleOut == null) {
         return emptyMap<String, LegislatorData>()
      }
      val map = peopleMap(queryPeopleOut)
      return map
   }

   private fun peopleMap(
         people: FindIterable<LegislatorData>): Map<String, LegislatorData> {
      val iter = people.asIterable()
      return iter.associateBy { it.id.bioguide }
   }

   private fun toJsonArray(strings: Collection<String>): String {
      if (strings.size == 0) {
         return "[]"
      }
      val iter = strings.iterator()
      val strBuilder = StringBuilder("[ \"${iter.next()}\"")
      for (str in iter) {
         strBuilder.append(", \"${str}\"")
      }
      strBuilder.append("]")
      return strBuilder.toString()
   }

   private fun getPeopleQuery(bioGuideIds: Set<String>): String {
      val inList = BasicDBObject()
      inList.put("${MongoOperator.`in`}", toJsonArray(bioGuideIds))

      val topQuery = BasicDBObject()
      topQuery.put("id.bioguide", inList)

      val out =
            """
{"id.bioguide": {${MongoOperator.`in`}: ${toJsonArray(bioGuideIds)} }}
""".formatJson()

      return out//topQuery
   }

   private fun collectPeople(bills: FindIterable<Bill>): Set<String> {
      val allPeople: MutableSet<String> = mutableSetOf()
      for (bill: Bill in bills) {
         allPeople.add(bill.billSponsor.bioguide_id)
         bill.cosponsorsArr.forEach {
            allPeople.add(it.bioguide_id)
         }
      }
      return allPeople
   }

   private fun queryBills(
         query: BasicDBObject,
         limit: Int,
         sortRequest: Bson?,
         offset: Int=0): FindIterable<Bill>? {


      val db = getFirstDb()
      val queryBillOut: FindIterable<Bill> = getFirstDb().queryCollection(
            db.getCollectionName(), {
         val res = find(query)
         if (offset != 0) { //TODO: this is not an efficient pagination technique, use the sort by info such as only bills with date later than the last bill from prior query.
            res.skip(offset)
         }
         res
      }) ?: return null

         queryBillOut.limit(limit)

      if (sortRequest!=null) {
         queryBillOut.sort(sortRequest)
      }
      return queryBillOut
   }
}