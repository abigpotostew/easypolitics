package bz.stewart.bracken.db.extension

import com.fasterxml.jackson.databind.ObjectMapper
import com.mongodb.client.MongoCollection
import com.mongodb.client.MongoDatabase

/**
 * Created by stew on 3/9/17.
 */
fun < T : Any> MongoDatabase.getCollectionRegular(collectionName: String, clazz:Class<T>): MongoCollection<T>
      = getCollection(collectionName, clazz)

fun <T : Any> ObjectMapper.readValueRegular(content: String,clazz:Class<T>): T {
   return this.readValue(content,clazz)

}