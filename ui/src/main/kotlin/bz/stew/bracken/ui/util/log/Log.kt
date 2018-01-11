package bz.stew.bracken.ui.util.log

/**
 * Created by stew on 1/28/17.
 */

enum class LogLevel {
    SILENT, ERROR, WARNING, INFO, DEBUG;
}

interface Logger {
    fun getLogLevel(): LogLevel
    fun setLogLevel(level: LogLevel)
    fun error(s: String)
    fun error(fn: () -> String)
    fun warning(s: String)
    fun warning(fn: () -> String)
    fun info(s: String)
    fun info(fn: () -> String)
    fun debug(s: String)
    fun debug(fn: () -> String)
}

class PrintSaveLogger(logLevel: LogLevel = LogLevel.ERROR) : Logger {
    private val messages = HashMap<LogLevel, HashSet<String>>()
    private var logLevel = logLevel
    private var saveMessages = false

    override fun getLogLevel(): LogLevel {
        return this.logLevel
    }

    override fun setLogLevel(lvl: LogLevel) {
        this.logLevel = lvl
    }

    override fun warning(s: String) {
        this.logMessage(LogLevel.WARNING, s)
    }

    override fun debug(s: String) {
        this.logMessage(LogLevel.DEBUG, s)
    }

    override fun error(s: String) {
        this.logMessage(LogLevel.ERROR, s)
    }

    override fun info(s: String) {
        this.logMessage(LogLevel.INFO, s)
    }

    override fun warning(fn: () -> String) {
        this.logMessage(LogLevel.WARNING, fn)
    }

    override fun debug(fn: () -> String) {
        this.logMessage(LogLevel.DEBUG, fn)
    }

    override fun error(fn: () -> String) {
        this.logMessage(LogLevel.ERROR, fn)
    }

    override fun info(fn: () -> String) {
        this.logMessage(LogLevel.INFO, fn)
    }

    private fun logMessage(msgLevel: LogLevel, msg: String) {
        saveMessage(msgLevel, msg)
        if (logLevel != LogLevel.SILENT && logLevel.ordinal >= msgLevel.ordinal) {
            console.log("Warning: " + msg)
        }
    }

    private fun logMessage(msgLevel: LogLevel, msg: () -> String) {
        saveMessage(msgLevel, msg)
        if (logLevel != LogLevel.SILENT && logLevel.ordinal >= msgLevel.ordinal) {
            println("Warning: " + msg())
        }
    }

    private fun saveMessage(msgLevel: LogLevel, msg: () -> String) {
        if (saveMessages) {
            saveMessage(msgLevel, msg())
        }
    }

    private fun saveMessage(msgLevel: LogLevel, msg: String) {
        if (saveMessages) {
            var set: HashSet<String>? = this.messages.get(logLevel)
            if (set == null) {
                set = HashSet<String>()
            }
            set.add(msg)
            this.messages.put(msgLevel, set)
        }
    }
}

/**
 * For legacy log calls
 */
val Log:Logger = PrintSaveLogger()
