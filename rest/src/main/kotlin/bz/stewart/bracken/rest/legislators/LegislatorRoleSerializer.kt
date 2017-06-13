package bz.stewart.bracken.rest.legislators

import bz.stewart.bracken.shared.data.person.LegislatorRole
import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.SerializerProvider

/**
 * Created by stew on 6/12/17.
 */

class LegislatorRoleSerializer : JsonSerializer<LegislatorRole>() {
   override fun serialize(role: LegislatorRole?, gen: JsonGenerator?, serializers: SerializerProvider?) {
      gen!!.writeString(role?.shortCode())
   }
}