package bz.stewart.bracken.db.database

/**
 * Created by stew.bracken on 3/7/17.
 */
abstract class Database<T : DbItem>(){

   //abstract fun openDatabase()
   abstract fun getDbName():String
   //fun getMongoDb(): MongoDatabase?

}

fun emptyDatabase():Database<out DbItem>{
   return object:Database<DbItem>(){
      override fun getDbName(): String {
         return ""
      }
   }
}