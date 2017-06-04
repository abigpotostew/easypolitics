package bz.bracken.stewart.db.mongodb

import bz.stewart.bracken.db.AssertAllFound
import bz.stewart.bracken.db.TestUtils.Methods.getTestResourcesData
import bz.stewart.bracken.shared.DateUtils
import bz.stewart.bracken.db.RuntimeMode
import bz.stewart.bracken.db.bill.data.Bill
import bz.stewart.bracken.db.bill.data.BillHistory
import bz.stewart.bracken.db.bill.data.Sponsor
import bz.stewart.bracken.db.bill.database.mongodb.BillJsonDataDatabase
import bz.stewart.bracken.db.bill.database.mongodb.SingleBillWriter
import bz.stewart.bracken.shared.data.BillType
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.litote.kmongo.findOne
import java.io.File
import java.util.*

/**
 * Created by stew on 3/10/17.
 */
class MongoDbTest {

   val dbName = "congress1"
   val collectionName = "bills_test_" + Date().time

   var writer: SingleBillWriter?=null
   var db:DebugDatabase?=null

   private fun createTestBill():Bill{
      return Bill(
            _id = null,
            bill_id = "sconres1-113",
            actionsArr = emptyArray(),
            bill_type = BillType.HOUSE_BILL,
            by_request = "by_request",
            congressNum = 113,
            cosponsorsArr = emptyArray(),
            billHistory = BillHistory(active = "true", vetoed = "false", enacted = "true", awaiting_signature = "false"),
            introduced_at = DateUtils.defaultDate(),
            billNumber = "1",
            committeesArr = emptyArray(),
            official_title = "Official title",
            related_bills = emptyArray(),
            billSponsor = Sponsor(bioguide_id = "1234ABC", name = "name", state = "CA", title = "title"),
            currentStatus = "getCurrentStatus",
            status_at = DateUtils.defaultDate(),
            subjectsArr = emptyArray(),
            titlesArr = emptyArray(),
            updated_at = DateUtils.defaultDate(),
            urlBill = "www.com"
                     )
   }

   @Before
   fun testSetup(){
      writer = SingleBillWriter()
      db = DebugDatabase(dbName)
      db!!.openDatabase()
      writer?.before(db!!)
   }

   @After
   fun tearDown(){
      if(db!!.isDbOpen()) {
         if(writer!!.isOpen){
            writer?.before(db!!)
         }
         writer?.drop(collectionName, db!!)
         writer?.after(db!!)
         db!!.closeDatabase()
      }
   }



   @Test
   fun loadSingleBillData() {
      val testBill = createTestBill()

      writer?.write(testBill, collectionName, db!!)
      db!!.queryCollection(collectionName, {
         val findFirst = this.findOne()
         assertTrue(testBill.equalLessId(findFirst as Bill))
      })
   }

   @Test
   fun loadAllTestData(){
      val testFinder = AssertAllFound<String>(listOf<String>("hconres1-113", "hjres1-113", "s11-113", "sres1-113","hconres1-114", "s11-115", "s71-115"),true)
      val db = BillJsonDataDatabase(File(getTestResourcesData()), "congress1", collectionName, RuntimeMode.RESET, false,
                                    writer!!)
      db.openDatabase()
      db.loadData(null)
      db.queryCollection(collectionName,{
         val searchRes = find()
         for (b:Bill in searchRes.iterator()){
            testFinder.foundElement(b.bill_id)
         }
      })
      testFinder.assertAllFound()
      writer?.before(db)
      writer?.drop(collectionName, db)
      //writer?.after(db)
      //db.closeDatabase()
   }

}