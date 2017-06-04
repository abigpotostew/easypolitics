package bz.stewart.bracken.db.leglislators

import bz.stewart.bracken.db.database.CollectionWriter
import bz.stewart.bracken.db.database.Database

class LegislatorRuntime(private val args: LegislatorArguments) {

   private var db:LegislatorCreateDb? = null

   fun execute() {
      val writer = LegislatorDbWriter()
      db = LegislatorCreateDb(args.dbName, writer as CollectionWriter<LegislatorData, Database<LegislatorData>>)
      executeCurrentLegislators()
   }

   fun executeCurrentLegislators(){
      val collName = "current"
      val db:LegislatorCreateDb = db!!
      db.openDatabase()
      val writer = db.getWriter()
      writer.before(db)
      writer.drop(collName, db)
      writer.after(db)

      //parse data
      val legislators = ParserJson().parseData(args.files.map { it.toPath() })

      //write to database
      writer.before(db)
      for (legislator in legislators){
         writer.write(legislator, collName, db)
      }
      writer.after(db)
      db.closeDatabase()
   }
}