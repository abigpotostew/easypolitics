package bz.stewart.bracken.rest.conf

import bz.stewart.bracken.db.database.DatabaseClient
import bz.stewart.bracken.db.database.mongo.DefaultMongoClient
import bz.stewart.bracken.db.database.mongo.RemoteMongoClient
import bz.stewart.bracken.shared.conf.FileProperties
import com.mongodb.MongoClient

class RestRuntimeContext(override val servingPort: Int,
                         override val client: DatabaseClient<MongoClient>) : RuntimeContext {

   companion object {
      fun builder(): Builder {
         return Builder()
      }
   }

   class Builder {
      private var port: Int = 0
      private var client: DatabaseClient<MongoClient>? = null

      fun servingPort(properties: FileProperties<RestDefaultProperties>): Builder {
         this.port = properties.getProperty(RestDefaultProperties.EXT_PORT).toInt()
         return this
      }

      fun setClientFromProps(properties: FileProperties<RestDefaultProperties>): Builder {
         val dbName = properties.getProperty(RestDefaultProperties.DB_NAME)
         val dbHost = properties.getProperty(RestDefaultProperties.DB_HOSTNAME)
         val dbPortString = properties.getProperty(RestDefaultProperties.DB_PORT)
         val dbPort = if (dbPortString.isEmpty()) null else dbPortString.toInt()
         val dbUser = properties.getProperty(RestDefaultProperties.DB_USERNAME)
         val dbPass = properties.getProperty(RestDefaultProperties.DB_PASSWORD)
         this.client = if (dbHost.isNotEmpty() && dbUser.isNotEmpty() && dbPass.isNotEmpty()) {
            RemoteMongoClient(dbHost, dbPort, dbName, dbUser, dbPass.toCharArray())
         } else {
            DefaultMongoClient(dbName)
         }
         return this
      }

      fun build(): RestRuntimeContext {
         return RestRuntimeContext(this.port, this.client!!)
      }
   }
}