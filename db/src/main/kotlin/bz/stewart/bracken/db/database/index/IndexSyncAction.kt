package bz.stewart.bracken.db.database.index

interface IndexSyncAction {
    fun doSync(testMode:Boolean)
}