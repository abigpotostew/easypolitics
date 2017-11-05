package bz.stewart.bracken.web

import bz.stewart.bracken.web.html.WebsiteSkeleton
import bz.stewart.bracken.web.view.BrowseBillsView
import bz.stewart.bracken.web.view.MainPageConfig
import bz.stewart.bracken.web.view.PrintInputView
import bz.stewart.bracken.web.view.SingleBillView
import bz.stewart.bracken.web.view.bill.SingleBillConfig
import bz.stewart.bracken.web.view.browse.BrowsePageConfig
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
            WebsiteSkeleton(SingleBillView(), SingleBillConfig(req.params("id"))).render()
        }
        Spark.get("/browse") { _, _ ->
            WebsiteSkeleton(BrowseBillsView(), BrowsePageConfig()).render()
        }
        Spark.get("/browse.js") { _,response ->
            response.status(200)
            response.type("text/javascript")
            UiApiActions().doBrowseAction()
            //RequireJs(arrayOf(ScriptSrcConstants.KOTLIN_JS_FROM_UI_LOCAL, ScriptSrcConstants.KOTLINX_JS_FROM_UI_LOCAL, ScriptSrcConstants.EASYPOLITICS_UI_JS_FROM_UI_LOCAL), "EasyPoliticsExecute").text()
        }
        Spark.get("/bill.js") { _,response ->
            response.status(200)
            response.type("text/javascript")
            UiApiActions().doSingleBillAction("pizza")//this won't accept input. the initial html request needs to place this code in the html, NOT as a separate request
            //RequireJs(arrayOf(ScriptSrcConstants.KOTLIN_JS_FROM_UI_LOCAL, ScriptSrcConstants.KOTLINX_JS_FROM_UI_LOCAL, ScriptSrcConstants.EASYPOLITICS_UI_JS_FROM_UI_LOCAL), "EasyPoliticsExecute").text()
        }
    }
}

