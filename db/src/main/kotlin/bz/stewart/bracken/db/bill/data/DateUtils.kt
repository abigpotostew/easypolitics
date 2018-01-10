package bz.stewart.bracken.shared

import mu.KotlinLogging
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by stew on 3/11/17.
 */
class DateUtils {
   companion object Utils {

      private val logger = KotlinLogging.logger {}


      private val iso8601_1 = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX")//this is the one I use in rest to write out to json
      private val iso8601_2 = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX")
      private val RFC822_1 = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
      //format from the timestamp in the file "data-fromfdsys-lastmod.txt"
      private val RFC822_2 = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ") //full expression with ms
      private val lastModFmt = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")//assumes input date is default locale PST, so this is wrong!
      //format from the timestamp in bill data.json
      private val updatedAtFmt = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'") //this literal 'Z' is due to me misunderstanding utc date format
      private val introducedFmt = SimpleDateFormat("yyyy-MM-dd")
      private val activeAtFmt = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX") //2017-01-03T14:33:52-05:00 (or -0500; -05)

      private val allDateFormats:MutableList<SimpleDateFormat> = mutableListOf()
      init {
         allDateFormats.add(iso8601_1)//most common first
         allDateFormats.add(iso8601_2)
         allDateFormats.add(RFC822_1)
         allDateFormats.add(RFC822_2)
         allDateFormats.add(lastModFmt)
         allDateFormats.add(updatedAtFmt)
         allDateFormats.add(introducedFmt)
         allDateFormats.add(activeAtFmt)
         for(fmt in allDateFormats) run {
            fmt.timeZone = TimeZone.getTimeZone("UTC") //washington time
         }
      }

      private val defaultDateValue = updatedAtFmt.parse("1800-01-01T00:00:00Z")

      fun defaultDate(): Date {
         return defaultDateValue
      }

      fun getLastModifiedDateFormat(): SimpleDateFormat {
         return lastModFmt
      }

      fun getUpdatedAtDateFormat(): SimpleDateFormat {
         return updatedAtFmt
      }

      fun getIntroducedDateFormat(): SimpleDateFormat {
         return introducedFmt
      }

      fun parseGovTrackDateNoFail(dateStr:String): Date { //doesn't return null but will throw parse exception
         return flexibleDateParser(dateStr)
         //return getLastModifiedDateFormat().parse(dateStr)
      }

      fun parseLastModifiedDateString(dateStr: String): Date? {
         try {
            return flexibleDateParser(dateStr)//getLastModifiedDateFormat().parse(dateStr)
         } catch(e: ParseException) {
            logger.error { "Error parsing date with format: $dateStr" }
            return null
         }
      }

      fun allDateFormats(): List<SimpleDateFormat> {
         return allDateFormats
      }

      /**
       * try to parse last mod string, if fails, try parse updated_at string.
       */
      fun parseModifiedOrUpdatedStrings(lastModifiedString: String, updated_at: String): Date? {
         return try {
            flexibleDateParser(lastModifiedString)
         }catch (e: ParseException){
            try {
               flexibleDateParser(updated_at)
            }catch (e: ParseException){
               null
            }
         }
//         return try {
//            getLastModifiedDateFormat().parse(lastModifiedString) ?: updatedAtFmt.parse(updated_at)
//         } catch (e: ParseException) {
//            return null
//         }
      }

      fun standardWriteFormat(): DateFormat {
         return iso8601_2//iso8601_1
      }

      /**
       * Parses using various known formats (iso860-1, RFC822,
       */
      fun flexibleDateParser(dateString:String): Date {
         if (dateString.isEmpty()){
            return defaultDate()
         }
         for(format in allDateFormats()) { //usually the first format (iso8601) will work but here's some backups
            try {
               return format.parse(dateString)
            } catch (e: ParseException) {
               //try again...
            }
         }
         //try all before throwing exception
         throw ParseException("Could not parse date with format: $dateString", 0)
      }
   }
}