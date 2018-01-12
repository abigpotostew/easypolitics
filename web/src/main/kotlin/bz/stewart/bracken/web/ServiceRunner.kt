package bz.stewart.bracken.web

import bz.stewart.bracken.shared.web.AppServices
import bz.stewart.bracken.web.html.WebsiteSkeleton
import bz.stewart.bracken.web.service.WebContextBuilder
import bz.stewart.bracken.web.view.BrowseBillsView
import bz.stewart.bracken.web.view.MainPageConfig
import bz.stewart.bracken.web.view.PrintInputView
import bz.stewart.bracken.web.view.SingleBillView
import spark.Spark

class ServiceRunner(val config: SparkConfig, private val restUrl: String) {

    init {
        config.config()
    }

    fun run() {
        val contextBuilder = WebContextBuilder()
        Spark.get(AppServices.MAIN.absoluteUrlPath) { req, res ->
            WebsiteSkeleton(PrintInputView("pizza"), MainPageConfig()).render(contextBuilder.build(req, res, AppServices.MAIN))
        }
        Spark.get(AppServices.RESPOND.absoluteUrlPath) { req, res ->
            WebsiteSkeleton(PrintInputView(req.params("id")), MainPageConfig()).render(contextBuilder.build(req, res, AppServices.RESPOND))
        }
        Spark.get(AppServices.SINGLE_BILL.absoluteUrlPath) { req, res ->
            WebsiteSkeleton(SingleBillView(), MainPageConfig()).render(contextBuilder.build(req, res, AppServices.SINGLE_BILL))
        }
        Spark.get(AppServices.BROWSE_BILL.absoluteUrlPath) { req, res ->
            WebsiteSkeleton(BrowseBillsView(), MainPageConfig()).render(contextBuilder.build(req, res, AppServices.BROWSE_BILL))
        }
        Spark.get(AppServices.SERVICE_URL.absoluteUrlPath) { _, response ->
            response.type("text/html")
            restUrl
        }
    }
}

