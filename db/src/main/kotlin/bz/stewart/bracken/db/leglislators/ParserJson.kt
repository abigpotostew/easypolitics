package bz.stewart.bracken.db.leglislators

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import java.nio.file.Files.newBufferedReader
import java.nio.file.Path

/**
 * parse congress/legislators database form the json format
 * Created by stew on 6/4/17.
 */
class ParserJson() {
   val mapper = jacksonObjectMapper() //kotlin module mapper

   fun parseData(dataPath: Path): List<LegislatorData> {
      return newBufferedReader(dataPath).use {
         val arr: List<LegislatorData> = mapper.readValue(it)
         arr
      }
   }

   fun parseData(dataPaths:List<Path>):List<LegislatorData>{
      val out = mutableListOf<LegislatorData>()
      for(path in dataPaths){
         out.addAll(parseData(path))
      }
      return out
   }
}