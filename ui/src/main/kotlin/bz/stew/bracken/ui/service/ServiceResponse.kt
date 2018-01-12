package bz.stew.bracken.ui.service

/**
 * Created by stew on 6/28/17.
 */
data class ServiceResponse<out T>(internal val response: Collection<T>, val rawResponse: String, val isFail: Boolean = false) {
   /**
    * Returns true if the response failed or there were 0 results in the response.
    */
   fun isEmpty(): Boolean {
      return this.isFail || this.response.isEmpty()
   }
}