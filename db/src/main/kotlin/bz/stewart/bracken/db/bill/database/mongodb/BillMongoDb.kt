package bz.stewart.bracken.db.bill.database.mongodb

import bz.stewart.bracken.db.bill.data.Bill
import bz.stewart.bracken.db.database.Database
import bz.stewart.bracken.db.database.DatabaseClient
import bz.stewart.bracken.db.database.mongo.AbstractMongoDb
import bz.stewart.bracken.db.database.mongo.CollectionWriter
import bz.stewart.bracken.db.database.mongo.emptyDatabaseWriter
import com.mongodb.MongoClient

/**
 * just a wrapper around AbstractMongoDb<Bill> to clean up the syntax
 * Created by stew on 4/1/17.
 */
abstract class BillMongoDb(dbClient: DatabaseClient<MongoClient>,
                           collWriter: CollectionWriter<Bill, Database<Bill>> = emptyDatabaseWriter())

    : AbstractMongoDb<Bill>(dbClient, Bill::class.java, collWriter) {
    //TODO hardcode the 'bills' collection somewhere in here, similar to LegislatorCreateDb
}