package bz.stewart.bracken.db

import bz.stewart.bracken.db.bill.data.Bill

class SyncAllIndex : DbRuntime {
    override fun validateArgs() {
        return
    }

    override fun run() {
        //get the index definition metadata, group by the db collection
        //for each db collection group
        //  get the actual db collection, get the index definitions
        //      if the db is missing the index, create.
        val field = Bill::introduced_at

    }
}