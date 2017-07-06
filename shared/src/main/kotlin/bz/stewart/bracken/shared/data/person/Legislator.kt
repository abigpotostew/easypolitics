package bz.stewart.bracken.shared.data.person

import bz.stewart.bracken.shared.data.party.Party

/**
 * Created by stew on 1/23/17.
 */
class Legislator(private val bioguideId: String,
                 private val firstName: String,
                 private val middleName: String?,
                 private val lastName: String,
                 private val officialName: String,
                 private val nickName: String,
                 private val party: Party,
                 private val role: LegislatorRole,
                 private val state: String,
                 private val twitterId: String,
                 private val phoneNumber: String,
                 private val website: String
                ) : Person {
   override fun getState(): String {
      return state
   }

   override fun getLastName(): String {
      return lastName
   }

   override fun getMiddleName(): String? {
      return middleName
   }

   override fun getOfficialName(): String {
      return officialName
   }

   fun getFullTitle():String{
      return "${getRole().shortLabel()} ${getOfficialName()} [${getState()}]"
   }

   override fun getNickName(): String? {
      return nickName
   }

   override fun getPhoneNumber(): String {
      return phoneNumber
   }

   override fun getWebsite(): String? {
      return website
   }

   override fun getBioguideId(): String {
      return this.bioguideId
   }

   override fun getFirstName(): String {
      return this.firstName
   }

   override fun getParty(): Party {
      return this.party
   }

   override fun getRole(): LegislatorRole {
      return this.role
   }

   override fun getTwitter(): String {
      return this.twitterId
   }
}

val EMPTY_LEG: Legislator = Legislator(bioguideId = "N000001",
                                       firstName = "First",
                                       middleName = "Middle",
                                       lastName = "Last",
                                       officialName = "First Middle Last",
                                       nickName = "Nickname",
                                       party = Party.DEMON,
                                       role = LegislatorRole.NONE,
                                       state = "NOSTATE",
                                       twitterId = "The_Donald",
                                       website = "easypolitics.bracken.bz",
                                       phoneNumber = "123-456-7890"
                                      )

fun emptyLegislator(): Legislator {
   return EMPTY_LEG
}
