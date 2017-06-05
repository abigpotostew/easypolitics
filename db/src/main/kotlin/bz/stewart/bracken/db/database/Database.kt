package bz.stewart.bracken.db.database

import java.io.Closeable

/**
 * Created by stew.bracken on 3/7/17.
 */
abstract class Database<T : DbItem>() : Closeable {
   abstract fun getDbName(): String
}

fun emptyDatabase(): Database<out DbItem> {
   return object : Database<DbItem>() {
      override fun getDbName(): String {
         return ""
      }
      override fun close() {
      }
   }
}