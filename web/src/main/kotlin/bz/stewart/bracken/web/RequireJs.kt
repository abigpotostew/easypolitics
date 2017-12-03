package bz.stewart.bracken.web

import java.lang.StringBuilder

class RequireJs (val scripts:Array<ScriptSrcConstants>){

    fun text():String{
        //build out the require js format:
        val outBuilder = StringBuilder()
        outBuilder.append("requirejs.config({\nbaseUrl: '/lib',\n")
        //outBuilder.append("paths:{app:'..'},\n\")
//        for(script in scripts){
//            //outBuilder.append("${script.jsName}: '${script.src}',\n")
//        }
        outBuilder.append("});")

        outBuilder.append("\n\n")
        //outBuilder.append(UiApiActions().doSingleBillAction("pizza"))
        outBuilder.append(
                "requirejs(['kotlin','kotlinx-html-js', easypolitics-ui'],\n" +
                "function   (        uiApp) {\n" +
                "    console.log(\"easypolitics finished loading\");\n" +
                "});")

        return outBuilder.toString()
    }
}