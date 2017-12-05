package bz.stewart.bracken.rest.spark

import spark.Filter
import spark.Spark

/**
 * Current hacky cross origin sharing. Not to be used in production
 * Todo: Update cors for production
 */
class CorsFilter {

    private val corsHeaders: MutableMap<String, String> = mutableMapOf()

    init {
        corsHeaders.put("Access-Control-Allow-Methods", "GET,PUT,POST,DELETE,OPTIONS")
        corsHeaders.put("Access-Control-Allow-Origin", "*")
        corsHeaders.put("Access-Control-Allow-Headers",
                "Content-Type,Authorization,X-Requested-With,Content-Length,Accept,Origin,")
        corsHeaders.put("Access-Control-Allow-Credentials", "true")
    }

    fun apply() {
        Spark.after(Filter{ request, response ->
            corsHeaders.forEach({
                response.header(it.key, it.value)
            })
        })
    }
}