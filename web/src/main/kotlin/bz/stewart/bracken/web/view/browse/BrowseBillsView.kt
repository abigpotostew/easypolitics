package bz.stewart.bracken.web.view.browse

import bz.stewart.bracken.web.extension.setId
import bz.stewart.bracken.web.html.ViewRender
import bz.stewart.bracken.web.service.WebPageContext
import bz.stewart.bracken.web.view.BillsMultiView
import bz.stewart.bracken.web.view.ContentRoot
import bz.stewart.bracken.web.view.bootstrap.BootstrapNavConfig
import bz.stewart.bracken.web.view.bootstrap.CommonHeader
import kotlinx.html.HtmlBlockTag
import kotlinx.html.div
import kotlinx.html.li
import kotlinx.html.span
import kotlinx.html.style
import kotlinx.html.ul

class BrowseBillsView : ViewRender {
    override fun renderIn(parent: HtmlBlockTag, context: WebPageContext) {
        CommonHeader(BootstrapNavConfig()).renderIn(parent, context)
        parent.span {
            ul {
                style = "display: inline-block;"
                li {
                    //<form class="form-inline my-2 my-lg-0 nav-item ">
                    PartyFilterForm().renderIn(this, context)
                }
                li {
                    FixedStatusFilterForm().renderIn(this, context)
                }
                li {
                    IntroDateFilter().renderIn(this, context)
                }
                li {
                    MajorActionFilterForm().renderIn(this, context)
                }
            }
            span(classes = "navbar-text") {
                setId("nav-bar-billCount")
                +"Showing 0 bills"

            }
        }
        parent.div {
            //NavBar().renderIn(this, context)


//
//            br { }
//            br { }
//            br { }
//            br { }
//            br { }

            ContentRoot(BillsMultiView()).renderIn(this, context)
        }


    }
}

