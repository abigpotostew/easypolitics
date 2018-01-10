package bz.bracken.stewart.db.file.parse

import bz.bracken.stewart.db.AssertAllFound
import bz.stewart.bracken.db.TestUtils.Methods.getTestResourcesData
import bz.stewart.bracken.db.file.DataWalk
import bz.stewart.bracken.db.file.parse.Parser
import org.junit.Assert
import org.junit.Test
import java.io.File

/**
 * Created by stew on 3/10/17.
 */
class ParserTest {

   val dataDir = getTestResourcesData()
   val parser = DebugParser()

   @Test
   fun parserTest(){
      println(System.getProperty("user.dir"))
      val fileDataDir = "$dataDir/113/bills/hconres/hconres1/data.json"
      println(fileDataDir)
      parser.parseData("hconres1-113",File(fileDataDir),null)
   }

   @Test
   fun dataWalkTest(){
      val elementFinder = AssertAllFound<String>(
            listOf<String>("hconres1-113", "hjres1-113", "s11-113", "sres1-113",
                           "hconres1-114", "s11-115", "s71-115"), true)

      var didComplete=false
      val parser = object :Parser{
         override fun parseData(uniqueId: String, data: File, lastModified: File?) {
            elementFinder.foundElement(uniqueId)
            //Assert.assertTrue("Missing $uniqueId",expectedData.contains(uniqueId))
            //queue.minusElement(uniqueId)
         }

         override fun onComplete() {
            didComplete = true
         }
      }
      val dw = DataWalk(File(dataDir),parser)
      dw.traverse()
      Assert.assertTrue("Didn't complete traversal", didComplete)
      elementFinder.assertAllFound()
      //Assert.assertTrue("Didn't parse: $queue",queue.size==0)
   }
}