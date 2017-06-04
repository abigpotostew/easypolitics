package bz.stewart.bracken.db.leglislators

import com.xenomachina.argparser.ArgParser
import com.xenomachina.argparser.default
import java.io.File

/**
 * Created by stew on 5/21/17.
 */
class LegislatorArguments(parser: ArgParser){
   val files by parser.adding("-f", "--file", help="Path(s) to legislator json files."){
      File(this)
   }
   val socialMediaFiles by parser.storing("-s", "--social", help="Path to social media json file.").default("")
   val testMode by parser.flagging("-t", "--test", help="Turns on test run mode. No data will be written.")
   val dbName:String by parser.storing("-b", "--database", help="Name of db to write to.")

   override fun toString(): String {
      return "TestMode: $testMode, dbName: $dbName, files: $files, social-files: $socialMediaFiles"
   }
}