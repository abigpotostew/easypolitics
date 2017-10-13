package bz.stewart.bracken.web.html

import bz.stewart.bracken.web.HtmlConstants
import bz.stewart.bracken.web.html.config.HtmlConfiguration
import kotlinx.html.META
import kotlinx.html.body
import kotlinx.html.head
import kotlinx.html.html
import kotlinx.html.lang
import kotlinx.html.link
import kotlinx.html.meta
import kotlinx.html.script
import kotlinx.html.stream.createHTML
import kotlinx.html.style
import kotlinx.html.title

class WebsiteSkeleton(private val content: ViewTemplate?=null, private val config:HtmlConfiguration) : WebPage {
    override fun render(): String {
        return createHTML().html {
            lang = "en"
            head {
                for(conf in config.getWithTag(META::class)){
                    meta{
                        conf.apply(this)
                    }
                }

//                meta {
//                    name = "viewport"
//                    content = "width=device-width, initial-scale=1, shrink-to-fit=no"
//                }
                link(rel = "stylesheet", href = "https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css") {
                    this.attributes.put("integrity", "sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ")
                    this.attributes.put("crossorigin", "anonymous")
                }
                link(rel = "stylesheet", href = "/mainstyle.css")

                title {
                    +"title"
                }
                style {

                }
            }
            body {
                content?.renderIn(this)
                script(src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js")
                script(src="/jquery.actual.js", type = HtmlConstants.TEXT_JAVASCRIPT.get())
                script(src="https://cdnjs.cloudflare.com/ajax/libs/velocity/1.4.0/velocity.min.js", type = "text/javascript")
                script(src="https://cdn.jsdelivr.net/velocity/1.4.0/velocity.ui.min.js", type = "text/javascript")
                script(
                    type = HtmlConstants.TEXT_JAVASCRIPT.get(),
                    src = "https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js"){
                    attributes.put("integrity","sha384-DztdAPBWPRXSA/3eYEEUWrWCy7G5KFbe8fFjk5JAIxUYHKkDx6Qin1DkWx51bBrb")
                    attributes.put("crossorigin","anonymous")
                }
                script(
                    type = HtmlConstants.TEXT_JAVASCRIPT.get(),
                    src = "https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js"){
                    attributes.put("integrity","sha384-vBWWzlZJ8ea9aCX4pEW3rVHjgjt7zpkNpZk+02D9phzyeVkE+jo0ieGizqPLForn")
                    attributes.put("crossorigin","anonymous")
                }
            }
        }
    }
}