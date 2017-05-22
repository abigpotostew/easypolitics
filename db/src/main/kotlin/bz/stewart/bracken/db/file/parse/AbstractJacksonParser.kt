package bz.stewart.bracken.db.file.parse

import bz.stewart.bracken.shared.DateUtils
import bz.stewart.bracken.db.database.DbItem
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import java.io.File

/**
 * Created by stew on 3/9/17.
 */
abstract class AbstractJacksonParser< T : DbItem>(val clazz:Class<T>): Parser {
   val mapper = jacksonObjectMapper()
   init {
      //mapper.dateFormat = DateUtils.getUpdatedAtDateFormat()
      mapper.setDateFormat(DateUtils.getIntroducedDateFormat())
   }

   fun mapJson(json:String):T{
      return mapper.readValue(json,clazz)
   }

   fun readJsonFile(data: File):String{
      return data.readText()
   }

   fun readMap(data:File):T{
      return mapJson(readJsonFile(data))
   }
}