package bz.stewart.bracken.web

import bz.stewart.bracken.web.conf.WebProperties
import spark.Spark
import java.util.Properties

interface SparkConfig {
    fun config()
}

class MainSparkConfig(private val webProps: Properties) : SparkConfig {
    override fun config() {
        if (webProps.getProperty(WebProperties.EXT_STATIC_FILES.propName) != "") {
            println("Using external static file location: " + webProps.getProperty(WebProperties.EXT_STATIC_FILES.propName))
            Spark.externalStaticFileLocation(webProps.getProperty(WebProperties.EXT_STATIC_FILES.propName))
        } else {
            Spark.staticFileLocation(webProps.getProperty(WebProperties.INT_STATIC_FILES.propName))
            webProps.getProperty(WebProperties.INT_STATIC_FILES.propName)
        }

        Spark.port(WebProperties.PORT.getOrDefault().toInt())

        doAllPrints("")
        doAllPrints("/")
        doAllPrints(webProps.getProperty(WebProperties.INT_STATIC_FILES.propName))
        doAllPrints(webProps.getProperty(WebProperties.EXT_STATIC_FILES.propName))
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