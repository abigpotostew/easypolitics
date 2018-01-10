package bz.stewart.bracken.db.bill.database.mongodb

import bz.stewart.bracken.db.bill.data.Bill
import bz.stewart.bracken.db.database.mongo.AbstractMongoDb
import bz.stewart.bracken.db.database.mongo.DefaultMongoWriter

/**
 * Created by stew on 3/9/17.
 */

abstract class BillWriter : DefaultMongoWriter<Bill>()

/**
 * Write Bills to db
 */
class SingleBillWriter : BillWriter()

fun emptyBillWriter(): BillWriter {
   return object : BillWriter() {
      override fun write(element: Bill, collection: String, db: AbstractMongoDb<Bill>) {}

      override fun drop(collection: String, db: AbstractMongoDb<Bill>) {}

   }
}