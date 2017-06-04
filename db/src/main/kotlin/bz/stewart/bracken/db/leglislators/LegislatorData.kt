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
      val name: NameData = NameData(),
      @JsonIgnore
      val other_names: List<OtherNameData>? = null,
      val bio: BioData = BioData(),
      val leadership_roles: List<LeadershipRoleData>? = null,
      val terms: List<TermData>? = null,
      val family: List<FamilyMember>? = null
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