package bz.stewart.bracken.db.database

interface DatabaseClient<T> {
    fun createClient(): T
    val databaseName:String
}