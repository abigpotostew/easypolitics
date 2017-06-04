package bz.stewart.bracken.db

import bz.stewart.bracken.db.bill.data.Bill
import bz.stewart.bracken.db.database.DbItem
import com.xenomachina.argparser.ArgParser
import java.io.File

/**
 * Immutable
 * Created by stew on 3/10/17.
 */
class BillArguments(parser: ArgParser) {
   val data by parser.storing("-d", "--data",
                              help = "Path to the data folder from the bills script.") { File(this) }

//   val testMode by parser.flagging("-t", "--test",
//                                   help = "Runtime mode. (r, u, [t]).  (t) = test mode, display stats on what will happen, but don't actually write anything into the database.")
//   val resetMode by parser.flagging("-r", "--reset",
//                                    help = "Runtime mode. ([r], u, t).  (r) = reset mode, delete db and reload from files.")
//   val updateMode by parser.storing("-u", "--update",
//                                    help = "Runtime mode. (r, [u], t).  (u) = update mode, update data that has a recent updated timestamp.")

   val test by parser.flagging("-t", "--test", help = "Test run, print change statistics but do not write to the DB.")

   val mode by parser.mapping(
         "--reset" to RuntimeMode.RESET,
         "--update" to RuntimeMode.UPDATE,
         help = "Runtime mode. (reset, update, test).  (t) = test mode, display stats on what will happen, but don't actually write anything into the database. (r) = reset mode, delete db and reload from files. (u) = update mode, update data that has a recent updated timestamp."
                             )//.default(RuntimeMode.NONE)

   val onlyParseCongressNums by parser.adding(
         "-C",
         help = "Only parse these specified congress numbers, ie -C114 -C115 to only parse bills in congress 114 and 115")
         { this.toInt() }

   val dbName:String by parser.storing("-b", "--database", help="The database name.")
}

enum class RuntimeMode {
   NONE, RESET, UPDATE;

   fun getDbRuntime(args: BillArguments): DbRuntime<out DbItem> {
      return when (this) {
         NONE -> emptyDbRuntime()
         RESET, UPDATE -> SetupDbRuntime<Bill>(args)
      }
   }

}