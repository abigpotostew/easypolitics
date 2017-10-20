package bz.stewart.bracken.web.view

import bz.stewart.bracken.web.extension.ac
import bz.stewart.bracken.web.extension.setId
import bz.stewart.bracken.web.html.ViewTemplate
import kotlinx.html.ButtonType
import kotlinx.html.HtmlBlockTag
import kotlinx.html.br
import kotlinx.html.button
import kotlinx.html.div
import kotlinx.html.form
import kotlinx.html.h3
import kotlinx.html.h4
import kotlinx.html.id
import kotlinx.html.input
import kotlinx.html.label
import kotlinx.html.li
import kotlinx.html.nav
import kotlinx.html.option
import kotlinx.html.select
import kotlinx.html.span
import kotlinx.html.style
import kotlinx.html.ul

class BillWebView(val id: String) : ViewTemplate {
    override fun renderIn(parent: HtmlBlockTag) {
        val reqId = this.id
        parent.div {
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
                                form(classes = "mr-sm-2 text-white") {
                                    label(classes = "mr-sm-2 text-white") {
                                        attributes.put("for", "billFilter-party")
                                        +"Party:"
                                    }
                                    select(classes = "custom-select form-inline mb-2 mr-sm-2 mb-sm-0") {
                                        setId("billFilter-party")
                                        option {
                                            attributes.put("selected", "true")
                                            attributes.put("value", "NONE")
                                            +"All"
                                        }
                                        option {
                                            attributes.put("value", "DEMOCRAT")
                                            +"Democrat"
                                        }
                                        option {
                                            attributes.put("value", "REPUBLICAN")
                                            +"Republican"
                                        }
                                        option {
                                            attributes.put("value", "INDEPENDENT")
                                            +"Independent"
                                        }

                                    }
                                }
                            }
                            li {
                                form(classes = "form-inline mr-sm-2 text-white") {
                                    label(classes = "mr-sm-2 text-white") {
                                        attributes.put("for", "billFilter-fixedstatus")
                                        +"Status:"
                                    }
                                    select(classes = "custom-select form-inline mb-2 mr-sm-2 mb-sm-0") {
                                        setId("billFilter-fixedstatus")
                                        option {
                                            attributes.put("selected", "true")
                                            attributes.put("value", "0")
                                            +"All"
                                        }
                                    }
                                }
                            }
                            li(classes = "form-inline") {
                                form(classes = "form-inline mr-sm-2 text-white") {
                                    label(classes = "mr-sm-2 text-white") {
                                        attributes.put("for", "billFilter-dateintrostart")
                                        +"Introduced:"
                                    }
                                    input(classes = "form-inline form-control") {
                                        setId("billFilter-dateintrostart")
                                        attributes.put("type", "date")
                                    }
                                    label(classes = "mr-sm-2 text-white") {
                                        attributes.put("for", "billFilter-dateintroend")
                                        +"Introduced:"
                                    }
                                    input(classes = "form-inline form-control") {
                                        setId("billFilter-dateintroend")
                                        attributes.put("type", "date")
                                    }
                                }
                            }
                            li(classes = "form-inline") {
                                form(classes = "form-inline mr-sm-2 text-white") {
                                    label(classes = "mr-sm-2 text-white") {
                                        attributes.put("for", "billFilter-lastmajorstatus")
                                        +"Major Action:"
                                    }
                                    select(classes = "custom-select form-inline mb-2 mr-sm-2 mb-sm-0") {
                                        setId("billFilter-lastmajorstatus")
                                        option {
                                            attributes.put("selected", "true")
                                            attributes.put("value", "0")
                                            +"All"
                                        }
                                    }
                                }
                            }
                        }
                        span(classes = "navbar-text") {
                            setId("nav-bar-billCount")
                            +"Showing 0 bills"

                        }
                    }
                }
            }
            br { }
            br { }
            br { }
            br { }
            br { }

            div(classes = "container-fluid") {
                this.id = "root"
                ac("container-fluid")

                div {
                    this.id = "bills"
                    ac("card-deck")
                    //  +"bill $reqId"
                }
            }
        }

    }
}

