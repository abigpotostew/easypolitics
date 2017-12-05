package bz.stewart.bracken.rest.spark

interface RouteBehavior<T> {
    fun execute(context:RouteContext):T

    fun onError():Exception
}