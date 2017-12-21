package bz.stewart.bracken.web.conf

import java.io.BufferedReader
import java.io.FileNotFoundException
import java.io.FileReader
import java.io.IOException
import java.lang.IllegalArgumentException
import java.util.Properties

class MainProperties(defaults: Properties = Properties()) {

    private val props = defaults

    fun loadFile(propPath: String) {
        try {
            val fileProps = Properties()
            val inStream = BufferedReader(FileReader(propPath))
            fileProps.load(inStream)
            inStream.close()
            this.props.putAll(fileProps) //merge the props with default
        } catch (e: FileNotFoundException) {
            throw Exception("Required main property path not found at '$propPath'")
        } catch (e: IOException) {
            throw Exception("Error reading from main property file.")
        } catch (e: IllegalArgumentException) {
            throw Exception("Malformed unicode escape appears in main property file.")
        }
    }

    fun validateRequired(propDefintions:Array<WebDefaultProperties>) {
        for (propDef in propDefintions){
            if (this.props.getProperty(propDef.propName) == null){
                throw Exception("Missing required main property '${propDef.name}'")
            }
        }
    }

    fun getProperty(propName: String): String {
        return this.props.getProperty(propName)
    }
}