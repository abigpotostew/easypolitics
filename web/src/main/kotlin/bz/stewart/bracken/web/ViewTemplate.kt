package bz.stewart.bracken.web

import kotlinx.html.HtmlBlockTag

interface ViewTemplate {
    fun renderIn(parent: HtmlBlockTag)
}