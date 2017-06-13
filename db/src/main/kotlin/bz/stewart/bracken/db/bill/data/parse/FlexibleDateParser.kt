package bz.stewart.bracken.db.bill.data.parse

import bz.stewart.bracken.shared.DateUtils
import java.util.*


/**
 * Created by stew on 3/30/17.
 */
class FlexibleDateParser : com.fasterxml.jackson.databind.JsonDeserializer<Date>() {
   @Throws(java.io.IOException::class, com.fasterxml.jackson.core.JsonProcessingException::class,
           java.text.ParseException::class)
   override fun deserialize(jsonparser: com.fasterxml.jackson.core.JsonParser,
                            deserializationcontext: com.fasterxml.jackson.databind.DeserializationContext): java.util.Date {
      val dateText = jsonparser.text
      if(dateText.isNullOrBlank()){
         return DateUtils.defaultDate()
      }
      return DateUtils.flexibleDateParser(dateText)
   }
}