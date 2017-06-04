package bz.stewart.bracken.db.leglislators

data class FamilyMember(val name: String? = null,
                        val relation: String? = null)

data class OtherNameData(val first: String? = null,
                         val last: String? = null,
                         val middle: String? = null,
                         val end: java.util.Date? = null)

data class IdData(
      val bioguide: String, // The alphanumeric ID for this legislator in http://bioguide.congress.gov. Note that at one time some legislators (women who had changed their name when they got married) had two entries on the bioguide website. Only one bioguide ID is included here. This is the best field to use as a primary key.
      val thomas: String? = null, //The numeric ID for this legislator on http://thomas.gov and http://beta.congress.gov. The ID is stored as a string with leading zeros preserved.
      val lis: String? = null, //The alphanumeric ID for this legislator found in Senate roll call votes (http://www.senate.gov/pagelayout/legislative/a_three_sections_with_teasers/votes.htm).
      val fec: List<String>? = null, // A list of IDs for this legislator in Federal Election Commission data.
      val govtrack: String? = null, //The numeric ID for this legislator on GovTrack.us (stored as an integer).
      val opensecrets: String? = null, //The alphanumeric ID for this legislator on OpenSecrets.org.
      val votesmart: String? = null, //The numeric ID for this legislator on VoteSmart.org (stored as an integer).
      val icpsr: String? = null, //The numeric ID for this legislator in Keith Poole's VoteView.com website, originally based on an ID system by the Interuniversity Consortium for Political and Social Research (stored as an integer).
      val cspan: String? = null, //The numeric ID for this legislator on C-SPAN's video website, e.g. http://www.c-spanvideo.org/person/1745 (stored as an integer).
      val wikipedia: String? = null, //The Wikipedia page name for the person (spaces are given as spaces, not underscores).
      val ballotpedia: String? = null, //The ballotpedia.org page name for the person (spaces are given as spaces, not underscores).
      val maplight: String? = null, //The numeric ID for this legislator on maplight.org (stored as an integer).
      val house_history: String? = null, //The numeric ID for this legislator on http://history.house.gov/People/Search/. The ID is present only for members who have served in the U.S. House.
      val bioguide_previous: String? = null, //When bioguide.congress.gov mistakenly listed a legislator under multiple IDs, this field is a list of alternative IDs. (This often ocurred for women who changed their name.) The IDs in this list probably were removed from bioguide.congress.gov but might still be in use in the wild.
      val wikidata: String? = null,
      val google_entity_id: String? = null
                 )

data class NameData(
      val first: String? = null, //The legislator's first name. Sometimes a first initial and period (e.g. in W. Todd Akin), in which case it is suggested to not use the first name for display purposes.
      val middle: String? = null, //The legislator's middle name or middle initial (with period).
      val last: String? = null, //The legislator's last name. Many last names include non-ASCII characters. When building search systems, it is advised to index both the raw value as well as a value with extended characters replaced with their ASCII equivalents (in Python that's: u"".join(c for c in unicodedata.normalize('NFKD', lastname) if not unicodedata.combining(c))).
      val suffix: String? = null, //A suffix on the legislator's name, such as "Jr." or "III".
      val nickname: String? = null, //The legislator's nick name when used as a common alternative to his first name.
      val official_full: String? = null, //The full name of the legislator according to the House or Senate (usually first, middle initial, nickname, last, and suffix). Present for those serving on 2012-10-30 and later.
      @com.fasterxml.jackson.annotation.JsonIgnore
      val other_names: List<String>? = null
                   )

data class BioData(
      val birthday: String? = null, //The legislator's birthday, in YYYY-MM-DD format.
      val gender: String? = null, //The legislator's gender, either "M" or "F". (In historical data, we've worked backwards from history.house.gov's Women in Congress feature.)
      val religion: String? = null//The legislator's religion.
                  )

data class TermData(
      val type: String? = null, //The type of the term. Either "sen" for senators or "rep" for representatives and delegates to the House.
      val start: java.util.Date?, //The date legislative service began: the date the legislator was sworn in, if known, or else the beginning of the legislator's term. Since 1935 regularly elected terms begin on January 3 at noon on odd-numbered years, but when Congress does not first meet on January 3, term start dates might reflect that swearing-in occurred on a later date. (Prior to 1935, terms began on March 4 of odd-numbered years, see here.) Formatted as YYYY-MM-DD.
      val end: java.util.Date?, //The date the term ended (because the Congress ended or the legislator died or resigned, etc.). End dates follow the Constitutional end of a term. Since 1935, terms begin and end on January 3 at noon in odd-numbered years, and thus a term end date may also be a term start date. Prior to 1935, terms began on March 4 and ended either on March 3 or March 4. The end date is the last date on which the legislator served this term. Unlike the start date, whether Congress was in session or not does not affect the value of this field.
      val state: String? = null, //The two-letter, uppercase USPS abbreviation for the state that the legislator is serving from. See below.
      val district: String? = null, //For representatives, the district number they are serving from. At-large districts are district 0. In historical data, unknown district numbers are recorded as -1.
      @com.fasterxml.jackson.annotation.JsonProperty("class")
      val classVal: String? = null, //For senators, their election class (1, 2, or 3). Note that this is unrelated to seniority.
      val state_rank: String? = null, //For senators, whether they are the "junior" or "senior" senator (only valid if the term is current, otherwise the senator's rank at the time the term ended).
      val party: String? = null, //The political party of the legislator. If the legislator changed parties, this is the most recent party held during the term and party_affiliations will be set. Values are typically "Democrat", "Independent", or "Republican". The value typically matches the political party of the legislator on the ballot in his or her last election, although for state affiliate parties such as "Democratic Farmer Labor" we will use the national party name ("Democrat") instead to keep the values of this field normalized.
      val caucus: String? = null, //For independents, the party that the legislator caucuses with, using the same values as the party field. Omitted if the legislator caucuses with the party indicated in the party field. When in doubt about the difference between the party and caucus fields, the party field is what displays after the legislator's name (i.e. "(D)") but the caucus field is what normally determines committee seniority. This field was added starting with terms for the 113th Congress.
      val party_affiliations: List<PartyAffiliation>?, //This field is present if the legislator changed party or caucus affiliation during the term. The value is a list of time periods, with start and end dates, each of which has a party field and a caucus field if applicable, with the same meanings as the main party and caucus fields. The time periods cover the entire term, so the first start will match the term start, the last end will match the term end, and the last party (and caucus if present) will match the term party (and caucus).
      val url: String? = null, //The official website URL of the legislator (only valid if the term is current).
      val address: String? = null, //The mailing address of the legislator's Washington, D.C. office (only valid if the term is current, otherwise the last known address).
      val phone: String? = null, //The phone number of the legislator's Washington, D.C. office (only valid if the term is current, otherwise the last known number).
      val fax: String? = null, //The fax number of the legislator's Washington, D.C. office (only valid if the term is current, otherwise the last known number).
      val contact_form: String? = null, //The website URL of the contact page on the legislator's official website (only valid if the term is current, otherwise the last known URL).
      val office: String? = null, //Similar to the address field, this is just the room and building number, suitable for display (only valid if the term is current, otherwise the last known office).
      val rss_url: String? = null //The URL to the official website's RSS feed (only valid if the term is current, otherwise the last known URL).
                   )

data class PartyAffiliation(val start: String? = null,
                            val end: String? = null,
                            val party: String? = null)

data class LeadershipRoleData(
      val title: String? = null, //Minority Leader
      val chamber: String? = null, //senate
      val start: java.util.Date?, //'2007-01-04'
      val end: java.util.Date?, //'2009-01-06'
      val state: String? = null, //VA
      val district: String? = null, //7
      val party: String? = null, //Republican
      val url: String? = null, //http://cantor.house.gov
      val address: String? = null, //303 Cannon HOB; Washington DC 20515-4607
      val phone: String? = null, //202-225-2815
      val fax: String? = null, //202-225-0011
      val contact_form: String? = null, //https://writerep.house.gov/writerep/welcome.shtml
      val office: String? = null, //303 Cannon House Office Building
      val rss_url: String? = null //http://cantor.house.gov/rss.xml
                             )