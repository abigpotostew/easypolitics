package bz.stewart.bracken.web.html

import bz.stewart.bracken.web.HtmlConstants
import bz.stewart.bracken.web.html.config.BasicConfig
import bz.stewart.bracken.web.html.config.HtmlConfiguration
import bz.stewart.bracken.web.html.config.TagConfiguration
import kotlinx.html.HTMLTag
import kotlinx.html.LINK
import kotlinx.html.META

@Suppress("UNCHECKED_CAST")
class MainPage(private val content: ViewTemplate) : WebPage {
    override fun render():String{

        val metas = getMetas()
        val links = getLinks()
        val webConfig = HtmlConfiguration()
        return WebsiteSkeleton(content=content, config = webConfig).render()
    }

    fun getLinks():Set<TagConfiguration<HTMLTag>>{
        val bootStrap = BasicConfig(LINK::class, mapOf(
            Pair("rel",HtmlConstants.STYLESHEET.get()),
            Pair("href", "https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css"),
            Pair("integrity", "sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ"),
            Pair("crossorigin", "anonymous")
        ))as TagConfiguration<HTMLTag>
        val stylesheet = BasicConfig(LINK::class, mapOf(
            Pair("href", "/mainstyle.css")
        ))as TagConfiguration<HTMLTag>
        return setOf(bootStrap, stylesheet)
    }

    fun getMetas():Set<TagConfiguration<HTMLTag>>{
        val charsetUpper = BasicConfig(META::class, mapOf(Pair("charset", HtmlConstants.UTF8.get()))) as TagConfiguration<HTMLTag>
        val charsetLower = BasicConfig(META::class, mapOf(Pair("charset", HtmlConstants.utf8.get()))) as TagConfiguration<HTMLTag>
        val pizza = BasicConfig(META::class, mapOf(Pair("name", "pizza"))) as TagConfiguration<HTMLTag>
        val viewport = BasicConfig(META::class, mapOf(Pair("name", "viewport"), Pair("content", "width=device-width, initial-scale=1, shrink-to-fit=no"))) as TagConfiguration<HTMLTag>
        return setOf(charsetUpper, charsetLower, pizza, viewport)
    }

    fun <T> union(vararg sets:Set<T>):Set<T>{
        val out = mutableSetOf<T>()
        for (set in sets){
            out.addAll(set)
        }
        return out
    }
}