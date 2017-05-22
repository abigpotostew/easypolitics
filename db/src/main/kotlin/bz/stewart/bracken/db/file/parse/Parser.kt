package bz.stewart.bracken.db.file.parse

import java.io.File

/**
 * Created by stew on 3/9/17.
 */
interface Parser {
   fun parseData(uniqueId:String, data: File, lastModified: File?)

   fun onComplete()
}