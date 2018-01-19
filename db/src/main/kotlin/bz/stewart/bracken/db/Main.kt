package bz.stewart.bracken.db

import bz.stewart.bracken.db.bill.BillArguments
import bz.stewart.bracken.db.file.DataWalk
import bz.stewart.bracken.db.file.parse.EmptyParser
import bz.stewart.bracken.db.leglislators.LegislatorRuntime
import bz.stewart.bracken.db.leglislators.ParsedArguments
import bz.stewart.bracken.db.sync.SyncAllIndex
import bz.stewart.bracken.db.sync.SyncIndexArgs
import com.xenomachina.argparser.ArgParser
import com.xenomachina.argparser.mainBody
import mu.KotlinLogging
import java.io.File
import kotlin.system.exitProcess


/**
 * Created by stew on 3/6/17.
 */
private val logger = KotlinLogging.logger {}


fun main(argv: Array<String>) = mainBody("easypolitics-db") {
    val mode = resolveMainMode(argv)
    if (mode == MainMode.NONE) {
        hardFail("First arg must be -b (bill mode) or -l (legislator mode).")
    }
    logger.info(
        { "Hello. Easy Politics Database Utility-- build and update bill and legislator databases. Currently using mongodb." })
    logger.info({ "Executing in $mode mode." })
    mode.mainRun(removeModeArg(argv))
}

fun removeModeArg(argv: Array<String>): Array<String> {
    val argvlist: MutableList<String> = argv.toMutableList()
    argvlist.removeAt(0)
    return argvlist.toTypedArray()
}

fun hardFail(msg: String) {
    logger.error(msg)
    exitProcess(0)
}

enum class MainMode(val flag: String) {
    NONE("") {
        override fun mainRun(argv: Array<String>) {
            //do nothing
        }
    },
    BILL("-b") {
        override fun mainRun(argv: Array<String>) {
            BillArguments(ArgParser(argv)).run {
                //logger.info("Easy Politics Database.")
                logger.info(
                    { "Starting Bill mode with options: mode = $mode, data = $data, db = $dbName" })

                val runtime = mode.getDbRuntime(this)
                try {
                    runtime.validateArgs()
                } catch (e: IllegalArgumentException) {
                    hardFail(
                        "Input error: ${e.message}. Rerun with --help to see required parameters")
                }
                runtime.run()
            }
        }
    },
    LEGISLATOR("-l") {
        override fun mainRun(argv: Array<String>) {
            ParsedArguments(ArgParser(argv)).run {
                logger.info({ "Starting Legislator mode with options: $this" })
                val legs = LegislatorRuntime(this)
                val validationMessage = invalidArgsMessage()
                if (validationMessage != null) {
                    val msg = "App args incorrect: $validationMessage"
                    logger.error(msg)
                    error(msg)
                }
                legs.run()
            }
        }
    },
    INDEX("-i") {
        override fun mainRun(argv: Array<String>) {
            SyncIndexArgs(ArgParser(argv)).run {
                logger.info({ "Starting Index Sync mode. Will create any missing index." })
                SyncAllIndex(this).run()
            }
        }
    };

    abstract fun mainRun(argv: Array<String>)
}

private fun resolveMainMode(argv: Array<String>): MainMode {
    if (argv.isEmpty()) {
        return MainMode.NONE
    }
    val modeFlag = argv[0]
    for (mode in MainMode.values()) {
        if (modeFlag == mode.flag) {
            return mode
        }
    }
    return MainMode.NONE
}

fun testTraversal() {
    DataWalk(File(
        "/Users/stew/Documents/Code/github/congress/data"
        //"/Users/stew.bracken/Documents/Code/python/congress-master/data"
    ), listOf("115"),
        EmptyParser()
        //bz.bracken.stewart.db.file.parse.DebugParser()
    )//.traverse()
}