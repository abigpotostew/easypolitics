package bz.bracken.stewart.db.legislators

import bz.bracken.stewart.db.AssertAllFound
import bz.stewart.bracken.db.TestUtils.Methods.getTestResourcesDir
import bz.stewart.bracken.db.leglislators.ParserLegislatorJson
import bz.stewart.bracken.db.leglislators.ParserSocialJson
import bz.stewart.bracken.db.leglislators.data.IdData
import bz.stewart.bracken.db.leglislators.data.LegislatorData
import bz.stewart.bracken.db.leglislators.data.LegislatorSocialInfo
import bz.stewart.bracken.db.leglislators.data.SocialData
import org.junit.Test
import java.io.File

/**
 * unit test
 * Created by stew on 6/4/17.
 */
class LegislatorParseTest {

   @Test
   fun testJsonParse() {
      val currentData = getTestResourcesDir("/legislators-data/legislators-current.json")
      val parsed = ParserLegislatorJson().parseData(
            File(currentData).toPath())

      val expected = listOf<LegislatorData>(
            LegislatorData(id = IdData(bioguide = "B000944")),
            LegislatorData(id = IdData(bioguide = "M000639")),
            LegislatorData(id = IdData(bioguide = "S000033")),
            LegislatorData(id = IdData(bioguide = "W000779"))

                                           )
      val finder = AssertAllFound<LegislatorData>(expected, true, {
         this.id.bioguide == it.id.bioguide
      })

      for (data in parsed) {
         finder.foundElement(data)
      }
      finder.assertAllFound()
   }

   @Test
   fun testSocialJsonParse() {
      val currentData = getTestResourcesDir("/legislators-data/legislators-social-media.json")
      val parsed = ParserSocialJson().parseData(
            File(currentData).toPath())

      val expected = listOf(
            LegislatorSocialInfo(id = IdData(bioguide = "R000600"), social = SocialData(twitter = "RepAmata")),
            LegislatorSocialInfo(id = IdData(bioguide = "B001283"), social = SocialData(twitter = "RepJBridenstine")),
            LegislatorSocialInfo(id = IdData(bioguide = "E000295"), social = SocialData(twitter = "SenJoniErnst")),
            LegislatorSocialInfo(id = IdData(bioguide = "S000033"), social = SocialData(twitter = "SenSanders")),
            LegislatorSocialInfo(id = IdData(bioguide = "M000639"), social = SocialData(twitter = "SenatorMenendez"))

                                           )
      val finder = AssertAllFound(expected, true, {
         this.id.bioguide == it.id.bioguide && this.social.twitter == it.social.twitter
      })

      for (data in parsed) {
         finder.foundElement(data)
      }
      finder.assertAllFound()
   }
}