package bz.stew.bracken.ui.service

/**
 * Created by stew on 1/24/17.
 */

import org.w3c.dom.events.Event
import org.w3c.dom.events.EventListener
import org.w3c.xhr.XMLHttpRequest

class ServerRequestDispatcher {

    //private final val WORKDAY_CLIENT_VERSION_HEADER = "X-Workday-Client"
    private final val CONTENT_TYPE_HEADER = "Content-Type"
    private final val ACCEPT_HEADER = "Accept"

    fun sendRequest(path: String,
                    callback: RequestCallback? = null): XMLHttpRequest {
        val request: XMLHttpRequest = XMLHttpRequest()
        request.open("GET",
                     path,
                     true)
        request.setRequestHeader(ACCEPT_HEADER,
                                 "application/json")
        request.setRequestHeader(CONTENT_TYPE_HEADER,
                                 "application/x-www-form-urlencoded")
        if (callback != null) {
            request.addEventListener("progress",
                                     object : EventListener {
                                         override fun handleEvent(event: Event) {
                                             callback.onProgress()
                                         }
                                     })
            request.addEventListener("load",
                                     object : EventListener {
                                         override fun handleEvent(event: Event) {
                                             callback.onLoad(request.response)
                                         }
                                     })
            request.addEventListener("error",
                                     object : EventListener {
                                         override fun handleEvent(event: Event) {
                                             callback.onError()
                                         }
                                     })
            request.addEventListener("abort",
                                     object : EventListener {
                                         override fun handleEvent(event: Event) {
                                             callback.onCancel()
                                         }
                                     })
        }
        request.send()
        return request
    }
}