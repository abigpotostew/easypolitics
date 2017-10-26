package bz.stewart.bracken.web

import java.lang.StringBuilder

class RequireJs (val scripts:Array<ScriptSrcConstants>, val callbackFuncName:String){

    fun text():String{
        //build out the require js format:
        val outBuilder = StringBuilder("requirejs.config({baseUrl: '/lib/lib',paths:{app:'..'\n")
        for(script in scripts){
            //outBuilder.append("${script.jsName}: '${script.src}',\n")
        }
        outBuilder.append("}});")

        outBuilder.append("\n\n")
        //outBuilder.append(UiApiActions().doSingleBillAction("pizza"))
        outBuilder.append("requirejs(['kotlin', 'kotlinx-html-js', 'app/easypolitics-ui'],\n" +
                "function   (        kotlin,   kotlinJsHtml, easypoliticsUi) {\n" +
                "    (new easypoliticsUi.bz.stew.bracken.ui.api.SingleBillRuntime('yo')).execute();" +
                "});")

        return outBuilder.toString()
    }
}