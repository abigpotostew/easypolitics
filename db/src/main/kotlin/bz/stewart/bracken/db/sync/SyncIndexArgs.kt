package bz.stewart.bracken.db.sync

import bz.stewart.bracken.db.args.AbstractClientArgs
import com.xenomachina.argparser.ArgParser

class SyncIndexArgs(parser: ArgParser) : AbstractClientArgs(parser) {
    val test by parser.flagging("-t",
        "--test",
        help = "Test run, print index information but make no changes to the database.")
}