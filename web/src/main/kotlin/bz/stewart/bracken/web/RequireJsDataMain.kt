package bz.stewart.bracken.web

import bz.stewart.bracken.web.html.ConstantText

enum class RequireJsDataMain(val src:String, val dataMain:String):ConstantText {

    REQUIREJS_APP("/lib/require.js", "/main.js"),
    ;

    override fun get(): String {
        return this.src
    }
}