package bz.stewart.bracken.web

import bz.stewart.bracken.shared.web.AppServices
import bz.stewart.bracken.web.html.WebsiteSkeleton
import bz.stewart.bracken.web.view.BrowseBillsView
import bz.stewart.bracken.web.view.MainPageConfig
import bz.stewart.bracken.web.view.PrintInputView
import bz.stewart.bracken.web.view.SingleBillView
import bz.stewart.bracken.web.view.bill.SingleBillConfig
import spark.Spark

class ServiceRunner(val config: SparkConfig, private val restUrl:String) {

    init {
        config.config()
    }

    fun run() {
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
        Spark.get(AppServices.SERVICE_URL.path) { _, response ->
            response.type("text/html")
            restUrl
        }
    }
}

