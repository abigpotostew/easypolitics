package bz.stewart.bracken.db

import bz.stewart.bracken.db.data.Bill
import bz.stewart.bracken.db.database.DbItem
import bz.stewart.bracken.db.database.Database
import bz.stewart.bracken.db.database.emptyDatabase

/**
 * Created by stew on 3/11/17.
 */
interface DbRuntime<T: DbItem> {

   fun validateArgs()
   fun run()
}

fun emptyDbRuntime(): DbRuntime<out DbItem> {
   return object: DbRuntime<DbItem> {
      override fun validateArgs() {}
      override fun run() {}
   }
}