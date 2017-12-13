package bz.stewart.bracken.rest

class Main {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            EnvProperties.values()
                    .filter { it.required && it.isEmpty() }
                    .forEach { error("Missing required java property: ${it.propName}") }
            RestServiceRunner().run()
        }
    }
}

//this isn't registering, weird
//fun main(args: Array<String>) {
//    RestServiceRunner().run()
//}