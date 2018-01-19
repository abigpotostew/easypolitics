package bz.stewart.bracken.db.args

import com.xenomachina.argparser.ArgParser
import com.xenomachina.argparser.default

abstract class AbstractClientArgs(parser: ArgParser) : ClientConnectionArgs {

    override val dbName: String by parser.storing("-b", "--database", help = "Name of db to write to.")
    override val hostname: String? by parser.storing("--host", help = "Hostname for a remote db connection.").default(
        null)
    override val port: String? by parser.storing("--port", help = "Port for a remote db connection.").default(null)
    override val username: String? by parser.storing("-u", "--user", help = "Username for db authentication.").default(
        null)
    override val password: String? by parser.storing("-p", "--pass", help = "Password for db authentication.").default(
        null)

    fun hasValidClientArgs():Boolean{
        return (this.hostname == null || (this.hostname != null && (this.username == null || this.password == null)))
    }

    fun getInvalidClientArgsMessage():String{
        return "Username (-u) and password (-p) are required when specifying the host (--host)"
    }
}

