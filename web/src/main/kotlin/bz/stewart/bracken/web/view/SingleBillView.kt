package bz.stewart.bracken.web.view

import bz.stewart.bracken.web.html.ViewTemplate
import kotlinx.html.HtmlBlockTag

class SingleBillView():ViewTemplate {
    override fun renderIn(parent: HtmlBlockTag) {
        //val requestSingleBillQuery
//        NavBar().renderIn(parent)
        ContentRoot(BillsMultiView()).renderIn(parent)
        //todo request query for bill

    }
}