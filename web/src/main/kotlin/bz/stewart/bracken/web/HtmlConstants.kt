package bz.stewart.bracken.web

import bz.stewart.bracken.web.html.ConstantText

enum class HtmlConstants(private val value:String): ConstantText {

    TEXT_JAVASCRIPT("text/javascript"),
    UTF8("UTF-8"),
    utf8("utd-8"),
    STYLESHEET("stylesheet")
    ;

    override fun get():String{
        return value
    }
}

