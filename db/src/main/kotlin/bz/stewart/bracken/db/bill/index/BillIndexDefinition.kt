package bz.stewart.bracken.db.bill.index

import bz.stewart.bracken.db.bill.data.Bill
import bz.stewart.bracken.db.bill.data.Title
import bz.stewart.bracken.db.database.index.AbstractIndexedCollection
import bz.stewart.bracken.db.database.index.IndexFieldBuilder
import bz.stewart.bracken.db.database.index.IndexSortTypes
import bz.stewart.bracken.db.database.index.IndexedField
import com.mongodb.client.MongoCollection

class BillIndexDefinition(collection: MongoCollection<Bill>) : AbstractIndexedCollection<Bill>(collection) {
    override val indicies: Set<IndexedField<Bill>>

    init {
        val indexes = mutableSetOf<IndexedField<Bill>>()

        val introducedIndex = IndexFieldBuilder.create(Bill::introduced_at,
            Bill::class,
            IndexSortTypes.DESCENDING)
        indexes.add(introducedIndex)

        val billIdIndex = IndexFieldBuilder.create(Bill::bill_id,
            Bill::class,
            IndexSortTypes.ASCENDING)
        indexes.add(billIdIndex)

        val titlesIndex = IndexFieldBuilder.createText(Bill::titlesArr,
            Bill::class, Title::title, Title::class)
        indexes.add(titlesIndex)

        indicies = indexes
    }
}