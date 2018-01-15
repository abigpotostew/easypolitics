package bz.stewart.bracken.db

/**
 * Created by stew on 3/11/17.
 */
interface DbRuntime {

   fun validateArgs()
   fun run()
}

fun emptyDbRuntime(): DbRuntime{
   return object: DbRuntime {
      override fun validateArgs() {}
      override fun run() {}
   }
}