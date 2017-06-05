package bz.stewart.bracken.db.leglislators.data

/**
 * Created by stew on 6/4/17.
 */
class SocialMapper (socialInfoParsed:List<LegislatorSocialInfo>){

   val socialInfoMap = socialInfoParsed.associate { Pair(it.id.bioguide, it) }

   /**
    * Updates the LegislatorData with the social info by mapping bioguide id.
    */
   fun associateSocialToPeople(legislatorsParsed:List<LegislatorData>){
      for (person in legislatorsParsed){
         val socialInfo = socialInfoMap[person.id.bioguide]
         if(socialInfo!=null){
            person.social = socialInfo.social
         }
      }
   }
}