package bz.stewart.bracken.db.leglislators.data

import bz.stewart.bracken.db.database.DbItem
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonInclude
import org.bson.types.ObjectId
import java.util.*

/**
 * Database schema for legislator read from the json file.
 * Created by stew on 5/21/17.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
data class LegislatorData(
      val _id: ObjectId? = null,
      val id: IdData,
      val name: NameData = NameData(),
      @JsonIgnore
      val other_names: List<OtherNameData>? = null,
      val bio: BioData = BioData(),
      val leadership_roles: List<LeadershipRoleData>? = null,
      val terms: List<TermData>? = null,
      val family: List<FamilyMember>? = null,
      var social: SocialData?=null //this is loaded and set from a separate file before writing to db
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