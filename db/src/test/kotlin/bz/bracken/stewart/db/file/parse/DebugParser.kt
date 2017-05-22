package bz.bracken.stewart.db.file.parse

import bz.stewart.bracken.db.data.Bill
import bz.stewart.bracken.db.file.parse.Parser
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import java.io.File

/**
 * Created by stew on 3/9/17.
 */
class DebugParser : Parser {

   val mapper = jacksonObjectMapper()
   val exceptions:MutableMap<String,Int> = HashMap()

   override fun parseData(uniqueId:String, data: File, lastModified: File?){

      val json = data.readText()
      //println(json)
      try {
         val bill = mapper.readValue<Bill>(json)

      }catch (e: UnrecognizedPropertyException){
         if(exceptions.containsKey(e.propertyName)){
            1+1
            exceptions.put(e.propertyName,exceptions.getValue(e.propertyName))
         }else{
            exceptions.put(e.propertyName,1)
         }
      }catch (e:Exception){
         println(e)
      }
   }

   override fun onComplete() {
      println(exceptions)
   }
}