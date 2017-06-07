package bz.stewart.bracken.db.database

/**
 * Access a single database that stores two different document classes.
 * Created by stew on 6/6/17.
 */
abstract class CompositeMongoDb <T: DbItem, S: DbItem>(_databaseName:String, _clazzA:Class<T>, collectionA:String, _clazzB:Class<S>, collectionB: String): AbstractMongoDb<DbItem>(
      _databaseName, DbItem::class.java, emptyDatabaseWriter()){

   private val clazzA = _clazzA
   private val clazzB = _clazzB
   private val dbA :AbstractMongoDb<T> = DefaultMongoDb<T>(_databaseName, collectionA,
                                                           _clazzA)
   private val dbB :AbstractMongoDb<S> = DefaultMongoDb<S>(_databaseName, collectionB,
                                                           _clazzB)

   override fun getCollectionName(): String {
      return "none" // this class delegates db access to the two internal dbs
   }

   override fun openDatabase() {
      dbA.openDatabase()
      dbB.overrideDb(dbB.client!!, dbA.db!!)
   }

   fun getFirstDb():AbstractMongoDb<T>{
      return dbA
   }

   fun getSecondDb():AbstractMongoDb<S>{
      return dbB
   }
}

