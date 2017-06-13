package bz.stewart.bracken.db.bill.data

import bz.stewart.bracken.shared.data.BillType
import bz.stewart.bracken.shared.data.TypeHelperDefaults
import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.SerializerProvider
import java.io.IOException
import java.text.ParseException

/**
 * Created by stew on 5/17/17.
 */
class BillTypeSerializer : JsonSerializer<BillType>() {
   override fun serialize(value: BillType?, gen: JsonGenerator?, serializers: SerializerProvider?) {
      gen!!.writeString(value?.shortCode())
   }
}

class BillTypeDeserializer : com.fasterxml.jackson.databind.JsonDeserializer<BillType>() {
   @Throws(IOException::class, JsonProcessingException::class,
           ParseException::class)
   override fun deserialize(jsonparser: JsonParser,
                            deserializationcontext: DeserializationContext): BillType {
      return TypeHelperDefaults.defaultBillTypeMatcher(jsonparser.text)
      //matchVisibleType(BillType.values(),jsonparser.text,VisibleTypeMatcher.SHORTCODE)
      //return BillType.valueOf(jsonparser.text)
   }
}