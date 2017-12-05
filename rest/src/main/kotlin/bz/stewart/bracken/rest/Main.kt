package bz.stewart.bracken.rest

import bz.stewart.bracken.rest.spark.RestServiceRunner
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper

class Main {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            RestServiceRunner().run()
        }
    }
}

//this isn't registering, weird
fun main(args: Array<String>) {
    RestServiceRunner().run()
}