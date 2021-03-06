package bz.stewart.bracken.db.database.mongo

import bz.stewart.bracken.db.database.Database
import bz.stewart.bracken.db.database.DbItem
import com.mongodb.client.MongoDatabase

/**
 * Created by stew on 3/9/17.
 */
interface CollectionWriter<T : DbItem, in B : Database<T>> {
   fun write(element: T, collection: String, db: B)
   fun drop(collection: String, db: B)
   fun before(db: B)
   fun after(db: B)


   /**
    * careful now
    */
   fun dropDb(db: MongoDatabase)
}

fun <T : DbItem, B : Database<T>> emptyDatabaseWriter(): CollectionWriter<T, B> {
   return object : CollectionWriter<T, B> {
      override fun before(db: B) {
      }

      override fun after(db: B) {
      }

      override fun write(element: T, collection: String, db: B) {
      }

      override fun drop(collection: String, db: B) {
      }

      override fun dropDb(db: MongoDatabase) {
      }
   }
}