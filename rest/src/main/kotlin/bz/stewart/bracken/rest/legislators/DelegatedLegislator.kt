package bz.stewart.bracken.rest.legislators

import bz.stewart.bracken.db.leglislators.data.LegislatorData
import bz.stewart.bracken.db.leglislators.data.TermData
import bz.stewart.bracken.db.leglislators.data.emptyTermDate
import bz.stewart.bracken.shared.data.defaultPartyTypeMatcher
import bz.stewart.bracken.shared.data.defaultRoleTypeMatcher
import bz.stewart.bracken.shared.data.party.Party
import bz.stewart.bracken.shared.data.person.LegislatorRole
import bz.stewart.bracken.shared.data.person.Person

/**
 * Created by stew on 6/5/17.
 */
class DelegatedLegislator (private val p:LegislatorData):Person{

   private val roles :List<TermData> = p.terms ?: emptyList()
   private val currentRole:TermData = roles.maxBy { it.end?.time ?: -1 } ?: emptyTermDate() //maybe null

   private val DEFAULT_NO_NAME = ""
   override fun getBioguideId(): String {
      return p.id.bioguide
   }

   override fun getFirstName(): String {
      return p.name.first ?: DEFAULT_NO_NAME
   }

   override fun getLastName(): String {
      return p.name.last ?: DEFAULT_NO_NAME
   }

   override fun getMiddleName(): String? {
      return p.name.middle ?: DEFAULT_NO_NAME
   }

   override fun getOfficialName(): String {
      return p.name.official_full ?: DEFAULT_NO_NAME
   }

   override fun getNickName(): String? {
      return p.name.nickname ?: DEFAULT_NO_NAME
   }

   override fun getParty(): Party {
      return defaultPartyTypeMatcher(currentRole.party ?: "")
   }

   override fun getRole(): LegislatorRole {
      return defaultRoleTypeMatcher(currentRole.type ?: "")
   }

   override fun getPhoneNumber(): String {
      return currentRole.phone ?: ""
   }

   override fun getWebsite(): String? {
      return currentRole.url
   }

   override fun getState(): String {
      return currentRole.state ?: ""
   }

   override fun getTwitter(): String {
      return p.social?.twitter ?: ""
   }
}