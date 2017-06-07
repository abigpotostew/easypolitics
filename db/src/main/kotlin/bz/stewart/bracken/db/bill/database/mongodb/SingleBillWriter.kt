package bz.stewart.bracken.db.bill.database.mongodb

//import org.litote.kmongo.MongoOperator
import bz.stewart.bracken.db.bill.data.Bill
import bz.stewart.bracken.db.database.AbstractMongoDb
import bz.stewart.bracken.db.database.DefaultAbstractWriter

/**
 * Created by stew on 3/9/17.
 */

abstract class BillWriter : DefaultAbstractWriter<Bill>() {

}

/**
 * Write Bills to db
 */
class SingleBillWriter : BillWriter() {

}

fun emptyBillWriter(): BillWriter {
   return object : BillWriter() {
      override fun write(element: Bill, collection: String, db: AbstractMongoDb<Bill>) {}

      override fun drop(collection: String, db: AbstractMongoDb<Bill>) {}

   }
}