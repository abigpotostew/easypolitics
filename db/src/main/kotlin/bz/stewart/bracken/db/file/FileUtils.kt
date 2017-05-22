package bz.stewart.bracken.db.file

import bz.stewart.bracken.shared.DateUtils
import bz.stewart.bracken.shared.DateUtils.Utils.getLastModifiedDateFormat
import mu.KotlinLogging
import java.io.File
import java.io.IOException
import java.util.*

/**
 * Created by stew on 3/11/17.
 */
class FileUtils {


   companion object UtilMethods {
      protected val logger = KotlinLogging.logger {}

      fun parseLastModDate(lastModFile: File): Date? {
         val len = lastModFile.length()
         if (len > 1000 || len <= 5) {
            return null
         }
         val line = try {
            lastModFile.readLines().first()
         } catch (e: NoSuchElementException) {
            return null
         } catch (e: IOException) {
            return null
         }
         val output = DateUtils.parseLastModifiedDateString(line)
         if (output == null) {
            logger.error { "Format for last mod file is jacked up: '$line' @ file '$lastModFile' using parser ${getLastModifiedDateFormat().toPattern()}" }
         }
         return output
      }
   }
}