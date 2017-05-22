package bz.bracken.stewart.db.mongodb

import org.junit.Test

/**
 * Created by stew on 5/16/17.
 */
class DatabaseConnectionTest {

   val dbName = "congress1"
   @Test
   fun testMongoConnection() {
      val db = DebugDatabase(dbName)
      db!!.openDatabase()

      db!!.closeDatabase()
   }
}