package bz.stewart.bracken.db.leglislators

import bz.stewart.bracken.db.args.AbstractClientArgs
import com.xenomachina.argparser.ArgParser
import com.xenomachina.argparser.default
import java.io.File

/**
 * Created by stew on 5/21/17.
 */
class ParsedArguments(parser: ArgParser) : AbstractClientArgs(parser), LegislatorArguments {
    override val files by parser.adding("-f", "--file", help = "Path(s) to legislator json files.") {
        File(this)
    }
    val socialMediaFiles by parser.storing("-s", "--social", help = "Path to social media json file.").default("")
    override val testMode by parser.flagging("-t", "--test", help = "Turns on test run mode. No data will be written.")
    //override val dbName: String by parser.storing("-b", "--database", help = "Name of db to write to.")

    override var socialFile: File? = null

    override fun toString(): String {
        return "TestMode: $testMode, dbName: $dbName, files: $files, social-files: $socialMediaFiles"
    }

    fun invalidArgsMessage(): String? {
        for (f: java.io.File in files) {
            if (!validJsonFile(f)) {
                return "File '$f' is unreadable, or is not a .json file. Only accepts files ending with .json."
            }
        }

        if (dbName.isBlank()) {
            return "Database name is empty."
        }

        if (!socialMediaFiles.isBlank()) {
            val _socialFile = java.io.File(socialMediaFiles)
            if (!validJsonFile(_socialFile)) {
                return "Social file is unreadable or not a json file: $socialMediaFiles"
            }
            socialFile = _socialFile
        }

        if (!hasValidClientArgs()) {
            return getInvalidClientArgsMessage()
        }

        return null
    }

    private fun validJsonFile(file: java.io.File): Boolean {
        return file.isFile && file.canRead() && (file.extension.equals("json") || file.extension.equals("jsn"))
    }
}