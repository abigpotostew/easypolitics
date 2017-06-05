package bz.stewart.bracken.db.leglislators

import bz.stewart.bracken.db.leglislators.data.LegislatorData
import bz.stewart.bracken.db.leglislators.data.LegislatorSocialInfo
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import mu.KLogging
import java.io.Reader
import java.nio.file.Files.newBufferedReader
import java.nio.file.Path

/**
 * Created by stew on 6/4/17.
 */
abstract class ParsersdfAllJson<T> (private val typeName:String="JsonObject"){
   companion object : KLogging()
   protected val mapper = jacksonObjectMapper() //kotlin module mapper
   abstract fun readValue(src: Reader):List<T>

   fun parseData(dataPath: Path): List<T> {
      return newBufferedReader(dataPath).use {
         val arr: List<T> = readValue(it)
         arr
      }
   }

   fun parseData(dataPaths: List<Path>): List<T> {
      val out = mutableListOf<T>()
      for (path in dataPaths) {
         val parsed = parseData(path)
         out.addAll(parsed)
         logger.debug { "Parsed ${parsed.size} ${typeName}s from file '${path.fileName} full path: '$path'" }
      }
      logger.debug { "Parsed ${out.size} ${typeName}s total." }
      return out
   }
}

class ParserLegislatorJson():ParsersdfAllJson<LegislatorData>("LegislatorData"){
   override fun readValue(src: Reader): List<LegislatorData> {
      return mapper.readValue(src)
   }
}

class ParserSocialJson():ParsersdfAllJson<LegislatorSocialInfo>("LegislatorSocialInfo"){
   override fun readValue(src: Reader): List<LegislatorSocialInfo> {
      return mapper.readValue(src)
   }
}