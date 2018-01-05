package bz.stewart.bracken.db.debug

import java.io.PrintWriter
import java.io.StringWriter

class DebugUtils {
    companion object {
        fun stackTraceToString(e:Exception):String{
            val sw =  StringWriter()
            val pw = PrintWriter(sw)
            e.printStackTrace(pw)
            return sw.toString()
        }
    }
}