package bz.bracken.stewart.db

import bz.stewart.bracken.shared.DateUtils
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * Created by stew on 4/4/17.
 */
class DateUtilsTests {

   @Test
   fun standardWriteFormatTest(){
      val v = DateUtils.standardWriteFormat()
      assertTrue(v.parse("2017-02-18T03:08:00Z").time == 1487387280000)
   }
}