package bz.stewart.bracken.rest.route

/**
 * A route service is a transaction that returns some data.
 */
interface RouteService<T> {
    fun execute(context: RouteContext): T

    fun onError(): Exception
}