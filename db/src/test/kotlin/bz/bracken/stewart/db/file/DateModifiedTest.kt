package bz.bracken.stewart.db.file

import bz.stewart.bracken.db.RuntimeMode
import bz.stewart.bracken.db.TestUtils
import bz.stewart.bracken.db.bill.data.Bill
import bz.stewart.bracken.db.database.mongo.AbstractMongoDb
import bz.stewart.bracken.db.bill.database.mongodb.BillJsonDataDatabase
import bz.stewart.bracken.db.bill.database.mongodb.BillWriter
import bz.stewart.bracken.db.database.mongo.DefaultMongoClient
import bz.stewart.bracken.db.file.FileUtils
import bz.stewart.bracken.shared.DateUtils
import org.bson.types.ObjectId
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import java.io.File

/**
 * Created by stew on 3/11/17.
 */
class DateModifiedTest {

   val EXPECTED_TIME = "Fri Feb 17 19:08:00 PST 2017"//1487416080128 //this is in GMT
   val EXPECTED_DATE_FROM_FILE = TestUtils.getTestResourcesData() + "/113/bills/hconres/hconres1/data-fromfdsys-lastmod.txt"
   val INVALID_FILE1 = TestUtils.getTestResourcesData() + "/113/bills/hconres/hconres1"
   val INVALID_FILE2 = TestUtils.getTestResourcesData() + "/113/bills/hconres/hconres1/data.json"

   var testDb: BillJsonDataDatabase? = null

   @Before
   fun testSetupDb() {
      testDb = BillJsonDataDatabase(File(TestUtils.getTestResourcesData()), DefaultMongoClient("congress1"),
                                    "test1235", RuntimeMode.NONE,
                                    true,
                                    writer = object : BillWriter() {
                                       override fun write(element: Bill,
                                                          collection: String,
                                                          db: AbstractMongoDb<Bill>) {
                                       }

                                       override fun drop(collection: String,
                                                         db: AbstractMongoDb<Bill>) {
                                       }
                                    })
   }

   @Test
   fun lastModFormatTest() {
      var str: String = "2017-02-18T03:08:00.128Z"
      var out = DateUtils.parseLastModifiedDateString(str)
      assertTrue(
            out != null && out.toString() == EXPECTED_TIME) //it converts to pst locale by default even though input date is in UTC

      str = "2017-02-18T03:08:00.128"
      out = DateUtils.parseLastModifiedDateString(str)
      assertTrue(out.toString() == "Fri Feb 17 16:00:00 PST 2017")

      str = ""
      out = DateUtils.parseLastModifiedDateString(str)
      assertEquals(out, DateUtils.defaultDate())
   }

   @Test
   fun fileModifiedTest() {
      var file = File(EXPECTED_DATE_FROM_FILE)
      var date = FileUtils.parseLastModDate(file)
      assertTrue(date != null && date.toString() == EXPECTED_TIME)

      file = File(INVALID_FILE1)
      date = FileUtils.parseLastModDate(file)
      assertTrue(date == null)

      file = File(INVALID_FILE2)
      date = FileUtils.parseLastModDate(file)
      assertTrue(date == null)
   }

   @Test
   fun billModifiedTest() {
      billWIthDatesHelper("2017-02-18T03:08:00.128Z", "2017-02-18T03:08:00.128Z", false,
                          "Date on file equal to date on db, no modified")
      billWIthDatesHelper("2017-02-18T03:08:00.128Z", "2017-02-18T03:08:00.129Z", true,
                          "Date on file greater than date on db, has been modified")
      billWIthDatesHelper("2017-02-18T03:08:00.129Z", "2017-02-18T03:08:00.128Z", false,
                          "Date on file less than db, no modified")
      billWIthDatesHelper("2017-02-18T03:08:00.128Z", "2017-02-18T03:08:00.128Z", false,
                          "dates should be equals, not modified", File(
            TestUtils.getTestResourcesData() + "/113/bills/hconres/hconres1/data-fromfdsys-lastmod.txt"))
      billWIthDatesHelper("2017-02-18T03:08:00.129Z", "2017-02-18T03:08:00.128Z", false,
                          "db modified after file", File(
            TestUtils.getTestResourcesData() + "/113/bills/hconres/hconres1/data-fromfdsys-lastmod.txt"))
      billWIthDatesHelper("2017-02-18T03:08:00.127Z", "2017-02-18T03:08:00.128Z", true,
                          "db modified before file, true", File(
            TestUtils.getTestResourcesData() + "/113/bills/hconres/hconres1/data-fromfdsys-lastmod.txt"))
   }

   private fun billWIthDatesHelper(billFromDbDate: String, billFromFileDate: String,
                                   isModifiedExpected: Boolean, msg: String,
                                   f: File? = null) {
      val billFromDb = Bill(_id = ObjectId())//, lastModifiedString = billFromDbDate)
      billFromDb.setLastModified(DateUtils.parseLastModifiedDateString(billFromDbDate))
      val billFromFile = Bill(_id = ObjectId())//, lastModifiedString = billFromFileDate)
      billFromFile.setLastModified(
            DateUtils.parseLastModifiedDateString(billFromFileDate))
      val dateFromFile = testDb!!.getBillExternalModifiedTime(f, billFromFile)
      assertEquals(msg, isModifiedExpected,
                   testDb!!.billModifiedSinceLastTime(dateFromFile, billFromDb))
   }

}