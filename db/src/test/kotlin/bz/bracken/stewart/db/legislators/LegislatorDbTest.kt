package bz.bracken.stewart.db.legislators

import bz.bracken.stewart.db.AssertAllFound
import bz.stewart.bracken.db.TestUtils
import bz.stewart.bracken.db.database.CollectionWriter
import bz.stewart.bracken.db.database.Database
import bz.stewart.bracken.db.leglislators.*
import bz.stewart.bracken.db.leglislators.data.IdData
import bz.stewart.bracken.db.leglislators.data.LegislatorData
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
   val collectionName = "current"
   val currentDataPath = TestUtils.getTestResourcesDir(
         "/legislators-data/legislators-current.json")
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
      if (db != null && (db?.isDbOpen() ?: false)) {
         db!!.openDatabase()
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

   private fun assertSampleData(db: LegislatorCreateDb) {

      val expected = listOf<LegislatorData>(
            LegislatorData(id = IdData(bioguide = "B000944")),
            LegislatorData(id = IdData(bioguide = "M000639")),
            LegislatorData(id = IdData(bioguide = "S000033")),
            LegislatorData(id = IdData(bioguide = "W000779"))
                                           )

      //todo make this verify more data
      val finder = AssertAllFound<LegislatorData>(expected, true, {
         this.id.bioguide == it.id.bioguide
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
   fun runtimeTest() {
      val args = MockLegislatorArgs(_dbName = dbName,
                                    _files = mutableListOf(File(currentDataPath)),
                                    _testMode = false)
      val runtime = LegislatorRuntime(args = args)
      runtime.execute()

      val writer = LegislatorDbWriter() as CollectionWriter<LegislatorData, Database<LegislatorData>>
      val db = LegislatorCreateDb(dbName, writer)

      db.openDatabase()
      assertSampleData(db)
      //rely on cleanup from teardown
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

}