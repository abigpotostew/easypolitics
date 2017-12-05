package bz.stewart.bracken.rest

import bz.stewart.bracken.db.TestUtils
import bz.stewart.bracken.db.bill.database.mongodb.BillMongoDb
import bz.stewart.bracken.rest.query.MainDbAccess
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.FilterType


/**
 * Created by stew on 4/1/17.
 */

//disabling to prevent test from running
//@SpringBootApplication
@ComponentScan(excludeFilters = arrayOf(
      // Exclude the default message service
      //ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = DefaultMessageService::class),
      // Exclude the default boot application or it's
      // @ComponentScan will pull in the default message service
      ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = EasypoliticsRestApplication::class),
      ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = MongoDbBeanProd::class),
      ComponentScan.Filter(type = FilterType.ANNOTATION, value = MongoDbBean::class, classes = arrayOf(MongoDbBeanProd::class))))
//@TestConfiguration
class BillControllerTestConfig {

   @Bean
   fun mongoDatabase(): MongoDbBean {
      TestUtils.getTestResourcesData()
      return TestMongoBean()
   }
}

class TestMongoBean: MongoDbBean() {

   var billMongoDb:BillMongoDb?=null
   //var peopleMongoDb:LegislatorMongoDb?=null
   var mainAccess:MainDbAccess? = null

   override fun setup() {
      //not working, so i'm commenting out to remove usage
//      val testDb = TestUtils.generateTestDb()
//      this.billMongoDb =  testDb
//      this.mainAccess = MainDbAccess(testDb.getDbName())
   }

   override fun destroy() {
      val db:BillMongoDb? = billMongoDb
      if(db != null) {
         db.openDatabase()
         db.getTargetCollection()?.drop()
         db.dropDatabase()
         db.closeDatabase()
      }
   }

   override fun getBillDb(): BillMongoDb? {
      return billMongoDb
   }

   override fun getMainDb(): MainDbAccess? {
      return mainAccess
   }
}