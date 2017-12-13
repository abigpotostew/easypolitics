package bz.stewart.bracken.rest.route

interface RouteService<T> {
    fun execute(context: RouteContext):T

    fun onError():Exception
}