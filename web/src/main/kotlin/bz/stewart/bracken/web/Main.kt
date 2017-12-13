package bz.stewart.bracken.web

import java.io.File

fun main(args: Array<String>) {
    EnvProperties.values()
            .filter { it.required && it.isEmpty() }
            .forEach { error("Missing required java property: ${it.propName}") }
    println("user.dir: "+System.getProperty("user.dir"))
    val file = File("")
    println(file.getAbsolutePath())
    println(file.path)

    ServiceRunner().run()

}