package bz.stewart.bracken.web

import bz.stewart.bracken.shared.web.AppServices
import bz.stewart.bracken.web.html.WebsiteSkeleton
import bz.stewart.bracken.web.view.BrowseBillsView
import bz.stewart.bracken.web.view.MainPageConfig
import bz.stewart.bracken.web.view.PrintInputView
import bz.stewart.bracken.web.view.SingleBillView
import bz.stewart.bracken.web.view.bill.SingleBillConfig
import spark.Spark

class ServiceRunner {
    fun run() {
        printPath("")
        printPath("/")
        printResPath("")
        printResPath("/")
        printTextPath("")
        printTextPath("/")

        Spark.port(EnvProperties.PORT.getOrDefault().toInt())

        Spark.staticFileLocation("/static")
        Spark.get(AppServices.MAIN.path) { _, _ ->
            WebsiteSkeleton(PrintInputView("pizza"), MainPageConfig()).render()
        }
        Spark.get(AppServices.RESPOND.path) { req, _ ->
            WebsiteSkeleton(PrintInputView(req.params("id")), MainPageConfig()).render()
        }
        Spark.get(AppServices.SINGLE_BILL.path) { req, _ ->
            WebsiteSkeleton(SingleBillView(), SingleBillConfig(req.params("id"))).render()
        }
        Spark.get(AppServices.BROWSE_BILL.path) { _, _ ->
            WebsiteSkeleton(BrowseBillsView(), MainPageConfig()).render()
        }
        //unused
        Spark.get("/main.js") { _, response ->
            response.status(200)
            response.type("text/javascript")
            RequireJs(emptyArray())
            UiApiActions().mainAll().js
        }
    }
    fun printPath(path:String){
        println("Path is: $path")
        val rc = this::class.java.classLoader.getResource(path)
        val path = rc?.path
        println("class loader resource: $rc")
        println("class loader path: $path")
        println("--")
    }

    fun printResPath(path:String){
        println("Path is: $path")
        val rc = this::class.java.getResource(path)
        val path = rc?.path
        println("res resource: $rc")
        println("res path: $path")
        println("--")
    }

    fun printTextPath(path:String){
        println("Path is: $path")
        val rc = this::class.java.getResource(path).readText()
        println("class loader text: $path")
        println("--")
    }
}

