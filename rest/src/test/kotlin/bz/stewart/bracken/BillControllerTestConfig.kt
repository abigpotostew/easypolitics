package bz.stewart.bracken

import bz.stewart.bracken.db.TestUtils
import bz.stewart.bracken.db.bill.database.mongodb.BillMongoDb
import bz.stewart.bracken.rest.EasypoliticsRestApplication
import bz.stewart.bracken.rest.MongoDbBean
import bz.stewart.bracken.rest.MongoDbBeanProd
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.FilterType


/**
 * Created by stew on 4/1/17.
 */

@SpringBootApplication
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

   var mongoDb:BillMongoDb?=null

   override fun setup() {
      this.mongoDb =  TestUtils.generateTestDb()
   }

   override fun destroy() {
      mongoDb?.getTargetCollection()?.drop()
   }

   override fun getDb(): BillMongoDb? {
      return mongoDb
   }
}