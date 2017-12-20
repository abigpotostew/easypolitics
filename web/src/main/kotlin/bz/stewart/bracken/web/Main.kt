package bz.stewart.bracken.web

import bz.stewart.bracken.web.conf.EnvironmentProperties
import bz.stewart.bracken.web.conf.MainProperties
import bz.stewart.bracken.web.conf.WebProperties
import bz.stewart.bracken.web.conf.asDefault

fun main(args: Array<String>) {
    EnvironmentProperties.values()
            .filter { it.required && it.isEmpty() }
            .forEach { error("Missing required java property: ${it.propName}") }
    val props = MainProperties(EnvironmentProperties.PROGRAM_PROPS_FILE.propName, asDefault(WebProperties.values()))
    ServiceRunner(MainSparkConfig()).run()

}