package bz.stewart.bracken.db

import bz.stewart.bracken.db.bill.database.mongodb.BillJsonDataDatabase
import bz.stewart.bracken.db.bill.database.mongodb.BillMongoDb
import bz.stewart.bracken.db.bill.database.mongodb.SingleBillWriter
import bz.stewart.bracken.db.leglislators.LegislatorArguments
import bz.stewart.bracken.db.leglislators.LegislatorRuntime
import bz.stewart.bracken.db.leglislators.MockLegislatorArgs
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

      fun getTestLegislatorsCurrentData():String{
         return getTestResourcesDir(
               "/legislators-data/legislators-current.json")
      }

      fun getTestLegislatorsSocialData():String{
         return getTestResourcesDir(
               "/legislators-data/legislators-social-media.json")
      }

      fun generateTestBillWriteDb(runtimeMode: RuntimeMode, testRun:Boolean = false): BillJsonDataDatabase {
         //val collectionName = "billsTest_"+ Date().time
         val dbName = "congress" + Date().time
         val writer = SingleBillWriter()
         val db = BillJsonDataDatabase(dataRoot = File(getTestResourcesData()),
                                       dbName = dbName,
                                       runtimeMode = runtimeMode,
                                       testRun = testRun,
                                       writer = writer)
         db.openDatabase()
         db.loadData(null)
         db.closeDatabase()

         val legislator = LegislatorRuntime(MockLegislatorArgs(_files = mutableListOf(File(getTestLegislatorsCurrentData())),
               _testMode = false,
               _dbName = dbName,
               _socialFile = File(getTestLegislatorsSocialData())))
         legislator.execute() //build legislator db

         return db
      }
   }
}

