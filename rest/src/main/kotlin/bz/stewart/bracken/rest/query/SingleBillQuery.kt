package bz.stewart.bracken.rest.query

import com.mongodb.BasicDBObject

class SingleBillQuery(private val billId: String,
                      private val db: MainDbAccess) : Query {
    override fun find(): QueryResult {
       return db.standardBillQuery(queryBson(), 1, null)
    }

    private fun queryBson(): BasicDBObject {
        val query = BasicDBObject()
        query.put("bill_id", billId)
        return query
    }
}