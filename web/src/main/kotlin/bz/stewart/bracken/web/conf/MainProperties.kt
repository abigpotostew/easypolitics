package bz.stewart.bracken.web.conf

import java.io.BufferedReader
import java.io.FileReader
import java.util.Properties

class MainProperties(propPath: String, defaults: Properties) {

    constructor(propPath: String) : this(propPath, Properties())

    val props = Properties(defaults)

    init {
        val inStream = BufferedReader(FileReader(propPath))
        props.load(inStream)
    }
}