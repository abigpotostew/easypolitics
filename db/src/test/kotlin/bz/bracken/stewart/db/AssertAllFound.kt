package bz.bracken.stewart.db

import org.junit.Assert.assertTrue

/**
 * when using comparator, the expected collection could be mocked objects that would fail
 * referential "===" equality but may pass with the comparison function.
 */
class AssertAllFound<T>(val expected: Collection<T>,
                        val failIfNotExact: Boolean = false,
                        val comparator: (T.(T) -> Boolean)? = null) {
   val queue: MutableCollection<T> = expected.toMutableList()

   fun foundElement(el: T) {
      if (containsElement(el)) {
         if (comparator != null) {
            queue.remove(findComparableElement(el))
         }
         else {
            queue.remove(el)
         }
      }
      else {
         assertTrue("Unexpected element found: $el", !failIfNotExact)
      }
   }

   private fun findComparableElement(el: T): T? {
      if (comparator != null) {
         val comp: T.(T) -> Boolean = comparator as T.(T) -> Boolean
         for (item in queue) {
            if (item.comp(el)) {
               return item
            }
         }
      }
      return null
   }

   private fun containsElement(el: T): Boolean {
      if (comparator != null) {
         val found = findComparableElement(el)
         return found != null
      }
      else {
         return queue.contains(el)
      }
   }

   fun assertAllFound() {

      assertTrue(
            "All elements were not found, ${queue.size} out of ${expected.size} remaining!! -  $queue",
            queue.isEmpty())
   }
}