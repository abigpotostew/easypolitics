package bz.stewart.bracken.db.database

import bz.stewart.bracken.db.database.mongo.CollectionWriter

/**
 * Created by stew on 3/12/17.
 */
interface  Transaction <T : DbItem, D:Database<T>> {
   fun setWriter(writer: CollectionWriter<T, D>)
   fun beginTransaction()
   fun execute()
   fun endTransaction()
   fun abort(msg:String="")
}