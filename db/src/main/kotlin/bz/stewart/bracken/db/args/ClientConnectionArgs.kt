package bz.stewart.bracken.db.args

interface ClientConnectionArgs {
    val dbName: String
    val hostname: String?
    val port: String?
    val username: String?
    val password: String?
}