package bz.stewart.bracken.web

import spark.Spark

class ServiceRunner {
    fun run(){
        Spark.staticFileLocation("/static")
        Spark.get("/hello") { req, res -> "Hello World" }
        Spark.get("/bill/:id") {
            req, res -> WebsiteSkeleton(BillWebView(req.params("id"))).render()

        }
    }
}

