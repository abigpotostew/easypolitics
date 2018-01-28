package bz.stewart.bracken.web

import bz.stewart.bracken.shared.web.AppServices
import bz.stewart.bracken.web.html.WebsiteSkeleton
import bz.stewart.bracken.web.service.WebContextBuilder
import bz.stewart.bracken.web.view.browse.BrowseBillsView
import bz.stewart.bracken.web.view.BootstrapPageConfig
import bz.stewart.bracken.web.view.PrintInputView
import bz.stewart.bracken.web.view.SingleBillView
import bz.stewart.bracken.web.view.home.HomeView
import bz.stewart.bracken.web.view.map.MapView
import bz.stewart.bracken.web.view.search.SearchView
import spark.Spark

class ServiceRunner(val config: SparkConfig, private val restUrl: String) {

    init {
        config.config()
    }

    fun run() {
        val contextBuilder = WebContextBuilder()
        Spark.get(AppServices.MAIN.absoluteUrlPath) { req, res ->
            WebsiteSkeleton(HomeView("pizza"), BootstrapPageConfig()).render(contextBuilder.build(req, res, AppServices.MAIN))
        }
        Spark.get(AppServices.RESPOND.absoluteUrlPath) { req, res ->
            WebsiteSkeleton(PrintInputView(req.params("id")), BootstrapPageConfig()).render(contextBuilder.build(req, res, AppServices.RESPOND))
        }
        Spark.get(AppServices.SINGLE_BILL.absoluteUrlPath) { req, res ->
            WebsiteSkeleton(SingleBillView(), BootstrapPageConfig()).render(contextBuilder.build(req, res, AppServices.SINGLE_BILL))
        }
        Spark.get(AppServices.BROWSE_BILL.absoluteUrlPath) { req, res ->
            WebsiteSkeleton(BrowseBillsView(), BootstrapPageConfig()).render(contextBuilder.build(req, res, AppServices.BROWSE_BILL))
        }
        Spark.get(AppServices.SEARCH.absoluteUrlPath) { req, res ->
            WebsiteSkeleton(SearchView(), BootstrapPageConfig()).render(contextBuilder.build(req, res, AppServices.BROWSE_BILL))
        }
        Spark.get(AppServices.MAP.absoluteUrlPath) { req, res ->
            WebsiteSkeleton(MapView(), BootstrapPageConfig()).render(contextBuilder.build(req, res, AppServices.MAP))
        }
        Spark.get(AppServices.SERVICE_URL.absoluteUrlPath) { _, response ->
            response.type("text/html")
            restUrl
        }
    }
}

