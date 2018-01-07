package bz.stewart.bracken.web

import bz.stewart.bracken.shared.conf.FileProperties
import bz.stewart.bracken.web.conf.EnvironmentProperties
import bz.stewart.bracken.web.conf.WebDefaultProperties

fun main(args: Array<String>) {
    EnvironmentProperties.values()
        .filter { it.required && it.isEmpty() }
        .forEach { error("Missing required java property: ${it.propName}") }
    val props = FileProperties<WebDefaultProperties>(WebDefaultProperties.values().toList())
    println("Loading main properties from: ${EnvironmentProperties.WEB_PROPS_FILE.getOrDefault()}")
    props.loadFile(EnvironmentProperties.WEB_PROPS_FILE.getOrDefault())
    if (props.hasMissingRequiredDef())
        throw props.getMissingPropertyException()

    ServiceRunner(MainSparkConfig(props)).run()
}