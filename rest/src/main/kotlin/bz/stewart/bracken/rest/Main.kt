package bz.stewart.bracken.rest

import bz.stewart.bracken.shared.conf.FileProperties

class Main {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            EnvProperties.values()
                    .filter { it.required && it.isEmpty() }
                    .forEach { error("Missing required java property: ${it.propName}") }

            val props = FileProperties<RestDefaultProperties>(RestDefaultProperties.values().toList())
            props.loadFile(EnvProperties.REST_PROPS_FILE.getOrDefault())
            validateProperties(props)

            val runner = RestServiceRunner(props)
            runner.run()
        }

        fun validateProperties(props:FileProperties<RestDefaultProperties>) {
            val host = props.getProperty(RestDefaultProperties.DB_HOSTNAME)
            val user = props.getProperty(RestDefaultProperties.DB_USERNAME)
            val pass = props.getProperty(RestDefaultProperties.DB_PASSWORD)
            if (!host.isEmpty() && (user.isEmpty() || pass.isEmpty())){
                throw Exception("User and host properties are required when specifying the host.")
            }
        }
    }
}

//this isn't registering, weird
//fun main(args: Array<String>) {
//    RestServiceRunner().run()
//}