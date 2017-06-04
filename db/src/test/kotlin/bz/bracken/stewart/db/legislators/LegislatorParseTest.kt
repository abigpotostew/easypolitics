package bz.bracken.stewart.db.legislators

import bz.bracken.stewart.db.AssertAllFound
import bz.stewart.bracken.db.TestUtils.Methods.getTestResourcesDir
import bz.stewart.bracken.db.leglislators.IdData
import bz.stewart.bracken.db.leglislators.LegislatorData
import bz.stewart.bracken.db.leglislators.ParserJson
import org.junit.Test
import java.io.File

/**
 * Created by stew on 6/4/17.
 */
class LegislatorParseTest {

   @Test
   fun testJsonParse(){
      val currentData = getTestResourcesDir("/legislators-data/legislators-current.json")
      val parsed = ParserJson().parseData(File(currentData).toPath())

      val expected = listOf<LegislatorData>(
            LegislatorData(id= IdData(bioguide = "B000944")),
            LegislatorData(id= IdData(bioguide = "M000639")),
            LegislatorData(id= IdData(bioguide = "S000033")),
            LegislatorData(id= IdData(bioguide = "W000779"))

                                           )
      val finder = AssertAllFound<LegislatorData>(expected, true, {
         this.id.bioguide == it.id.bioguide
      })

      for (data in parsed){
         finder.foundElement(data)
      }
      finder.assertAllFound()
   }
}