package bz.stewart.bracken.web.html

import bz.stewart.bracken.web.service.WebPageContext
import kotlinx.html.*
import kotlinx.html.stream.createHTML

class WebsiteSkeleton(private val content: ViewRender? = null, private val config: PageConfig) : WebPage {
    override fun render(context: WebPageContext): String {
        return createHTML().html {
            lang = "en"
            head {
                for (conf in config.getMetas()) {
                    meta {
                        conf.apply(this, context)
                    }
                }
                for (conf in config.getLinks()) {
                    link {
                        conf.apply(this, context)
                    }
                }
                title {
                    config.getTitle().apply(this, context)
                }
                for (conf in config.getBeginBodyScripts()) {
                    script {
                        conf.apply(this, context)
                    }
                }
            }
            body {

                config.getNavHeader()?.renderIn(this, context)

                content?.renderIn(this, context)
                for (scriptConfig in config.getEndBodyScripts()) {
                    script {
                        scriptConfig.apply(this, context)
                    }
                }
            }
        }
    }
}