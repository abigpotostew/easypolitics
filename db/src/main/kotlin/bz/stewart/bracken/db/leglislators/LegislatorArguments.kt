package bz.stewart.bracken.db.leglislators

import bz.stewart.bracken.db.args.ClientConnectionArgs
import java.io.File

/**
 * Created by stew on 6/4/17.
 */
interface LegislatorArguments : ClientConnectionArgs {
    val files: MutableList<File>
    val testMode: Boolean
    val resetMode: Boolean
    var socialFile: File?
}