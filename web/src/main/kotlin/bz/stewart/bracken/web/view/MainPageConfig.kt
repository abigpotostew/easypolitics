package bz.stewart.bracken.web.view

import bz.stewart.bracken.web.HtmlConstants
import bz.stewart.bracken.web.html.PageConfig
import bz.stewart.bracken.web.html.config.BasicConfig
import bz.stewart.bracken.web.html.config.TagConfiguration
import kotlinx.html.HTMLTag
import kotlinx.html.LINK
import kotlinx.html.META
import kotlinx.html.SCRIPT
import kotlinx.html.TITLE

class MainPageConfig : PageConfig{
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
            Pair("href", "/mainstyle.css")
        ))
        return setOf(bootStrap, stylesheet)
    }

    override fun getTitle(): TagConfiguration<TITLE> {
        return BasicConfig(TITLE::class, mapOf(Pair("__content","pizza")))
    }

    override fun getEndBodyScripts(): Set<TagConfiguration<SCRIPT>> {
        return emptySet()
    }
}