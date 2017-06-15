package bz.stewart.bracken.shared.data

import bz.stewart.bracken.shared.data.party.Party
import bz.stewart.bracken.shared.data.person.LegislatorRole

/**
 * Created by stew on 6/12/17.
 */
class TypeHelperDefaults {
   companion object Defaults{

      fun defaultBillTypeMatcher(matchTo:String):BillType{
         return matchVisibleType(BillType.values(),matchTo,VisibleTypeMatcher.SHORTCODE)
      }

      fun defaultPartyTypeMatcher(matchTo:String): Party {
         return try{
            matchVisibleType(Party.values(), matchTo, VisibleTypeMatcher.NICE)
         }catch(e:TypeResolutionException){
            Party.NONE
         }
      }

      fun defaultRoleTypeMatcher(matchTo:String): LegislatorRole {
         return try{
            matchVisibleType(LegislatorRole.values(), matchTo, VisibleTypeMatcher.SHORTCODE)
         }catch(e:TypeResolutionException){
            LegislatorRole.NONE
         }
      }
   }
}