package bz.stewart.bracken.db.bill

import bz.stewart.bracken.db.DbRuntime
import bz.stewart.bracken.db.args.AbstractClientArgs
import bz.stewart.bracken.db.emptyDbRuntime
import com.xenomachina.argparser.ArgParser
import java.io.File

/**
 * Immutable
 * Created by stew on 3/10/17.
 */
class BillArguments(parser: ArgParser) : AbstractClientArgs(parser) {
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
}

enum class RuntimeMode {
    NONE, RESET, UPDATE;

    fun getDbRuntime(args: BillArguments): DbRuntime {
        return when (this) {
            NONE -> emptyDbRuntime()
            RESET, UPDATE -> SetupBillRuntime(
                args)
        }
    }

}