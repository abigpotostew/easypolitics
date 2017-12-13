package bz.stewart.bracken.rest.route

import bz.stewart.bracken.rest.service.IllegalQueryInput

class ExecuteRoute<T : RouteService<R>, R>(behavior: T) {
    private val behavior = behavior
    fun execute(context: RouteContext): String {
        return try {
            context.objectMapper.writeValueAsString(behavior.execute(context))
        } catch (e: IllegalQueryInput) {
            "Invalid query parameters: ${e.message}"
        } catch (e: Exception) {
            "Internal error: " + behavior.onError().message + "\n\nCause: " + e.message + "\n" + e.printStackTrace()
        }
    }
}