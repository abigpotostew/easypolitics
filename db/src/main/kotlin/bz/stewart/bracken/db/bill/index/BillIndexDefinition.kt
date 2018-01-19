package bz.stewart.bracken.db.bill.index

import bz.stewart.bracken.db.bill.data.Bill
import bz.stewart.bracken.db.bill.data.Sponsor
import bz.stewart.bracken.db.bill.data.Summary
import bz.stewart.bracken.db.database.index.AbstractIndexedCollection
import bz.stewart.bracken.db.database.index.IndexFieldBuilder
import bz.stewart.bracken.db.database.index.IndexSortTypes
import bz.stewart.bracken.db.database.index.IndexedField
import com.mongodb.client.MongoCollection

class BillIndexDefinition(collection: MongoCollection<Bill>) : AbstractIndexedCollection<Bill>(collection) {
    override val indicies: Set<IndexedField<Bill>>

    init {
        val indexes = mutableSetOf<IndexedField<Bill>>()

        /**
         * Date index
         */
        val introducedIndex = IndexFieldBuilder.create(Bill::introduced_at,
            Bill::class,
            IndexSortTypes.DESCENDING)
        indexes.add(introducedIndex)

        val statusAtIndex = IndexFieldBuilder.create(Bill::status_at,
            Bill::class,
            IndexSortTypes.DESCENDING)
        indexes.add(statusAtIndex)

        val updatedAtIndex = IndexFieldBuilder.create(Bill::updated_at,
            Bill::class,
            IndexSortTypes.DESCENDING)
        indexes.add(updatedAtIndex)

        /**
         * Regular string index
         */
        val billIdIndex = IndexFieldBuilder.create(Bill::bill_id,
            Bill::class,
            IndexSortTypes.ASCENDING)
        indexes.add(billIdIndex)

        val billTypeIndex = IndexFieldBuilder.create(Bill::bill_type,
            Bill::class,
            IndexSortTypes.ASCENDING)
        indexes.add(billTypeIndex)

        val statusIndex = IndexFieldBuilder.create(Bill::currentStatus,
            Bill::class,
            IndexSortTypes.ASCENDING)
        indexes.add(statusIndex)

        val summaryAsIndex = IndexFieldBuilder.createNullable(Bill::billSummary,
            Bill::class,
            Summary::asValue,
            Summary::class,
            IndexSortTypes.ASCENDING)
        indexes.add(summaryAsIndex)

        val sponsorIndex = IndexFieldBuilder.create(Bill::billSponsor,
            Bill::class,
            Sponsor::name,
            Sponsor::class,
            IndexSortTypes.ASCENDING)
        indexes.add(sponsorIndex)

        val sponsorStateIndex = IndexFieldBuilder.create(Bill::billSponsor,
            Bill::class,
            Sponsor::state,
            Sponsor::class,
            IndexSortTypes.ASCENDING)
        indexes.add(sponsorStateIndex)

        val sponsorIdIndex = IndexFieldBuilder.create(Bill::billSponsor,
            Bill::class,
            Sponsor::bioguide_id,
            Sponsor::class,
            IndexSortTypes.ASCENDING)
        indexes.add(sponsorIdIndex)

        val cosponsorIndex = IndexFieldBuilder.createArr(Bill::cosponsorsArr,
            Bill::class,
            Sponsor::name,
            Sponsor::class,
            IndexSortTypes.ASCENDING)
        indexes.add(cosponsorIndex)

        /**
         * Text index, limit to one!
         */
        val allTextIndex = IndexFieldBuilder.createText(Bill::denormalizedAllText, Bill::class)
        indexes.add(allTextIndex)

        indicies = indexes
    }
}