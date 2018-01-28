package bz.stewart.bracken.db.leglislators

import bz.stewart.bracken.db.DbRuntime
import bz.stewart.bracken.db.database.ClientBuilder
import bz.stewart.bracken.db.database.Database
import bz.stewart.bracken.db.database.DatabaseClient
import bz.stewart.bracken.db.database.index.SyncIndex
import bz.stewart.bracken.db.database.mongo.AbstractMongoDb
import bz.stewart.bracken.db.database.mongo.CollectionWriter
import bz.stewart.bracken.db.database.mongo.emptyDatabaseWriter
import bz.stewart.bracken.db.leglislators.data.LegislatorData
import bz.stewart.bracken.db.leglislators.data.SocialMapper
import bz.stewart.bracken.db.leglislators.index.LegislatorIndexDefinition
import com.mongodb.MongoClient
import mu.KLogging
import org.litote.kmongo.find
import org.litote.kmongo.formatJson
import org.litote.kmongo.json

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

        val runDb = db!!

        val socialMapper = if (args.socialFile != null) {
            SocialMapper(ParserSocialJson().parseData(
                args.socialFile!!.toPath()))
        } else {
            SocialMapper(emptyList())
        }

        runDb.use {
            executeCurrentLegislators(socialMapper)
        }
        runDb.use {
            indexCollection(runDb)
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

        if (this.args.resetMode) {
            writer.before(db)
            writer.drop(collName, db)
            logger.info { "Dropped collection '$collName' in preparation to load fresh data." }
            writer.after(db)
        }

        //parse data
        val legislators = ParserLegislatorJson().parseData(args.files.map { it.toPath() })
        socialMapper.associateSocialToPeople(legislators)

        //write to database
        writer.before(db)
        for (legislator in legislators) {
            writeDataIfNecessary(legislator, writer, collName, db)
        }
        writer.after(db)
        logger.info { "Successfully wrote ${legislators.size} current legislators to database." }
    }

    private fun indexCollection(db: LegislatorCreateDb) {
        SyncIndex(db, { LegislatorIndexDefinition(it) }).doSync(this.args.testMode)
    }

    private fun getClient(): DatabaseClient<MongoClient> {
        return ClientBuilder(this.args.dbName,
            this.args.hostname,
            this.args.port,
            this.args.username,
            this.args.password).createClient()
    }

    private fun writeDataIfNecessary(legislatorData: LegislatorData,
                                     writer: CollectionWriter<LegislatorData, AbstractMongoDb<LegislatorData>>,
                                     collName: String,
                                     db: LegislatorCreateDb) {
        var existing: LegislatorData? = null
        db.queryCollection(collName, {
            val id = legislatorData.id.bioguide.json
            val found = find(
                "{\"id.buiguide\":$id }".formatJson())
            existing = found.first()
        })
        if (existing == null || (existing != null && existing != legislatorData)) {
            writer.write(legislatorData, collName, db)
        }
    }

}