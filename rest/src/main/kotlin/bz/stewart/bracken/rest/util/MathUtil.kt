
package bz.stewart.bracken.rest.util

/**
 * Created by stew on 4/12/17.
 */
class MathUtil {
   companion object MathUtil {
      fun clamp(v: Int, lo: Int, hi: Int): Int {
         return if (v <= lo) {
            lo
         }
         else if (v >= hi) {
            hi
         }
         else {
            v
         }
      }
   }
}
