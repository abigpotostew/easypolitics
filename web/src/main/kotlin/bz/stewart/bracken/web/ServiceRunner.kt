package bz.stewart.bracken.web

import bz.stewart.bracken.web.html.WebsiteSkeleton
import bz.stewart.bracken.web.view.BillWebView
import bz.stewart.bracken.web.view.MainPageConfig
import bz.stewart.bracken.web.view.PrintInputView
import spark.Spark

class ServiceRunner {
    fun run() {
        Spark.staticFileLocation("/static")
        Spark.get("/") { _, _ ->
            WebsiteSkeleton(PrintInputView("pizza"), MainPageConfig()).render()
        }
        Spark.get("/respond/:id") { req, _ ->
            WebsiteSkeleton(PrintInputView(req.params("id")), MainPageConfig()).render()
        }
        Spark.get("/bill/:id") { req, _ ->
            WebsiteSkeleton(BillWebView(req.params("id")), MainPageConfig()).render()
        }
    }
}

