package bz.stewart.bracken.db.database.mongodb

//import org.litote.kmongo.MongoOperator
import bz.stewart.bracken.db.data.Bill
import bz.stewart.bracken.db.database.CollectionWriter
import com.mongodb.MongoWriteException
import org.litote.kmongo.save

/**
 * Created by stew on 3/9/17.
 */

abstract class BillWriter : CollectionWriter<Bill, AbstractMongoDb<Bill>> {
   var isOpen = false
   var openToDb:AbstractMongoDb<Bill>? = null
   fun before(db: AbstractMongoDb<Bill>) {
      //db.openDatabase()
      isOpen = db.isDbOpen()
      openToDb = db
   }

   fun after(db: AbstractMongoDb<Bill>) {
      //db.closeDatabase()
      isOpen = false
      openToDb = null
   }

   private fun validStateToWrite(db: AbstractMongoDb<Bill>) {
      if (!isOpen || db != openToDb) {
         throw RuntimeException("Error, the writer.before() method was not called before calling the write method.")
      }
   }

   override fun write(element: Bill, collection: String, db: AbstractMongoDb<Bill>) {
      validStateToWrite(db)
   }

   override fun drop(collection: String,
                     db: AbstractMongoDb<Bill>) {
      validStateToWrite(db)
   }
}

/**
 * Write Bills to db
 */
class SingleBillWriter : BillWriter() {

   override fun write(element: Bill, collection: String, db: AbstractMongoDb<Bill>) {
      super.write(element, collection, db)
      try {
         db.getCollection(collection)?.save(element)
      } catch(e: MongoWriteException) {
         error("couldn't write:\n\telement: $element\n\t db:$db\n\tcollection:$collection")
      }
   }

   override fun drop(collection: String,
                     db: AbstractMongoDb<Bill>) {
      super.drop(collection, db)
      db.getCollection(collection)?.drop()
      //todo figure out optimal way to connect to db
   }
}

fun emptyBillWriter(): BillWriter {
   return object : BillWriter() {
      override fun write(element: Bill, collection: String, db: AbstractMongoDb<Bill>) {}

      override fun drop(collection: String, db: AbstractMongoDb<Bill>) {}

   }
}