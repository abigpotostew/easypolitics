package bz.stewart.bracken.db.leglislators

import java.io.File

/**
 * Created by stew on 6/4/17.
 */
interface LegislatorArguments {
    val files: MutableList<File>
    val testMode: Boolean
    val dbName: String
    var socialFile: File?

    val hostname: String?
    val port: String?
    val username: String?
    val password: String?
}