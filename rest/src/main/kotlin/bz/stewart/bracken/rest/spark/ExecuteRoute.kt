package bz.stewart.bracken.rest.spark

class ExecuteRoute<T : RouteBehavior<R>, R>(behavior: T) {
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