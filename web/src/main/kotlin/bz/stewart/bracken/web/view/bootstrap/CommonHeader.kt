package bz.stewart.bracken.web.view.bootstrap

import bz.stewart.bracken.shared.view.Classes
import bz.stewart.bracken.web.extension.ac
import bz.stewart.bracken.web.html.ViewRender
import bz.stewart.bracken.web.service.WebPageContext
import kotlinx.html.*

class CommonHeader(private val navItems: List<NavItem>) : ViewRender {
    override fun renderIn(parent: HtmlBlockTag, context: WebPageContext) {
        parent.nav {
            ac("navbar navbar-toggleable-md navbar-inverse bg-inverse")
            this.id = "mainheader"

            button {
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
            a {
                ac("navbar-brand")
                href = "#"
                +"Navbar"
            }
            div {
                ac("collapse navbar-collapse")
                id = "navbarSupportedContent"
                ul {
                    ac("navbar-nav mr-auto")
                    for (item in navItems) {
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
                }
                form {
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