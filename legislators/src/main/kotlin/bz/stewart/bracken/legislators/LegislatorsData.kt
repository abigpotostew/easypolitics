package bz.stewart.bracken.legislators

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*

/**
 * Created by stew on 5/21/17.
 */
data class LegislatorsYaml(val legislators: List<LegislatorData>)

data class LegislatorData(val id: IdData,
                          val name: NameData,
                          @JsonIgnore
                          val other_names: List<OtherNameData>?,
                          val bio: BioData,
                          val leadership_roles:List<LeadershipRoleData>?,
                          val terms: List<TermData>?,
                          val family:List<FamilyMember>?
                          )

data class FamilyMember (val name:String?,
                         val relation:String?)

data class OtherNameData(val first :String?,
                          val last:String?,
                          val middle:String?,
                          val end: Date?)

data class IdData(
      val bioguide: String, // The alphanumeric ID for this legislator in http://bioguide.congress.gov. Note that at one time some legislators (women who had changed their name when they got married) had two entries on the bioguide website. Only one bioguide ID is included here. This is the best field to use as a primary key.
      val thomas: String?, //The numeric ID for this legislator on http://thomas.gov and http://beta.congress.gov. The ID is stored as a string with leading zeros preserved.
      val lis: String?, //The alphanumeric ID for this legislator found in Senate roll call votes (http://www.senate.gov/pagelayout/legislative/a_three_sections_with_teasers/votes.htm).
      val fec: List<String>?, // A list of IDs for this legislator in Federal Election Commission data.
      val govtrack: String?, //The numeric ID for this legislator on GovTrack.us (stored as an integer).
      val opensecrets: String?, //The alphanumeric ID for this legislator on OpenSecrets.org.
      val votesmart: String?, //The numeric ID for this legislator on VoteSmart.org (stored as an integer).
      val icpsr: String?, //The numeric ID for this legislator in Keith Poole's VoteView.com website, originally based on an ID system by the Interuniversity Consortium for Political and Social Research (stored as an integer).
      val cspan: String?, //The numeric ID for this legislator on C-SPAN's video website, e.g. http://www.c-spanvideo.org/person/1745 (stored as an integer).
      val wikipedia: String?, //The Wikipedia page name for the person (spaces are given as spaces, not underscores).
      val ballotpedia: String?, //The ballotpedia.org page name for the person (spaces are given as spaces, not underscores).
      val maplight: String?, //The numeric ID for this legislator on maplight.org (stored as an integer).
      val house_history: String?, //The numeric ID for this legislator on http://history.house.gov/People/Search/. The ID is present only for members who have served in the U.S. House.
      val bioguide_previous: String?, //When bioguide.congress.gov mistakenly listed a legislator under multiple IDs, this field is a list of alternative IDs. (This often ocurred for women who changed their name.) The IDs in this list probably were removed from bioguide.congress.gov but might still be in use in the wild.
      val wikidata:String?,
      val google_entity_id:String?
                 )

data class NameData(
      val first: String?, //The legislator's first name. Sometimes a first initial and period (e.g. in W. Todd Akin), in which case it is suggested to not use the first name for display purposes.
      val middle: String?, //The legislator's middle name or middle initial (with period).
      val last: String?, //The legislator's last name. Many last names include non-ASCII characters. When building search systems, it is advised to index both the raw value as well as a value with extended characters replaced with their ASCII equivalents (in Python that's: u"".join(c for c in unicodedata.normalize('NFKD', lastname) if not unicodedata.combining(c))).
      val suffix: String?, //A suffix on the legislator's name, such as "Jr." or "III".
      val nickname: String?, //The legislator's nick name when used as a common alternative to his first name.
      val official_full: String?, //The full name of the legislator according to the House or Senate (usually first, middle initial, nickname, last, and suffix). Present for those serving on 2012-10-30 and later.
      @JsonIgnore
      val other_names: List<String>?
                   )

data class BioData(
      val birthday: String?, //The legislator's birthday, in YYYY-MM-DD format.
      val gender: String?, //The legislator's gender, either "M" or "F". (In historical data, we've worked backwards from history.house.gov's Women in Congress feature.)
      val religion: String?//The legislator's religion.
                  )

data class TermData(
      val type: String?, //The type of the term. Either "sen" for senators or "rep" for representatives and delegates to the House.
      val start: Date?, //The date legislative service began: the date the legislator was sworn in, if known, or else the beginning of the legislator's term. Since 1935 regularly elected terms begin on January 3 at noon on odd-numbered years, but when Congress does not first meet on January 3, term start dates might reflect that swearing-in occurred on a later date. (Prior to 1935, terms began on March 4 of odd-numbered years, see here.) Formatted as YYYY-MM-DD.
      val end: Date?, //The date the term ended (because the Congress ended or the legislator died or resigned, etc.). End dates follow the Constitutional end of a term. Since 1935, terms begin and end on January 3 at noon in odd-numbered years, and thus a term end date may also be a term start date. Prior to 1935, terms began on March 4 and ended either on March 3 or March 4. The end date is the last date on which the legislator served this term. Unlike the start date, whether Congress was in session or not does not affect the value of this field.
      val state: String?, //The two-letter, uppercase USPS abbreviation for the state that the legislator is serving from. See below.
      val district: String?, //For representatives, the district number they are serving from. At-large districts are district 0. In historical data, unknown district numbers are recorded as -1.
      @JsonProperty("class")
      val classVal: String?, //For senators, their election class (1, 2, or 3). Note that this is unrelated to seniority.
      val state_rank: String?, //For senators, whether they are the "junior" or "senior" senator (only valid if the term is current, otherwise the senator's rank at the time the term ended).
      val party: String?, //The political party of the legislator. If the legislator changed parties, this is the most recent party held during the term and party_affiliations will be set. Values are typically "Democrat", "Independent", or "Republican". The value typically matches the political party of the legislator on the ballot in his or her last election, although for state affiliate parties such as "Democratic Farmer Labor" we will use the national party name ("Democrat") instead to keep the values of this field normalized.
      val caucus: String?, //For independents, the party that the legislator caucuses with, using the same values as the party field. Omitted if the legislator caucuses with the party indicated in the party field. When in doubt about the difference between the party and caucus fields, the party field is what displays after the legislator's name (i.e. "(D)") but the caucus field is what normally determines committee seniority. This field was added starting with terms for the 113th Congress.
      val party_affiliations: List<PartyAffiliation>?, //This field is present if the legislator changed party or caucus affiliation during the term. The value is a list of time periods, with start and end dates, each of which has a party field and a caucus field if applicable, with the same meanings as the main party and caucus fields. The time periods cover the entire term, so the first start will match the term start, the last end will match the term end, and the last party (and caucus if present) will match the term party (and caucus).
      val url: String?, //The official website URL of the legislator (only valid if the term is current).
      val address: String?, //The mailing address of the legislator's Washington, D.C. office (only valid if the term is current, otherwise the last known address).
      val phone: String?, //The phone number of the legislator's Washington, D.C. office (only valid if the term is current, otherwise the last known number).
      val fax: String?, //The fax number of the legislator's Washington, D.C. office (only valid if the term is current, otherwise the last known number).
      val contact_form: String?, //The website URL of the contact page on the legislator's official website (only valid if the term is current, otherwise the last known URL).
      val office: String?, //Similar to the address field, this is just the room and building number, suitable for display (only valid if the term is current, otherwise the last known office).
      val rss_url: String? //The URL to the official website's RSS feed (only valid if the term is current, otherwise the last known URL).
                   )

data class PartyAffiliation (val start:String?,
                             val end:String?,
                             val party:String?)

data class LeadershipRoleData(
      val title: String?, //Minority Leader
      val chamber: String?, //senate
      val start: Date?, //'2007-01-04'
      val end: Date?, //'2009-01-06'
      val state: String?, //VA
      val district: String?, //7
      val party: String?, //Republican
      val url: String?, //http://cantor.house.gov
      val address: String?, //303 Cannon HOB; Washington DC 20515-4607
      val phone: String?, //202-225-2815
      val fax: String?, //202-225-0011
      val contact_form: String?, //https://writerep.house.gov/writerep/welcome.shtml
      val office: String?, //303 Cannon House Office Building
      val rss_url: String? //http://cantor.house.gov/rss.xml
                             )