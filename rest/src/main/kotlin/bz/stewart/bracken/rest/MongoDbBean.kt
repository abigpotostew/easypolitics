package bz.stewart.bracken.rest

import bz.stewart.bracken.db.bill.database.mongodb.BillMongoDb
import bz.stewart.bracken.db.leglislators.LegislatorMongoDb
import javax.annotation.PostConstruct
import javax.annotation.PreDestroy

/**
 * abstraction to initialize the db for difference entry points, testing, vs prod, etc.
 * Could also return mocked or in memory db.
 */
abstract class MongoDbBean{
   @PostConstruct
   abstract fun setup()
   @PreDestroy
   abstract fun destroy()
   abstract fun getBillDb(): BillMongoDb?
   abstract fun getPeopleDb(): LegislatorMongoDb?
}