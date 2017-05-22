package bz.stewart.bracken.legislators

import com.xenomachina.argparser.ArgParser
import com.xenomachina.argparser.mainBody
import mu.KotlinLogging

private val logger = KotlinLogging.logger {}

fun main(argv: Array<String>) = mainBody("easypolitics-legislators") {
   //logger.info("Easy Politics Database.")
   Arguments(ArgParser(argv)).run {
      logger.info({ "Easy Politics Legislators Database. Hello." })
      logger.info({ "Running with args: $this" })

      val legs = LegislatorRuntime(this)
      val validationMessage = legs.invalidArgsMessage()
      if(validationMessage!=null){
         val msg = "App args incorrect: $validationMessage"
         logger.error(msg)
         error(msg)
      }
      legs.execute()
   }
}

