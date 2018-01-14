package bz.stewart.bracken.web.view

import bz.stewart.bracken.web.extension.setId
import bz.stewart.bracken.web.html.ViewRender
import bz.stewart.bracken.web.service.WebPageContext
import bz.stewart.bracken.web.view.browse.FixedStatusFilterForm
import bz.stewart.bracken.web.view.browse.IntroDateFilter
import bz.stewart.bracken.web.view.browse.MajorActionFilterForm
import bz.stewart.bracken.web.view.browse.PartyFilterForm
import kotlinx.html.ButtonType
import kotlinx.html.HtmlBlockTag
import kotlinx.html.button
import kotlinx.html.div
import kotlinx.html.h3
import kotlinx.html.h4
import kotlinx.html.li
import kotlinx.html.nav
import kotlinx.html.span
import kotlinx.html.style
import kotlinx.html.ul

class NavBar : ViewRender {
    override fun renderIn(parent: HtmlBlockTag, context: WebPageContext) {
        parent.apply {
            div(classes = "fixed-top") {
                //                <div class="collapse " id="navbarToggleExternalContent">
                div(classes = "collapse") {
                    setId("navbarToggleExternalContent")
                    //attributes.put("aria-expanded","false")
                    div(classes = "bg-inverse p-4") {
                        h4(classes = "text-white") {
                            +"Collapsed pizza"
                        }
                        span("text-muted") {
                            +"toggle via navbar"
                        }
                        button(classes = "btn btn-primary btn-sm") {
                            setId("loadNextPageBtn")
                            type = ButtonType.button
                            +"Load Next Page"
                        }
                    }
                }
                nav("navbar navbar-inverse bg-inverse collapse") {
                    div {
                        style = "display: inline-block;"
                        ul(classes = "nav navbar-nav") {
                            style = "display: inline-block;"
                            li {
                                div {
                                    style = "display: inline-block;"
                                    button(classes = "navbar-toggler") {
                                        type = ButtonType.button
                                        attributes.put("data-toggle", "collapse")
                                        attributes.put("data-target", "#navbarToggleExternalContent")
                                        attributes.put("aria-controls", "navbarToggleExternalContent")
                                        attributes.put("aria-expanded", "false")
                                        attributes.put("aria-label", "Toggle navigation")
                                        span(classes = "navbar-toggler-icon")
                                    }
                                }
                            }
                            li {
                                h3 { setId("site-title"); +"Easy Politics" }
                            }
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
                }
            }
        }
    }
}