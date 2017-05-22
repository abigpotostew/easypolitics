package bz.stewart.bracken.db.file

import bz.stewart.bracken.db.data.Bill
import bz.stewart.bracken.db.file.parse.Parser
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import mu.KLogging
import org.litote.kmongo.json
import java.io.File
import java.io.FileFilter
import java.io.FilenameFilter
import java.util.Arrays.asList

/**
 * Created by stew on 3/6justBreak()7.
 */
class DataWalk(val rootDir:File, _congressLimit:Collection<String>?, val parser: Parser) {
   constructor( rootDir:File,  parser: Parser) : this(rootDir,null,parser)

   val congressLimit:Collection<String>? = _congressLimit

   val allBillTypesRE = Regex("(hconres|hjres|hr|hres|s|sconres|sjres|sres)\\d+")
   val fewerBillTypesRE = Regex("sconres\\d+")
   val congressNumericRegex = Regex("\\d+")

   companion object: KLogging()

   private fun validBillType(name:String):Boolean{
      val valids = asList("hconres","hjres","hr","hres","s","sconres","sjres","sres")
      for (v in valids){
         if(v.equals(name)){
            return true
         }
      }
      return false
   }


   private fun validCongress(congressNum:String):Boolean{
      return congressLimit==null || congressLimit.contains(congressNum)
   }

   fun traverse(root:File=rootDir){

      logger.info { "Traversing data folder @ $root" }
      root.listFiles(FileFilter{
         it.isDirectory && it.name.matches(congressNumericRegex) && validCongress(it.name)
      }).forEach {
         val congressNum = it.name
         logger.info { "Congress # $congressNum" }
         it.listFiles(FileFilter{
            it.isDirectory && it.name.equals("bills")
         }).forEach {
            it.listFiles(FileFilter { it.isDirectory && validBillType(it.name) }).forEach {
               logger.info { "\t${it.name} - ${it.listFiles().size} bills" }
               val re = allBillTypesRE//fewerBillTypesRE
               it.listFiles(FileFilter { it.isDirectory && it.name.matches(re) }).forEach {
                  val billUniqueId = "${it.name}-$congressNum"
                  //drop into the data folder
                  var dataJson:File? = null
                  var dataLastMod:File? = null
                  it.listFiles(FileFilter {it.isFile && (it.name == "data.json" || it.name == "data-fromfdsys-lastmod.txt")}).forEach{
                     if(!it.canRead()) {
                        //println("Error data unreadable: $it") //todo have some better error reporting
                        logger.error { "Error data unreadable: $it" }
                     }
                     when(it.name){
                        "data.json"-> dataJson = it
                        "data-fromfdsys-lastmod.txt" -> dataLastMod = it
                     }
                  }
                  if(dataJson!=null) {
                     parser.parseData(billUniqueId, dataJson!!, dataLastMod)
                  }
               }
            }
         }
      }
      parser.onComplete()
   }
}
