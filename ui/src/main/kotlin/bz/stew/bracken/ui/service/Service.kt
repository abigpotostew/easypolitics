package bz.stew.bracken.ui.service

interface Service<T>{
    fun sendRequest(requestUrl: ServiceEndpoint, parser: Parser<T>, onDownload: (ServiceResponse<T>) -> Unit)
}