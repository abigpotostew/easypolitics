package bz.stewart.bracken.db.database.index

import bz.stewart.bracken.db.database.DbItem

interface IndexDefinition<T: DbItem> {
    val field:IndexedField<T>
}