package bz.stewart.bracken.db.bill.database.mongodb

import bz.stewart.bracken.db.database.DatabaseClient
import com.mongodb.MongoClient

/**
 * Created by stew on 4/1/17.
 */
class ReadOnlyBillDatabase(dbClient: DatabaseClient<MongoClient>
                           ) : BillMongoDb(dbClient)