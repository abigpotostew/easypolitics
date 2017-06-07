package bz.stewart.bracken.db.database

import com.mongodb.MongoClient
import com.mongodb.MongoClientException
import com.mongodb.MongoTimeoutException
import com.mongodb.client.MongoCollection
import com.mongodb.client.MongoDatabase
import mu.KLogging
import org.litote.kmongo.KMongo

/**
 * Created by stew on 3/9/17.
 */
abstract class AbstractMongoDb<T : DbItem>(_databaseName: String = "",
                                           val clazz: Class<T>,
                                           val collWriter: CollectionWriter<T, Database<T>> = emptyDatabaseWriter()) : Database<T>() {

   private var client: MongoClient? = null
   protected var db: MongoDatabase? = null
   private var isOpen = false
   var databaseName: String = _databaseName

   companion object : KLogging()

   /**
    * Delegates the drop behavior to the writer.
    */
   fun dropDatabase() {
      getWriter().dropDb(db!!)
   }

   fun isDbOpen(): Boolean {
      return isOpen
   }

   open fun openDatabase() {
      if (isDbOpen()) {
         logger.debug { "Mongo Db already open." }
      }
      try {
         client = KMongo.createClient()
         db = client?.getDatabase(databaseName)
         isOpen = true
      } catch(e: MongoClientException) {
         logger.error { "Error connecting to mongodb, is the server running?" }
         error(e)
      } catch (e: MongoTimeoutException) {
         logger.error { "Error connecting to mongodb, is the server running?" }
         error(e)
      }
   }

   fun closeDatabase() {
      client?.close()
      isOpen = false
   }

   protected fun validForQuery(collection: String) {
      if (!this.isOpen || collection.isEmpty()) {
         throw RuntimeException(
               "Running query before opening connection to mongo database.")
      }
   }

   fun getCollection(collection: String): MongoCollection<T>? {
      validateOpen()
      return db!!.getCollection(collection, clazz)
   }

   private fun validateOpen() {
      if (!isDbOpen()) {
         throw RuntimeException("Please call openDatabase() before running a query")
      }
   }

   fun <R> queryCollection(collection: String, query: MongoCollection<T>.() -> R?): R? {
      validateOpen()
      validForQuery(collection)
      val col: MongoCollection<T>? = getCollection(collection)

      return col?.query()
   }

   override fun getDbName(): String {
      return databaseName
   }

   abstract fun getCollectionName(): String

   fun getTargetCollection(): MongoCollection<T>? {
      return getCollection(getCollectionName())
   }

   fun getWriter(): CollectionWriter<T, in Database<T>> {
      return collWriter
   }

   override fun close() {
      closeDatabase()
   }
}