package bz.stewart.bracken.db.leglislators

import bz.stewart.bracken.db.DbRuntime
import bz.stewart.bracken.db.database.ClientBuilder
import bz.stewart.bracken.db.database.Database
import bz.stewart.bracken.db.database.DatabaseClient
import bz.stewart.bracken.db.database.mongo.AbstractMongoDb
import bz.stewart.bracken.db.database.mongo.CollectionWriter
import bz.stewart.bracken.db.database.mongo.DefaultMongoClient
import bz.stewart.bracken.db.database.mongo.RemoteMongoClient
import bz.stewart.bracken.db.database.mongo.emptyDatabaseWriter
import bz.stewart.bracken.db.leglislators.data.LegislatorData
import bz.stewart.bracken.db.leglislators.data.SocialMapper
import com.mongodb.MongoClient
import mu.KLogging

class LegislatorRuntime(private val args: LegislatorArguments) : DbRuntime {
    companion object : KLogging()

    private var db: LegislatorCreateDb? = null

    override fun validateArgs() {
        return //TODO
    }

    override fun run() {
        val writer: CollectionWriter<LegislatorData, AbstractMongoDb<LegislatorData>> = if (args.testMode) {
            emptyDatabaseWriter<LegislatorData, Database<LegislatorData>>()
        } else {
            LegislatorDbWriter()
        }
        val client = getClient()
        db = LegislatorCreateDb(client, writer)

        val socialMapper = if (args.socialFile != null) {
            SocialMapper(ParserSocialJson().parseData(
                args.socialFile!!.toPath()))
        } else {
            SocialMapper(emptyList())
        }

        db.use {
            executeCurrentLegislators(socialMapper)
        }

        if (args.testMode == true) {
            logger.info { "Completed database update. Test mode enabled. No data was updated." }
        } else {
            logger.info { "Completed legislators database update." }
        }
    }

    private fun executeCurrentLegislators(socialMapper: SocialMapper) {

        val collName = db?.getCollectionName() ?: "legislators"
        val db: LegislatorCreateDb = db!!
        db.openDatabase()
        val writer = db.getWriter()
        writer.before(db)
        writer.drop(collName, db)
        logger.info { "Dropped collection '$collName' in preparation to load fresh data." }
        writer.after(db)

        //parse data
        val legislators = ParserLegislatorJson().parseData(args.files.map { it.toPath() })
        socialMapper.associateSocialToPeople(legislators)

        //write to database
        writer.before(db)
        for (legislator in legislators) {
            writer.write(legislator, collName, db)
        }
        writer.after(db)
        logger.info { "Successfully wrote ${legislators.size} current legislators to database." }
    }

    private fun getClient(): DatabaseClient<MongoClient> {
        return ClientBuilder(this.args.dbName,
            this.args.hostname,
            this.args.port,
            this.args.username,
            this.args.password).createClient()
    }
}