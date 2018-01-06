package bz.stewart.bracken.shared.conf

import java.io.BufferedReader
import java.io.FileNotFoundException
import java.io.FileReader
import java.io.IOException
import java.lang.IllegalArgumentException
import java.util.Properties

class FileProperties<T : Property>(defaults: Properties = Properties()) {

    val properties = defaults

    fun loadFile(propPath: String) {
        try {
            val fileProps = Properties()
            val inStream = BufferedReader(FileReader(propPath))
            fileProps.load(inStream)
            inStream.close()
            this.properties.putAll(fileProps) //merge the props with default
        } catch (e: FileNotFoundException) {
            throw Exception("Required main property path not found at '$propPath'")
        } catch (e: IOException) {
            throw Exception("Error reading from main property file.")
        } catch (e: IllegalArgumentException) {
            throw Exception("Malformed unicode escape appears in main property file.")
        }
    }

    fun getProperty(propName: String): String {
        return this.properties.getProperty(propName)
    }

    fun getProperty(prop: T): String {
        return getProperty(prop.propName)
    }
}