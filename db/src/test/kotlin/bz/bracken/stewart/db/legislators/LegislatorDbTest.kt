package bz.bracken.stewart.db.legislators

import bz.bracken.stewart.db.AssertAllFound
import bz.stewart.bracken.db.TestUtils
import bz.stewart.bracken.db.database.CollectionWriter
import bz.stewart.bracken.db.database.Database
import bz.stewart.bracken.db.database.emptyDatabaseWriter
import bz.stewart.bracken.db.leglislators.*
import bz.stewart.bracken.db.leglislators.data.IdData
import bz.stewart.bracken.db.leglislators.data.LegislatorData
import bz.stewart.bracken.db.leglislators.data.SocialData
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.io.File
import java.util.*

/**
 * Created by stew on 6/4/17.
 */
class LegislatorDbTest {

   val dbName = "legislators" + Date().time
   val collectionName = "legislators"
   val currentDataPath = TestUtils.getTestLegislatorsCurrentData()
   val socialDataPath = TestUtils.getTestLegislatorsSocialData()
   var writer: CollectionWriter<LegislatorData, Database<LegislatorData>>? = null
   var db: LegislatorCreateDb? = null

   @Before
   fun testSetup() {
      writer = LegislatorDbWriter() as CollectionWriter<LegislatorData, Database<LegislatorData>>
      db = LegislatorCreateDb(dbName, writer!!)
   }

   @After
   fun testTeardown() {
      writer?.after(db!!)
      if (db != null ) {
         if(! db!!.isDbOpen()) {
            db!!.openDatabase()
         }
         db?.dropDatabase()
         db?.closeDatabase()
      }
   }

   @Test
   fun testLoadAllData() {

      val writer = LegislatorDbWriter() as CollectionWriter<LegislatorData, Database<LegislatorData>>
      val db = LegislatorCreateDb(dbName, writer)
      db.openDatabase()
      writer.before(db)

      for (l in ParserLegislatorJson().parseData(
            File(currentDataPath).toPath())) {
         writer.write(l, collectionName, db)
      }

      assertSampleData(db)

      writer.drop(collectionName, db)
      writer.after(db)

   }

   private fun assertSampleData(db: LegislatorCreateDb, social: Boolean = false) {

      val expected = listOf<LegislatorData>(
            LegislatorData(id = IdData(bioguide = "B000944")),
            LegislatorData(id = IdData(bioguide = "M000639"),
                           social = if (social) SocialData(twitter = "SenatorMenendez")
                           else null),
            LegislatorData(id = IdData(bioguide = "S000033"),
                           social = if (social) SocialData(twitter = "SenSanders")
                           else null),
            LegislatorData(id = IdData(bioguide = "W000779"))
                                           )

      //todo make this verify more data
      val finder = AssertAllFound<LegislatorData>(expected, true, {
         val checkSocial = social && (this.id.bioguide == "S000033" || this.id.bioguide == "M000639")
         val hasSocial = if (checkSocial) {
            this.social?.twitter == it.social?.twitter
         }
         else {
            true
         }
         this.id.bioguide == it.id.bioguide && hasSocial
      })
      db.queryCollection(collectionName, {
         val res = find()
         for (l in res.iterator()) {
            finder.foundElement(l)
         }
      })
      finder.assertAllFound()
   }

   @Test
   fun runtimeTestTestMode() {
      val args = MockLegislatorArgs(_dbName = dbName,
                                    _files = mutableListOf(File(currentDataPath)),
                                    _testMode = true)
      val runtime = LegislatorRuntime(args = args)
      runtime.execute()

      val writer = LegislatorDbWriter() as CollectionWriter<LegislatorData, Database<LegislatorData>>
      val db = LegislatorCreateDb(dbName, writer)

      db.openDatabase()
      db.queryCollection(collectionName, {
         val res = find()
         val actualData = mutableListOf<LegislatorData>()
         res.forEach { actualData.add(it) }
         assert(actualData.size == 0)
      })
   }

   @Test
   fun runtimeTest() {
      val args = MockLegislatorArgs(_dbName = dbName,
                                    _files = mutableListOf(File(currentDataPath)),
                                    _testMode = false)
      val runtime = LegislatorRuntime(args = args)
      runtime.execute()

      val db = LegislatorCreateDb(dbName, emptyDatabaseWriter())
      db.openDatabase()
      assertSampleData(db)
      //rely on cleanup from teardown
   }

   @Test
   fun runtimeTestWithSocialMapper() {
      val args = MockLegislatorArgs(_dbName = dbName,
                                    _files = mutableListOf(File(currentDataPath)),
                                    _testMode = false,
                                    _socialFile = File(socialDataPath))
      val runtime = LegislatorRuntime(args)
      runtime.execute()

      val db = LegislatorCreateDb(dbName, emptyDatabaseWriter())
      db.openDatabase()
      assertSampleData(db, true)

   }

}