package bz.stewart.bracken.db.leglislators.index

import bz.stewart.bracken.db.database.index.AbstractIndexedCollection
import bz.stewart.bracken.db.database.index.IndexFieldBuilder
import bz.stewart.bracken.db.database.index.IndexSortTypes
import bz.stewart.bracken.db.database.index.IndexedField
import bz.stewart.bracken.db.leglislators.data.IdData
import bz.stewart.bracken.db.leglislators.data.LegislatorData
import bz.stewart.bracken.db.leglislators.data.NameData
import com.mongodb.client.MongoCollection

class LegislatorIndexDefinition(collection: MongoCollection<LegislatorData>) : AbstractIndexedCollection<LegislatorData>(
    collection) {
    override val indicies: Set<IndexedField<LegislatorData>>

    init {
        val indexes = mutableSetOf<IndexedField<LegislatorData>>()

        /**
         * Regular string index
         */
        val bioIdIdx = IndexFieldBuilder.create(LegislatorData::id,
            LegislatorData::class,
            IdData::bioguide,
            IdData::class,
            IndexSortTypes.ASCENDING)
        indexes.add(bioIdIdx)

        val officialNameIdIdx = IndexFieldBuilder.createNullable(LegislatorData::name,
            LegislatorData::class,
            NameData::official_full,
            NameData::class,
            IndexSortTypes.ASCENDING)
        indexes.add(officialNameIdIdx)

        val firstNameIdIdx = IndexFieldBuilder.createNullable(LegislatorData::name,
            LegislatorData::class,
            NameData::first,
            NameData::class,
            IndexSortTypes.ASCENDING)
        indexes.add(firstNameIdIdx)

        val lastNameIdIdx = IndexFieldBuilder.createNullable(LegislatorData::name,
            LegislatorData::class,
            NameData::last,
            NameData::class,
            IndexSortTypes.ASCENDING)
        indexes.add(lastNameIdIdx)

        indicies = indexes
    }
}