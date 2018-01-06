package bz.stewart.bracken.web

import bz.stewart.bracken.web.conf.EnvironmentProperties
import bz.stewart.bracken.shared.conf.FileProperties
import bz.stewart.bracken.web.conf.WebDefaultProperties
import bz.stewart.bracken.web.conf.asProperties
import bz.stewart.bracken.web.conf.validateRequiredProperties

fun main(args: Array<String>) {
    EnvironmentProperties.values()
            .filter { it.required && it.isEmpty() }
            .forEach { error("Missing required java property: ${it.propName}") }
    val props = FileProperties<WebDefaultProperties>(asProperties(WebDefaultProperties.values()))
    println("Loading main properties from: ${EnvironmentProperties.WEB_PROPS_FILE.getOrDefault()}")
    props.loadFile(EnvironmentProperties.WEB_PROPS_FILE.getOrDefault())
    validateRequiredProperties(props.properties, WebDefaultProperties.values())

    ServiceRunner(MainSparkConfig(props)).run()
}