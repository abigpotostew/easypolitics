package bz.stewart.bracken.shared.data.person

import bz.stewart.bracken.shared.data.party.Party

/**
 * Created by stew on 1/23/17.
 */
interface PublicLegislator {
   fun getBioguideId(): String
   fun getFirstName(): String
   fun getLastName(): String
   fun getMiddleName(): String?
   fun getOfficialName(): String
   fun getNickName(): String?
   fun getParty(): Party
   fun getRole(): LegislatorRole
   fun getPhoneNumber(): String
   fun getWebsite(): String?
   fun getState(): String
   fun getTwitter(): String
}