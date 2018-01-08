package bz.stewart.bracken.rest.service

import bz.stewart.bracken.rest.route.RouteContext
import bz.stewart.bracken.rest.route.RouteService

class HomeService : RouteService<String> {
    override fun execute(context: RouteContext): String {
        return "Error: Please specify the query type such as bills."
    }

    override fun onError(): Exception {
        return Exception("Error: Please specify the query type such as bills.")
    }
}