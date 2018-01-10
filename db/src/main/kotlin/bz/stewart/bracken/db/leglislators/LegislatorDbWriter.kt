package bz.stewart.bracken.db.leglislators

import bz.stewart.bracken.db.database.mongo.DefaultMongoWriter
import bz.stewart.bracken.db.leglislators.data.LegislatorData

/**
 * Created by stew on 6/4/17.
 */
class LegislatorDbWriter : DefaultMongoWriter<LegislatorData>()