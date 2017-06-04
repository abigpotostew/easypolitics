package bz.stewart.bracken.db.database

/**
 * Created by stew on 3/9/17.
 */
interface CollectionWriter<T : DbItem, B : Database<T>> {
   fun write(element:T, collection:String, db: B)
   fun drop(collection: String, db: B)
}

fun <T: DbItem,B : Database<T>> emptyDatabaseWriter(): CollectionWriter<T,B> {
   return object:CollectionWriter<T,B>{
      override fun write(element: T, collection:String, db:B) {
      }

      override fun drop(collection: String, db: B) {
      }
   }
}