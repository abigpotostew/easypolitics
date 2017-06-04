package bz.stewart.bracken.db.bill.data.parse

import bz.stewart.bracken.shared.DateUtils
import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.SerializerProvider
import java.util.*

/**
 * Created by stew on 3/31/17.
 */
class DbDateSerializer : JsonSerializer<Date>() {
   val format = DateUtils.standardWriteFormat()
   override fun serialize(value: Date?, gen: JsonGenerator?, serializers: SerializerProvider?) {
      gen!!.writeString(serializeDate(value))
   }

   fun serializeDate(value:Date?):String{
      return format.format(value)
   }
}