package bz.stewart.bracken.web.html

import bz.stewart.bracken.web.service.WebPageContext

interface WebPage {
    fun render(context: WebPageContext):String
}