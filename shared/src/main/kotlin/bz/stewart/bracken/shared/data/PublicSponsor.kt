package bz.stewart.bracken.shared.data

/**
 * * You should be using DelegatedLegislator instead
 * Created by stew on 5/17/17.
 */
interface PublicSponsor {

   fun getFirstname():String
   fun getLastname():String
   //fun middlename():String?
   fun getName():String //title first middle last party
   //fun nickname():String?
   fun getSortname():String
   /*fun twitter():String
   fun youtube():String
   fun party():String
   fun link():String?
   fun phone():String?*/
   /*

   "sponsor": {
    "bioguideid": "H000463",
    "birthday": "1921-10-18",
    "cspanid": null,
    "firstname": "Jesse",
    "gender": "male",
    "gender_label": "Male",
    "id": 300154,
    "lastname": "Helms",
    "link": "https://www.govtrack.us/congress/members/jesse_helms/300154",
    "middlename": "",
    "name": "Sen. Jesse Helms [R-NC, 1973-2002]",
    "namemod": "",
    "nickname": "",
    "osid": null,
    "pvsid": null,
    "sortname": "Helms, Jesse (Sen.) [R-NC, 1973-2002]",
    "twitterid": null,
    "youtubeid": null
    */
}