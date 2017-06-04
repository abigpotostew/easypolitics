package bz.stewart.bracken.db.bill.database.mongodb

import bz.stewart.bracken.db.bill.data.Bill
import bz.stewart.bracken.db.database.CollectionWriter
import bz.stewart.bracken.db.database.Database
import bz.stewart.bracken.db.database.emptyDatabaseWriter

/**
 * just a wrapper around AbstractMongoDb<Bill> to clean up the syntax
 * Created by stew on 4/1/17.
 */
abstract class BillMongoDb(_databaseName: String = "",
                           collWriter: CollectionWriter<Bill, Database<Bill>> = emptyDatabaseWriter())

   : AbstractMongoDb<Bill>(
      _databaseName, Bill::class.java, collWriter) {
}