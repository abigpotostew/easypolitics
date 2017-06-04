package bz.stewart.bracken.db.bill.data

import bz.stewart.bracken.shared.data.BillReference
import bz.stewart.bracken.shared.data.PublicAction
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Created by stew on 3/9/17.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
data class Action(val acted_at: String,
                  @JsonProperty("references")
                  val referencesVal: Array<
                        BillReference>?,
                  @JsonProperty("text")
                  val textVal: String,
                  val action_code:String, //legacy??
                  val law:String?, //public,
                  val congress:String?,
                  val calendar:String?, //ignore
                  val type: String?,//[vote,action], todo make type an enum
                  val committees:Array<String>?,
                  val bill_ids:Array<String>?,//any related bills in id form "scres10-113"
                  val suspension:String?,
                  val result:String?,
                  val how:String?,
                  val roll:String?, //int
                  val status:String?, //code
                  val vote_type:String?,
                  val where:String?,
                  val in_committee:String?, // only if in comitte, full comitte name,
                  val in_subcommittee:String?, // full subcom name
                  val committee:String?,
                  @JsonProperty("number")
                  val numberVal:String?,
                  val under:String?
                  ): PublicAction {

   @JsonIgnore
   override fun getActedAt(): String {
      return acted_at
   }

   @JsonIgnore
   override fun getText(): String {
      return textVal
   }

   @JsonIgnore
   override fun getActionCode(): String {
      return action_code
   }

   @JsonIgnore
   override fun getCommittess(): Array<String>? {
      return committees
   }

   @JsonIgnore
   override fun getNumber(): Int {
      return numberVal?.toInt() ?: 0
   }

   @JsonIgnore
   override fun getBillReferences(): Array<BillReference>? {
      return referencesVal
   }

   @JsonIgnore
   override fun getBillIds(): Array<String>? {
      return bill_ids
   }

}