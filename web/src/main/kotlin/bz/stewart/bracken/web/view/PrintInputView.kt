package bz.stewart.bracken.web.view

import bz.stewart.bracken.web.html.ViewTemplate
import kotlinx.html.HtmlBlockTag
import kotlinx.html.div
import kotlinx.html.h1

class PrintInputView(private val string: String) : ViewTemplate {
    override fun renderIn(parent: HtmlBlockTag) {
        parent.h1 {
            div {
                +string
            }
        }
    }
}