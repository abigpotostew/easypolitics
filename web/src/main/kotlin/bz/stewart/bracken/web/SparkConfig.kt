package bz.stewart.bracken.web

import bz.stewart.bracken.web.conf.MainProperties
import bz.stewart.bracken.web.conf.WebDefaultProperties
import spark.Spark

interface SparkConfig {
    fun config()
}

class MainSparkConfig(private val mainProperties: MainProperties) : SparkConfig {
    override fun config() {
        if (mainProperties.getProperty(WebDefaultProperties.EXT_STATIC_FILES.propName) != "") {
            println("Using external static file location: " + mainProperties.getProperty(WebDefaultProperties.EXT_STATIC_FILES.propName))
            Spark.externalStaticFileLocation(mainProperties.getProperty(WebDefaultProperties.EXT_STATIC_FILES.propName))
        } else {
            Spark.staticFileLocation(mainProperties.getProperty(WebDefaultProperties.INT_STATIC_FILES.propName))
            mainProperties.getProperty(WebDefaultProperties.INT_STATIC_FILES.propName)
        }

        println("Using Port: ${mainProperties.getProperty(WebDefaultProperties.PORT.propName).toInt()}")
        Spark.port(mainProperties.getProperty(WebDefaultProperties.PORT.propName).toInt())

//        doAllPrints("")
//        doAllPrints("/")
//        doAllPrints(mainProperties.getProperty(WebDefaultProperties.INT_STATIC_FILES.propName))
//        doAllPrints(mainProperties.getProperty(WebDefaultProperties.EXT_STATIC_FILES.propName))
    }

    fun doAllPrints(path: String) {
        println("--------------------------------")
        println("Print Path: $path")
        printPath(path)
        printResPath(path)
        printTextPath(path)
        println("--------------------------------")
    }

    fun printPath(path: String) {
        println("Path is: $path")
        val rc = this::class.java.classLoader.getResource(path)
        val path = rc?.path
        println("class loader resource: $rc")
        println("class loader path: $path")
        println("--")
    }

    fun printResPath(path: String) {
        println("Path is: $path")
        val rc = this::class.java.getResource(path)
        val path = rc?.path
        println("res resource: $rc")
        println("res path: $path")
        println("--")
    }

    fun printTextPath(path: String) {
        println("Path is: $path")
        val rc = this::class.java.getResource(path)?.readText()
        println("class loader text: $path")
        println("--")
    }
}