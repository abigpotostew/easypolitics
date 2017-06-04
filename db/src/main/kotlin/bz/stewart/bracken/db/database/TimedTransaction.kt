package bz.stewart.bracken.db.database

import java.util.*

/**
 * Created by stew on 3/30/17.
 */
class TimedTransaction<T : DbItem,D : Database<T>>(private val capture: Transaction<T, D>): Transaction<T, D>{

   var startTime: Date? = null
   var startExecution: Date? = null
   var endExecution: Date? = null
   var endTime: Date? = null
   var aborted = false

   override fun setWriter(writer: CollectionWriter<T, D>) {
      capture.setWriter(writer)
   }

   override fun beginTransaction() {
      startTime = now()
      capture.beginTransaction()
   }

   override fun execute() {
      startExecution = now()
      capture.execute()
      endExecution = now()
   }

   override fun endTransaction() {
      capture.endTransaction()
      endTime = now()
   }

   fun now():Date{
      return Date()
   }

   fun formatTimes(start:Date,end:Date):String{
      val diff = end.time - start.time
      if(diff<1000){
         return "$diff milliseconds"
      }
      return "${diff/1000} seconds"
   }

   override fun abort(msg: String) {
      capture.abort()
      aborted = true
      if(endExecution==null){
         endExecution = now()
      }
   }

   override fun toString(): String {
      val se = startExecution ?: now()
      val ee = endExecution ?: now()
      val et = endTime ?: now()
      return "Execution time ${formatTimes(se,ee)}. Total time ${formatTimes(startTime!!,et)}"
   }
}