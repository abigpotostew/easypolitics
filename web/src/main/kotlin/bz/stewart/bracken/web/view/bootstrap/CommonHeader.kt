package bz.stewart.bracken.web.view.bootstrap

import bz.stewart.bracken.shared.view.Classes
import bz.stewart.bracken.shared.web.AppServices
import bz.stewart.bracken.web.extension.ac
import bz.stewart.bracken.web.extension.setId
import bz.stewart.bracken.web.html.ViewRender
import bz.stewart.bracken.web.service.WebPageContext
import kotlinx.html.*

class CommonHeader(private val navConfig: NavConfigBuilder) : ViewRender {
    override fun renderIn(parent: FlowContent, context: WebPageContext) {
        parent.div {
            this.id = "mainheader"

//            div(classes = "collapse") {
//                setId("navbarToggleExternalContent")
//                //attributes.put("aria-expanded","false")
//                div(classes = "bg-inverse p-4") {
//                    h4(classes = "text-white") {
//                        +"Collapsed pizza"
//                    }
//                    span("text-muted") {
//                        +"toggle via navbar"
//                    }
//                    button(classes = "btn btn-primary btn-sm") {
//                        setId("loadNextPageBtn")
//                        type = ButtonType.button
//                        +"Load Next Page"
//                    }
//                }
//            }

            nav {
                ac("navbar navbar-toggleable-md navbar-inverse bg-inverse")

                button { //small screens will display this menu thing
                    ac("navbar-toggler navbar-toggler-right")
                    type = ButtonType.button
                    attributes["data-toggle"] = "collapse"
                    attributes["data-target"] = "#navbarSupportedContent"
                    attributes["aria-controls"] = "navbarSupportedContent"
                    attributes["aria-expanded"] = "false"
                    attributes["aria-label"] = "Toggle navigation"
                    span {
                        ac("navbar-toggler-icon")
                    }
                }

                //Tdo use this toggler to show filters maybe, needs to work along with the supporteContent togger
//                div {
//                    style = "display: inline-block;"
//                    button(classes = "navbar-toggler") {
//                        type = ButtonType.button
//                        attributes.put("data-toggle", "collapse")
//                        attributes.put("data-target", "#navbarToggleExternalContent")
//                        attributes.put("aria-controls", "navbarToggleExternalContent")
//                        attributes.put("aria-expanded", "false")
//                        attributes.put("aria-label", "Toggle navigation")
//                        span(classes = "navbar-toggler-icon")
//                    }
//                }

                a {
                    ac("navbar-brand")
                    href = "/"
                    +"Easy Politics"
                }
                div {
                    ac("collapse navbar-collapse")
                    id = "navbarSupportedContent"
                    ul {
                        ac("navbar-nav mr-auto")


                        for (item in navConfig.getNavs(context)) {
                            li {
                                ac("nav-item")
                                if (item.isActive) {
                                    ac("active")
                                }
                                a {
                                    ac("nav-link")
                                    href = item.href
                                    +item.label
                                    if (item.isActive) {
                                        span {
                                            ac(Classes.boots_screen_reader_only.label())
                                            +"(current)"
                                        }
                                    }
                                }

                            }
                        }
                        //todo in progress the filters could go here maybe
                    }
                    form(AppServices.SEARCH.absoluteUrlPath) {
                        ac("form-inline my-2 my-lg-0")
                        input {
                            ac("form-control mr-sm-2")
                            type = InputType.text
                            placeholder = "Search"
                            button {
                                ac("btn btn-outline-success my-2 my-sm-0")
                                type = ButtonType.submit
                                +"Search bills"
                            }
                        }
                    }
                }
            }
        }
    }
}