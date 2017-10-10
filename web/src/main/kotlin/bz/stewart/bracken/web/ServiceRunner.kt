package bz.stewart.bracken.web

import spark.Spark

class ServiceRunner {
    fun run(){
        Spark.get("/hello") { req, res -> "Hello World" }
        Spark.get("/bill/:id") {
            req, res -> BillWebView(req.params("id")).render()
        }
    }
}

