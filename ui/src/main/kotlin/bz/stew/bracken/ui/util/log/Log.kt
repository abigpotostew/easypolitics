package bz.stew.bracken.ui.util.log


/**
 * Created by stew on 1/28/17.
 */

enum class LogLevel{
    SILENT,ERROR, WARNING, INFO, DEBUG;
}

class Log {
    private val messages = HashMap<LogLevel, HashSet<String>>()
    var logLevel = LogLevel.ERROR
    var saveMessages = false

    companion object LogCompanion {
        fun getLogLevel():LogLevel{
            return logs.logLevel
        }

        fun setLogLevel(lvl:LogLevel){
            logs.logLevel = lvl
        }

        fun warning(s: String) {
            logs.logMessage(LogLevel.WARNING,s)
        }
        fun debug(s: String) {
            logs.logMessage(LogLevel.DEBUG,s)
        }
        fun error(s: String) {
            logs.logMessage(LogLevel.ERROR,s)
        }
        fun info(s: String) {
            logs.logMessage(LogLevel.INFO,s)
        }

        fun warning(fn: ()->String) {
            logs.logMessage(LogLevel.WARNING,fn)
        }
        fun debug(fn: ()->String) {
            logs.logMessage(LogLevel.DEBUG,fn)
        }
        fun error(fn: ()->String) {
            logs.logMessage(LogLevel.ERROR,fn)
        }
        fun info(fn: ()->String) {
            logs.logMessage(LogLevel.INFO,fn)
        }
    }
    fun logMessage(msgLevel:LogLevel, msg:String){
        saveMessage(msgLevel,msg)
        if(logLevel!=LogLevel.SILENT && logLevel.ordinal >= msgLevel.ordinal) {
            println("Warning: " + msg)
        }
    }

    fun logMessage(msgLevel:LogLevel, msg:()->String){
        saveMessage(msgLevel,msg)
        if(logLevel!=LogLevel.SILENT && logLevel.ordinal >= msgLevel.ordinal) {
            println("Warning: " + msg())
        }
    }

    private fun saveMessage(msgLevel: LogLevel, msg: ()->String){
        if(saveMessages) {
            saveMessage(msgLevel,msg())
        }
    }

    private fun saveMessage(msgLevel: LogLevel, msg: String){
        if(saveMessages) {
            var set: HashSet<String>? = logs.messages.get(logLevel)
            if (set == null) {
                set = HashSet<String>()
            }
            set.add(msg)
            logs.messages.put(msgLevel, set)
        }
    }
}

private val logs:Log = Log()