package bz.stewart.bracken.db.database

import bz.stewart.bracken.db.bill.database.mongodb.AbstractMongoDb
import com.mongodb.client.MongoDatabase

/**
 * Created by stew on 3/9/17.
 */
interface CollectionWriter<T : DbItem, in B : Database<T>> {
   fun write(element: T, collection: String, db: B)
   fun drop(collection: String, db: B)
   fun before(db: AbstractMongoDb<T>)
   fun after(db: AbstractMongoDb<T>)


   /**
    * careful now
    */
   fun dropDb(db: MongoDatabase)
}

fun <T : DbItem, B : Database<T>> emptyDatabaseWriter(): CollectionWriter<T, B> {
   return object : CollectionWriter<T, B> {
      override fun before(db: AbstractMongoDb<T>) {
      }

      override fun after(db: AbstractMongoDb<T>) {
      }

      override fun write(element: T, collection: String, db: B) {
      }

      override fun drop(collection: String, db: B) {
      }

      override fun dropDb(db: MongoDatabase) {
      }
   }
}