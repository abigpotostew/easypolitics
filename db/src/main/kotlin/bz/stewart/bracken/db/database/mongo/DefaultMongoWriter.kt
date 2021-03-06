package bz.stewart.bracken.db.database.mongo

import bz.stewart.bracken.db.database.DbItem
import com.mongodb.MongoWriteException
import com.mongodb.client.MongoDatabase
import org.litote.kmongo.save

/**
 * Default mutating collection writer.
 */
abstract class DefaultMongoWriter<T : DbItem> : CollectionWriter<T, AbstractMongoDb<T>> {
   var isOpen = false
   var openToDb: AbstractMongoDb<T>? = null

   override fun before(db: AbstractMongoDb<T>) {
      isOpen = db.isDbOpen()
      openToDb = db
   }

   override fun after(db: AbstractMongoDb<T>) {
      isOpen = false
      openToDb = null
   }

   private fun validStateToWrite(db: AbstractMongoDb<T>) {
      if (!isOpen || db != openToDb) {
         throw RuntimeException(
               "Error, the writer.before() method was not called before calling the write method.")
      }
   }

   override fun write(element: T, collection: String, db: AbstractMongoDb<T>) {
      validStateToWrite(db)
      try {
         db.getCollection(collection)?.save(element)
      } catch(e: MongoWriteException) {
         error("couldn't write:\n\telement: $element\n\t db:$db\n\tcollection:$collection")
      }

   }

   override fun drop(collection: String,
                     db: AbstractMongoDb<T>) {
      validStateToWrite(db)
      db.getCollection(collection)?.drop()
   }

   override fun dropDb(db: MongoDatabase) {
      db.drop()
   }
}