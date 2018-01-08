package bz.stewart.bracken.rest.conf

import bz.stewart.bracken.db.database.DatabaseClient
import com.mongodb.MongoClient

interface RuntimeContext {
    val servingPort: Int
    val client: DatabaseClient<MongoClient>
}