package bz.stewart.bracken.db.leglislators

import com.fasterxml.jackson.module.kotlin.readValue

class LegislatorRuntime(private val args: LegislatorArguments) {

   private var socialFile: java.io.File? = null

   fun invalidArgsMessage():String?{
      for (f: java.io.File in args.files){
         if(!validJsonFile(f)){
            return "File '$f' is unreadable, or is not a .json file. Only accepts files ending with .json."
         }
      }

      if( args.dbName.isNullOrBlank() ){
         return "Database name is empty."
      }

      if(!args.socialMediaFiles.isNullOrBlank()){
         val _socialFile = java.io.File(args.socialMediaFiles)
         if(!validJsonFile(_socialFile)){
            return "Social file is unreadable or not a json file: ${args.socialMediaFiles}"
         }
         socialFile = _socialFile
      }

      return null
   }

   private fun validJsonFile(file: java.io.File):Boolean{
      return file.isFile && file.canRead() && (file.extension.equals("json") || file.extension.equals("jsn"))
   }

   fun execute() {
      //val mapper = ObjectMapper(YAMLFactory()) // Enable YAML parsing
      //mapper.registerModule(KotlinModule()) // Enable Kotlin support
      val mapper = com.fasterxml.jackson.module.kotlin.jacksonObjectMapper()


      val legs = mutableListOf<LegislatorData>()
      for ( f in args.files ) {
         legs.addAll(java.nio.file.Files.newBufferedReader(f.toPath()).use {
            //mapper.readValue(it, LegislatorsYaml::class.java)
            //mapper.readValue(it, LegislatorData::class.java)
            val arr:List<LegislatorData> = mapper.readValue(it)
            arr


            //mapper.readValue(it, object : TypeReference<List<LegislatorData>>() {}) as List<LegislatorData>

//            val myObjects:List<LegislatorData> = mapper.readValue(it,
//               mapper.getTypeFactory().constructCollectionType(List::class.java, LegislatorData::class.java))
         })
      }
      for(l in legs){
         val str = l.toString()
         str[0]
         //l.legislators
      }
   }
}
//gw :legislators:run -PappArgs="['-f', '/Users/stew/Documents/Code/github/congress-legislators/alternate_formats/legislators-current.json', '-s', '/Users/stew/Documents/Code/github/congress-legislators/alternate_formats/legislators-social-media.json', '-t', '-b', 'legislators1']"
