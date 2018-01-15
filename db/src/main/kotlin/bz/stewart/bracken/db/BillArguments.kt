package bz.stewart.bracken.db

import com.xenomachina.argparser.ArgParser
import com.xenomachina.argparser.default
import java.io.File

/**
 * Immutable
 * Created by stew on 3/10/17.
 */
class BillArguments(parser: ArgParser) {
    val data by parser.storing("-d", "--data",
        help = "Path to the data folder from the bills script.") { File(this) }

    val test by parser.flagging("-t", "--test", help = "Test run, print change statistics but do not write to the DB.")

    val mode by parser.mapping(
        "--reset" to RuntimeMode.RESET,
        "--update" to RuntimeMode.UPDATE,
        help = "Runtime mode. (reset, update). (r) = reset mode, delete db and reload from files. (u) = update mode, update data that has a recent updated timestamp."
    )

    val onlyParseCongressNums by parser.adding(
        "-C",
        help = "Only parse these specified congress numbers, ie -C114 -C115 to only parse bills in congress 114 and 115")
    { this.toInt() }

    val dbName: String by parser.storing("-b", "--database", help = "The database name.")

    val hostname: String? by parser.storing("--host", help = "Hostname for a remote db connection.").default(null)
    val port: String? by parser.storing("--port", help = "Port for a remote db connection.").default(null)
    val username: String? by parser.storing("-u", "--user", help = "Username for db authentication.").default(null)
    val password: String? by parser.storing("-p", "--pass", help = "Password for db authentication.").default(null)
}

enum class RuntimeMode {
    NONE, RESET, UPDATE;

    fun getDbRuntime(args: BillArguments): DbRuntime {
        return when (this) {
            NONE -> emptyDbRuntime()
            RESET, UPDATE -> SetupBillRuntime(args)
        }
    }

}