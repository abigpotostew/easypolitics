package bz.bracken.stewart.db.mongodb

import bz.stewart.bracken.db.RuntimeMode
import bz.stewart.bracken.db.TestUtils
import bz.stewart.bracken.db.bill.data.Bill
import bz.stewart.bracken.db.bill.database.mongodb.BillJsonDataDatabase
import bz.stewart.bracken.db.file.DataWalk
import bz.stewart.bracken.db.file.parse.AbstractJacksonParser
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.litote.kmongo.find
import org.litote.kmongo.formatJson
import java.io.File
import kotlin.test.fail

class UpdateModifiedBillTest {

   var db: BillJsonDataDatabase? = null

   @Before
   fun setupDb() {
      db = TestUtils.generateTestBillWriteDb(RuntimeMode.UPDATE)
   }

   @After
   fun teardown() {
      db!!.openDatabase()
      db!!.dropDatabase()
      db!!.closeDatabase()
   }

   @Test
   fun testUpdateSimilarBill() {

      val fileWalker = DataWalk(File(TestUtils.getTestResourcesData()), object : AbstractJacksonParser<Bill>(Bill::class.java) {
         override fun onComplete() {
         }

         override fun parseData(uniqueId: String, data: File, lastModified: File?) {
            val db = db!!
            val bill = readMap(data)
            val found = db.queryCollection("bills", {
               find("{ bill_id:${bill.bill_id} }".formatJson()).first()
            })
            val extModTime = db.getBillExternalModifiedTime(lastModified, bill)
            if (db.shouldUpdate(extModTime, found)) {
               fail("this bill was not updated, fudge")
            }
         }
      })
      db!!.openDatabase()
      fileWalker.traverse()
      db!!.closeDatabase()
   }
}