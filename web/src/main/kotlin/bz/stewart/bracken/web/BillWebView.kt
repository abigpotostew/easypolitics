package bz.stewart.bracken.web

import kotlinx.html.body
import kotlinx.html.div
import kotlinx.html.h1
import kotlinx.html.head
import kotlinx.html.html
import kotlinx.html.stream.createHTML
import kotlinx.html.style

class BillWebView(private val id: String) : WebView {
    override fun render(): String {
        return createHTML().html {
            head{
                style {

                }
            }
            body {
                h1 {
                    div {
                        +"bill $id"
                    }
                }
            }
        }
    }
}