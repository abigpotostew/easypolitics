package bz.stewart.bracken.db.leglislators

import com.xenomachina.argparser.ArgParser
import com.xenomachina.argparser.default
import java.io.File

/**
 * Created by stew on 5/21/17.
 */
class ParsedArguments(parser: ArgParser) : LegislatorArguments {
    override val files by parser.adding("-f", "--file", help = "Path(s) to legislator json files.") {
        File(this)
    }
    val socialMediaFiles by parser.storing("-s", "--social", help = "Path to social media json file.").default("")
    override val testMode by parser.flagging("-t", "--test", help = "Turns on test run mode. No data will be written.")
    override val dbName: String by parser.storing("-b", "--database", help = "Name of db to write to.")

    override val hostname: String? by parser.storing("--host", help = "Hostname for a remote db connection.").default(
            null)
    override val port: String? by parser.storing("--port", help = "Port for a remote db connection.").default(null)
    override val username: String? by parser.storing("-u", "--user", help = "Username for db authentication.").default(
            null)
    override val password: String? by parser.storing("-p", "--pass", help = "Password for db authentication.").default(
            null)

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

        if (this.hostname != null && (this.username == null || this.password == null)) {
            return "Username (-u) and password (-p) are required when specifying the host (--host)"
        }

        return null
    }

    private fun validJsonFile(file: java.io.File): Boolean {
        return file.isFile && file.canRead() && (file.extension.equals("json") || file.extension.equals("jsn"))
    }
}