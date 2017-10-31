package bz.stew.bracken.ui.service

/**
 * Created by stew on 6/28/17.
 */
data class ServiceResponse<T>(internal val response: Collection<T>?, val rawResponse: String)