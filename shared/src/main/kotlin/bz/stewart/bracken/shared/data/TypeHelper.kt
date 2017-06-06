package bz.stewart.bracken.shared.data

import bz.stewart.bracken.shared.data.party.Party
import bz.stewart.bracken.shared.data.person.LegislatorRole

/**
 * Created by stew on 4/29/17.
 */


enum class VisibleTypeMatcher(val func : VisibleType.()->String){

   CAPS(VisibleType::capitalizedName),
   LOWER(VisibleType::lowercaseName),
   SHORTCODE(VisibleType::shortCode),
   NICE(VisibleType::niceFormat);

   fun matches(value:String, item:VisibleType):Boolean{
      return item.func().equals(value)
   }
}


class TypeResolutionException(msg:String) :Throwable(msg){
}

/**
 * Matching the string value to the property defined by the matcher.
 */
fun <T :VisibleType> matchVisibleType(values: Array<T>,
                matchTo: String, matcher: VisibleTypeMatcher): T {
   for (item: T in values) {
      if (matcher.matches(matchTo,item)) {
         return item
      }
   }
   throw TypeResolutionException("Can't match type ${values[0]::class} with name = '$matchTo'")
}

fun defaultBillTypeMatcher(matchTo:String):BillType{
   return matchVisibleType(BillType.values(),matchTo,VisibleTypeMatcher.SHORTCODE)
}

fun defaultPartyTypeMatcher(matchTo:String):Party{
   return try{
      matchVisibleType(Party.values(), matchTo, VisibleTypeMatcher.NICE)
   }catch(e:TypeResolutionException){
      Party.NONE
   }
}

fun defaultRoleTypeMatcher(matchTo:String):LegislatorRole{
   return try{
      matchVisibleType(LegislatorRole.values(), matchTo, VisibleTypeMatcher.SHORTCODE)
   }catch(e:TypeResolutionException){
      LegislatorRole.NONE
   }
}