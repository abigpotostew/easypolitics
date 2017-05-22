package bz.stewart.bracken.db.data

import bz.stewart.bracken.shared.data.PublicSummary
import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Created by stew on 5/17/17.
 */
data class Summary(@JsonProperty("as") val asValue: String? = "",
                   val date: String? = "",
                   val text: String? = "") : PublicSummary {
}