package bz.stewart.bracken.rest.query

import com.mongodb.BasicDBObject
import org.bson.conversions.Bson

/**
 * Created by stew on 6/6/17.
 */
interface StandardDbAccess {
   fun standardBillQuery(query: BasicDBObject, limitRequest: Int?,
                         sortRequest: Bson?, offset: Int = 0): QueryResult
}