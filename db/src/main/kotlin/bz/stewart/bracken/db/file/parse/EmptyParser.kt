package bz.stewart.bracken.db.file.parse

import java.io.File

/**
 * Created by stew on 3/9/17.
 */
class EmptyParser :Parser{
   override fun parseData(uniqueId: String, data: File, lastModified: File?) {

   }

   override fun onComplete() {

   }
}