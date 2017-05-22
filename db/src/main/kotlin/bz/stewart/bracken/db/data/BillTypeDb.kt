package bz.stewart.bracken.db.data

import bz.stewart.bracken.shared.data.BillType
import bz.stewart.bracken.shared.data.defaultBillTypeMatcher
import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.SerializerProvider

/**
 * Created by stew on 5/17/17.
 */
class BillTypeSerializer : JsonSerializer<BillType>() {
   override fun serialize(value: BillType?, gen: JsonGenerator?, serializers: SerializerProvider?) {
      gen!!.writeString(value?.shortCode())
   }
}

class BillTypeDeserializer : com.fasterxml.jackson.databind.JsonDeserializer<BillType>() {
   @Throws(java.io.IOException::class, com.fasterxml.jackson.core.JsonProcessingException::class,
           java.text.ParseException::class)
   override fun deserialize(jsonparser: com.fasterxml.jackson.core.JsonParser,
                            deserializationcontext: com.fasterxml.jackson.databind.DeserializationContext): BillType {
      return defaultBillTypeMatcher(jsonparser.text)
      //matchVisibleType(BillType.values(),jsonparser.text,VisibleTypeMatcher.SHORTCODE)
      //return BillType.valueOf(jsonparser.text)
   }
}