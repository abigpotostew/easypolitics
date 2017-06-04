package bz.stewart.bracken.db.leglislators

import bz.stewart.bracken.db.database.DbItem
import com.fasterxml.jackson.annotation.JsonIgnore
import org.bson.types.ObjectId
import java.util.*

/**
 * Created by stew on 5/21/17.
 */
data class LegislatorData(
      val _id: ObjectId? = null,
      val id: IdData,
      val name: NameData,
      @JsonIgnore
      val other_names: List<OtherNameData>?,
      val bio: BioData,
      val leadership_roles: List<LeadershipRoleData>?,
      val terms: List<TermData>?,
      val family: List<FamilyMember>?
                         ) : DbItem {
   override fun getDbId(): ObjectId? {
      return _id
   }

   @JsonIgnore
   override fun getLastModified(): Date? {
      TODO()
   }

   @JsonIgnore
   override fun setLastModified(lastMod: Date?) {
      TODO()
   }

   @JsonIgnore
   override fun <T : DbItem> equalLessId(other: T): Boolean {
      TODO()
   }
}