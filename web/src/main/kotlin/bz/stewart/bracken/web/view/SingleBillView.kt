package bz.stewart.bracken.web.view

import bz.stewart.bracken.web.html.ViewTemplate
import kotlinx.html.HtmlBlockTag

class SingleBillView():ViewTemplate {
    override fun renderIn(parent: HtmlBlockTag) {
        //val requestSingleBillQuery
        ContentRoot(BillsMultiView()).renderIn(parent)
    }
}