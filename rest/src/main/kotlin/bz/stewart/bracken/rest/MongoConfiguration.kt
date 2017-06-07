package bz.stewart.bracken.rest

import bz.stewart.bracken.db.bill.database.mongodb.BillMongoDb
import bz.stewart.bracken.db.bill.database.mongodb.ReadOnlyDatabase
import bz.stewart.bracken.rest.query.MainDbAccess
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration



/**
 * Created by stew on 3/14/17.
 */
@Configuration
class MongoConfiguration  {

   @Bean
   fun mongoDatabaseBean(): MongoDbBean {
//      val dbName  = System.getProperty("bz.stewart.bracken.db.name")
//      val collectionName= System.getProperty("bz.stewart.bracken.db.collection")
//
//      val client = KMongo.createClient() //get com.mongodb.MongoClient new instance
//      val database = client.getDatabase(dbName) //normal java driver usage
//
//      return ReadOnlyDatabase(dbName,collectionName)
      return MongoDbBeanProd()
   }
//   override fun mongo(): Mongo {
//      return KMongo.createClient()
//   }
//
//   override fun getDatabaseName(): String {
//      return "congress1"
//   }
//
//   @Bean
//   override fun mongoTemplate(): MongoTemplate {
//      return MongoTemplate(mongo(), getDatabaseName())
//   }
}

class MongoDbBeanProd:MongoDbBean(){

   var readDb:ReadOnlyDatabase?=null
   var mainDbInst: MainDbAccess?=null

   override fun setup() {
      val dbName  = System.getProperty("bz.stewart.bracken.db.name")
      val collectionName= System.getProperty("bz.stewart.bracken.db.collection")

      //val client = KMongo.createClient() //get com.mongodb.MongoClient new instance
      //val database = client.getDatabase(dbName) //normal java driver usage

      //readDb = ReadOnlyDatabase(dbName, collectionName)
      //readDb?.openDatabase()

      mainDbInst = MainDbAccess(dbName)
      mainDbInst?.openDatabase()
   }

   override fun destroy() {
      //readDb?.closeDatabase()
      mainDbInst?.closeDatabase()
   }

   override fun getBillDb(): BillMongoDb? {
      return readDb
   }

   override fun getMainDb(): MainDbAccess? {
      return mainDbInst
   }
}