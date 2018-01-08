package bz.stewart.bracken.rest.data.bills

import bz.stewart.bracken.db.database.DatabaseClient
import bz.stewart.bracken.rest.query.MainDbAccess
import com.mongodb.MongoClient

class BillDAO(dbClient:DatabaseClient<MongoClient>) {

    val mainDbInst: MainDbAccess = MainDbAccess(dbClient)

    init {
        mainDbInst.openDatabase()
    }

    fun close() {
        this.mainDbInst.closeDatabase()
    }
}