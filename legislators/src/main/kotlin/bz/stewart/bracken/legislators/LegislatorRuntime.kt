package bz.stewart.bracken.legislators

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import com.fasterxml.jackson.module.kotlin.KotlinModule
import java.io.File
import java.nio.file.Files

class LegislatorRuntime(private val args: Arguments) {

   private var socialFile:File? = null

   fun invalidArgsMessage():String?{
      for (f: File in args.files){
         if(!validYamlFile(f)){
            return "File '$f' is unreadable, or is not a .yaml file. Only accepts files ending with .yaml."
         }
      }

      if( args.dbName.isNullOrBlank() ){
         return "Database name is empty."
      }

      if(!args.socialMediaFiles.isNullOrBlank()){
         val _socialFile = File(args.socialMediaFiles)
         if(!validYamlFile(_socialFile)){
            return "Social file is unreadable or not a yaml file: ${args.socialMediaFiles}"
         }
         socialFile = _socialFile
      }

      return null
   }

   private fun validYamlFile(file:File):Boolean{
      return file.isFile && file.canRead() && (file.extension.equals("yaml") || file.extension.equals("yml"))
   }

   fun execute() {
      val mapper = ObjectMapper(YAMLFactory()) // Enable YAML parsing
      mapper.registerModule(KotlinModule()) // Enable Kotlin support

      val legs = mutableListOf<LegislatorsYaml>()
      for ( f in args.files ) {
         legs.add(Files.newBufferedReader(f.toPath()).use {
            mapper.readValue(it, LegislatorsYaml::class.java)
         })
      }
      for(l in legs){
         l.legislators
      }
   }
}