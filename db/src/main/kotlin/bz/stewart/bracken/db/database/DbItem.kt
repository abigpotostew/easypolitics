package bz.stewart.bracken.db.database

import com.sun.org.apache.xpath.internal.operations.Bool
import java.util.*

/**
 * Created by stew on 3/9/17.
 */
interface DbItem {
   fun getDbId():org.bson.types.ObjectId?
   fun getLastModified():Date?
   fun setLastModified(lastMod:Date?)
   fun <T : DbItem> equalLessId(other: T):Boolean
}