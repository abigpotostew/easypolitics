package bz.stewart.bracken.web

import kotlinx.html.body
import kotlinx.html.head
import kotlinx.html.html
import kotlinx.html.lang
import kotlinx.html.meta
import kotlinx.html.stream.createHTML
import kotlinx.html.style
import kotlinx.html.title

class WebsiteSkeleton(private val content:ViewTemplate) : WebView {
    override fun render(): String {
        return createHTML().html {
            lang="en"
            head {
                meta { charset="UTF-8" }
                meta { charset="utf-8" }
                meta {
                    name="viewport"
                    content="width=device-width, initial-scale=1, shrink-to-fit=no"
                }
                title {
                    +"title"
                }
                style {

                }
            }
            body {
                content.renderIn(this)
            }
        }
    }
}