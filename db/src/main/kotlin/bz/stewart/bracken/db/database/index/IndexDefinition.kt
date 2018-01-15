package bz.stewart.bracken.db.database.index

interface IndexDefinition<T> {
    val field:IndexedField<T>
}