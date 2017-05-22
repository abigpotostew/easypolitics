package bz.stewart.bracken.rest.bills

import bz.stewart.bracken.db.data.Sponsor
import bz.stewart.bracken.shared.data.PublicSponsor

/**
 * Created by stew on 5/17/17.
 */
class DelegatedPublicSponsor(private val sponsor: Sponsor):PublicSponsor {
   override fun getFirstname(): String {
      return sponsor.name.substringAfter(',').substringBefore(' ')
   }

   override fun getLastname(): String {
      return sponsor.name.substringBefore(',')
   }

//   override fun middlename(): String? {
//      return "" // todo
//   }

   override fun getName(): String {
      return "${sponsor.title}. ${getFirstname()} ${getLastname()}"
   }

//   override fun nickname(): String? {
//      return "derp"
//   }

   override fun getSortname(): String {
      return sponsor.name
   }

//   override fun twitter(): String {
//      TODO()
//   }
//
//   override fun youtube(): String {
//      TODO()
//   }
//
//   override fun party(): String {
//      TODO()
//   }
//
//   override fun link(): String? {
//      TODO()
//   }
//
//   override fun phone(): String? {
//      TODO()
//   }
}


fun Sponsor.toPublicSponsorDelegated():PublicSponsor{
   return DelegatedPublicSponsor(this)
}

fun toPublicSponsorCollection(sponsors: Array<Sponsor>): Collection<PublicSponsor> {
   return sponsors.map (Sponsor::toPublicSponsorDelegated)
}