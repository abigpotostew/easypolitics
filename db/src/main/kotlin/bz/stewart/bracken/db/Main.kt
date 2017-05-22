package bz.stewart.bracken.db

import bz.stewart.bracken.db.file.DataWalk
import bz.stewart.bracken.db.file.parse.EmptyParser
import com.xenomachina.argparser.ArgParser
import com.xenomachina.argparser.mainBody
import mu.KotlinLogging
import java.io.File


/**
 * Created by stew on 3/6/17.
 */
private val logger = KotlinLogging.logger {}


fun main(argv: Array<String>) = mainBody("easypolitics-db") {
   Arguments(ArgParser(argv)).run{
      //logger.info("Easy Politics Database.")
      logger.info({ "Easy Politics Database. Hello." })
      logger.info({ "Starting with options: mode = $mode, data = $data, db = $dbName" })


      val runtime = mode.getDbRuntime(this)
      try {
         runtime.validateArgs()
      } catch (e: IllegalArgumentException) {
         logger.error { "Input error: ${e.message}. Rerun with --help to see required parameters" }
         error("Input error. Rerun with --help to see required parameters")
      }

      runtime.run()
   }
}

fun testTraversal() {
   DataWalk(File(
         "/Users/stew/Documents/Code/github/congress/data"
         //"/Users/stew.bracken/Documents/Code/python/congress-master/data"
                ), listOf("115"),
            EmptyParser()
         //bz.bracken.stewart.db.file.parse.DebugParser()
           )//.traverse()
}