package bz.stewart.bracken.db.database.mongo

import bz.stewart.bracken.db.database.DatabaseClient
import bz.stewart.bracken.db.database.DbItem
import com.mongodb.MongoClient

/**
 * Access a single database that stores two different document classes.
 * Created by stew on 6/6/17.
 */
abstract class CompositeMongoDb <T: DbItem, S: DbItem>(dbClient: DatabaseClient<MongoClient>, _clazzA:Class<T>, collectionA:String, _clazzB:Class<S>, collectionB: String): AbstractMongoDb<DbItem>(
      dbClient, DbItem::class.java,
        emptyDatabaseWriter()){

   private val clazzA = _clazzA
   private val clazzB = _clazzB
   private val dbA : AbstractMongoDb<T> = DefaultMongoDb<T>(
           this.dbClient,
           collectionA,
           _clazzA)
   private val dbB : AbstractMongoDb<S> = DefaultMongoDb<S>(
       this.dbClient,
           collectionB,
           _clazzB)

   override fun getCollectionName(): String {
      return "none" // this class delegates db access to the two internal dbs
   }

   override fun openDatabase() {
      dbA.openDatabase()
      dbB.overrideDb(dbA.client!!, dbA.db!!)
   }

   fun getFirstDb(): AbstractMongoDb<T> {
      return dbA
   }

   fun getSecondDb(): AbstractMongoDb<S> {
      return dbB
   }
}

