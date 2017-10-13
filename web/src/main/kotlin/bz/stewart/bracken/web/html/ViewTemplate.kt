package bz.stewart.bracken.web.html

import kotlinx.html.HtmlBlockTag

interface ViewTemplate {
    fun renderIn(parent: HtmlBlockTag)
}