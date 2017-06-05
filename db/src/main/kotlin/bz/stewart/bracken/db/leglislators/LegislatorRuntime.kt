package bz.stewart.bracken.db.leglislators

import bz.stewart.bracken.db.database.CollectionWriter
import bz.stewart.bracken.db.database.Database
import bz.stewart.bracken.db.database.emptyDatabaseWriter
import mu.KLogging

class LegislatorRuntime(private val args: LegislatorArguments) {
   companion object : KLogging()

   private var db: LegislatorCreateDb? = null

   fun execute() {
      val writer = if (args.testMode) {
         emptyDatabaseWriter<LegislatorData, Database<LegislatorData>>()
      }
      else {
         LegislatorDbWriter()
      }
      db = LegislatorCreateDb(args.dbName,
                              writer as CollectionWriter<LegislatorData, Database<LegislatorData>>)
      db.use {
         executeCurrentLegislators()
      }

      if (args.testMode == true) {
         logger.info { "Completed database update. Test mode enabled. No data was updated." }
      }
      else {
         logger.info { "Completed legislators database update." }
      }
   }

   fun executeCurrentLegislators() {
      val collName = "current"
      val db: LegislatorCreateDb = db!!
      db.openDatabase()
      val writer = db.getWriter()
      writer.before(db)
      writer.drop(collName, db)
      logger.info { "Dropped collection '$collName' in preparation to load fresh data." }
      writer.after(db)

      //parse data
      val legislators = ParserJson().parseData(args.files.map { it.toPath() })

      //write to database
      writer.before(db)
      for (legislator in legislators) {
         writer.write(legislator, collName, db)
      }
      writer.after(db)
      logger.info { "Successfully wrote ${legislators.size} current legislators to database." }

   }
}