package bz.stewart.bracken.rest


import bz.stewart.bracken.rest.bills.BillExample
import bz.stewart.bracken.rest.bills.EMPTY_CONGRESS
import bz.stewart.bracken.rest.query.BillQueryBuilder
import bz.stewart.bracken.rest.query.QueryResult
import bz.stewart.bracken.shared.data.BadStateException
import bz.stewart.bracken.shared.data.BillType
import bz.stewart.bracken.shared.data.defaultBillTypeMatcher
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import javax.annotation.PostConstruct

/**
 * bills api rest controller
 * Created by stew on 3/13/17.
 */
//@ConfigurationProperties//todo
@RestController
class BillsController {

   @Autowired
   var billMongoDatabase: MongoDbBean? = null

   @PostConstruct
   fun setupClient() {
      //billMongoDatabase!!.getBillDb()!!.openDatabase()
      //todo isn't setup called by the bean??
   }

   @CrossOrigin(origins = arrayOf("http://localhost:8080",
                                  "http://localhost:63342")) // TODO this is just for testing locally, remove before prod
   @GetMapping("/bills")
   fun get(
         @RequestParam(value = "number", required = false) number: String? = null,
         @RequestParam(value = "bill_id", required = false) billId: String? = null,
         @RequestParam(value = "bill_type", required = false) billType: String? = null,
         @RequestParam(value = "congress", required = false) congress: Int = EMPTY_CONGRESS,

         //meta stuff
         @RequestParam(value = "order_by", required = false,
                       defaultValue = "-current_status_date") orderBy: String = "-current_status_date",
         @RequestParam(value = "limit", required = false, defaultValue = "100") limit: Int = 100
         /*@RequestParam(defaultValue = "") billId:String=""*/): QueryResult {
      //congress=115&order_by=-current_status_date&limit=200

      //todo move this to input validator
      val matchedBillType = try {
         defaultBillTypeMatcher(billType ?: "")
      } catch (e: BadStateException) {
         BillType.NONE
      }

      val queryExample =
            BillExample(billNumber = number,
                        bill_id = billId,
                        bill_type = matchedBillType,
                        congressNum = congress
                       )

      //todo validate input
      return BillQueryBuilder(billMongoDatabase!!.getMainDb()!!,queryExample,orderBy, limit).find()
   }

}