package bz.stew.bracken.ui.service

/**
 * Created by stew on 1/24/17.
 */
abstract class RequestCallback {

    open fun onProgress() {
    }

    open fun onLoad(response: dynamic) {
    }

    open fun onError() {
    }

    open fun onCancel() {
    }

}