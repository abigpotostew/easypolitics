package bz.stewart.bracken.db

import bz.stewart.bracken.db.bill.database.mongodb.BillJsonDataDatabase
import bz.stewart.bracken.db.bill.database.mongodb.BillMongoDb
import bz.stewart.bracken.db.bill.database.mongodb.SingleBillWriter
import java.io.File
import java.util.*

/**
 * Created by stew on 3/10/17.
 */
class TestUtils {
   companion object Methods{
      /**
       * add path should have a leading '/'
       */
      fun getTestResourcesData(addPath:String=""):String{
         return "${getTestResourcesDir()}/data$addPath"
      }

      /**
       * add path should have a leading '/'
       */
      fun getTestResourcesDir(addPath:String=""):String{
         var resourceDir = System.getProperty("user.dir")+"/build/resources/test" //doesn't work from gradle
         if(System.getProperty("test.resources")!=null){
            resourceDir = System.getProperty("test.resources")
         }
         return "$resourceDir$addPath"
      }

      fun generateTestDb(): BillMongoDb {
         val collectionName = "billsTest_"+ Date().time
         val writer = SingleBillWriter()
         val db = BillJsonDataDatabase(File(getTestResourcesData()), "congress1", collectionName, RuntimeMode.RESET, false,
                                       writer!!)
         db.openDatabase()
         db.loadData(null)
         db.closeDatabase()

         val personDbCollection = "legislator"

         return db
      }
   }
}

