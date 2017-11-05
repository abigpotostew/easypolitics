package bz.stewart.bracken.web

import bz.stewart.bracken.web.html.config.ExecuteJsScriptConfig
import bz.stewart.bracken.web.html.config.TagConfiguration
import kotlinx.html.SCRIPT

class UiApiActions {
    fun doBrowseAction(): TagConfiguration<SCRIPT> {
        val config = ExecuteJsScriptConfig(
                "requirejs(['kotlin', 'kotlinx-html-js', 'app/easypolitics-ui'],\n" +
                        "function   (        kotlin,   kotlinJsHtml, easypoliticsUi) {\n" +
                        "    (new easypoliticsUi.bz.stew.bracken.ui.api.BrowseRuntime()).execute();" +
                        "});"
        )
        return config
    }

    fun doSingleBillAction(billId: String): TagConfiguration<SCRIPT> {
        return ExecuteJsScriptConfig(
                "requirejs(['kotlin', 'kotlinx-html-js', 'app/easypolitics-ui'],\n" +
                        "function   (        kotlin,   kotlinJsHtml, easypoliticsUi) {\n" +
                        "   console.log(Easypolitics is Loaded.);\n" +
                        //"    (new easypoliticsUi.bz.stew.bracken.ui.api.SingleBillRuntime('yo')).execute();" +
                        "});"
        )
    }


    fun mainAll():JsBlock{
        return JsBlock(RequireJs(emptyArray()).text())
    }
}