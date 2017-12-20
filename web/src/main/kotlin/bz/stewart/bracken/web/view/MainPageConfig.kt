package bz.stewart.bracken.web.view

import bz.stewart.bracken.web.HtmlConstants
import bz.stewart.bracken.web.RequireJsDataMain
import bz.stewart.bracken.web.ScriptSrcConstants
import bz.stewart.bracken.web.html.PageConfig
import bz.stewart.bracken.web.html.config.BasicConfig
import bz.stewart.bracken.web.html.config.ScriptConfig
import bz.stewart.bracken.web.html.config.ScriptDataMainConfig
import bz.stewart.bracken.web.html.config.TagConfiguration
import bz.stewart.bracken.web.html.config.TitleConfig
import kotlinx.html.LINK
import kotlinx.html.META
import kotlinx.html.SCRIPT
import kotlinx.html.TITLE

open class MainPageConfig : PageConfig{
    override fun getMetas(): Set<TagConfiguration<META>> {
        val charsetUpper = BasicConfig(META::class, mapOf(Pair("charset", HtmlConstants.UTF8.get())))
        val charsetLower = BasicConfig(META::class, mapOf(Pair("charset", HtmlConstants.utf8.get())))
        val pizza = BasicConfig(META::class, mapOf(Pair("name", "pizza")))
        val viewport = BasicConfig(META::class, mapOf(Pair("name", "viewport"), Pair("content", "width=device-width, initial-scale=1, shrink-to-fit=no")))
        return setOf(charsetUpper, charsetLower, pizza, viewport)
    }

    override fun getLinks(): Set<TagConfiguration<LINK>> {
        val bootStrap = BasicConfig(LINK::class, mapOf(
            Pair("rel",HtmlConstants.STYLESHEET.get()),
            Pair("href", "https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css"),
            Pair("integrity", "sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ"),
            Pair("crossorigin", "anonymous")
        ))
        val stylesheet = BasicConfig(LINK::class, mapOf(
            Pair("rel",HtmlConstants.STYLESHEET.get()),
            Pair("href", "/mainstyle.css")

        ))
        return setOf(bootStrap, stylesheet)
    }

    override fun getTitle(): TagConfiguration<TITLE> {
        return TitleConfig("pizza")
    }

    override fun getBeginBodyScripts(): Set<TagConfiguration<SCRIPT>> {
        return setOf(
                //ScriptConfig(ScriptSrcConstants.JQUERY_MIN_EXT),
                //ScriptConfig(ScriptSrcConstants.JQUERY_ACTUAL_LOCAL),
                ScriptConfig(ScriptSrcConstants.VELOCITY_EXT),
                ScriptConfig(ScriptSrcConstants.VELOCITY_UI_MIN_EXT),
                ScriptConfig(ScriptSrcConstants.TETHER_MIN_EXT)
                //ScriptConfig(ScriptSrcConstants.BOOSTRAP_MIN_EXT)

                //ScriptConfig(ScriptSrcConstants.BOOSTRAP_MIN_EXT)
                //ScriptDataMainConfig(RequireJsDataMain.REQUIREJS_APP)
        )
    }

    override fun getEndBodyScripts(): Set<TagConfiguration<SCRIPT>> {
        val out = mutableSetOf<TagConfiguration<SCRIPT>>()
        for(enum in ScriptSrcConstants.values().iterator()){
            //out.add(ScriptConfig(enum))
        }
        out.add(ScriptConfig(ScriptSrcConstants.UIAPP_LOCAL_TMP))
        return out
        //emptySet()//out
    }
}