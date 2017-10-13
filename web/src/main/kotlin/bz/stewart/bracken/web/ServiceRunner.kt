package bz.stewart.bracken.web

import bz.stewart.bracken.web.html.MainPage
import bz.stewart.bracken.web.view.BillWebView
import bz.stewart.bracken.web.view.PrintInputView
import spark.Spark

class ServiceRunner {
    fun run() {
        Spark.staticFileLocation("/static")
        Spark.get("/") { _, _ ->
            MainPage(PrintInputView("pizza")).render()
        }
        Spark.get("/respond/:id") { req, _ ->
            MainPage(PrintInputView(req.params("id"))).render()
        }
        Spark.get("/bill/:id") { req, _ ->
            MainPage(BillWebView(req.params("id"))).render()
        }
    }
}

