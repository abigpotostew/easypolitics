package bz.stewart.bracken.rest.data.bills

import bz.stewart.bracken.rest.query.MainDbAccess

class BillDAO(dbName:String) {

    val mainDbInst: MainDbAccess= MainDbAccess(dbName)

    init {
        mainDbInst.openDatabase()
    }

    fun close(){
        this.mainDbInst.closeDatabase()
    }
}