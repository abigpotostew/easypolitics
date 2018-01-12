package bz.stewart.bracken.web.html

import bz.stewart.bracken.web.service.WebPageContext
import kotlinx.html.body
import kotlinx.html.head
import kotlinx.html.html
import kotlinx.html.lang
import kotlinx.html.link
import kotlinx.html.meta
import kotlinx.html.script
import kotlinx.html.stream.createHTML
import kotlinx.html.title

class WebsiteSkeleton(private val content: ViewRender? = null, private val config: PageConfig) : WebPage {
    override fun render(context: WebPageContext): String {
        return createHTML().html {
            lang = "en"
            head {
                for (conf in config.getMetas()) {
                    meta {
                        conf.apply(this)
                    }
                }
                for (conf in config.getLinks()) {
                    link {
                        conf.apply(this)
                    }
                }
                title {
                    config.getTitle().apply(this)
                }
                for(conf in config.getBeginBodyScripts()){
                    script{
                        conf.apply(this)
                    }
                }
            }
            body {
                content?.renderIn(this, context)
                for (scriptConfig in config.getEndBodyScripts()) {
                    script {
                        scriptConfig.apply(this)
                    }
                }
            }
        }
    }
}