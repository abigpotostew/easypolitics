package bz.stewart.bracken.shared.data

/**
 * Created by stew on 5/18/17.
 */
interface PublicAction {
   fun getActedAt():String
   fun getText():String
   fun getActionCode():String
   //fun getType():String
   fun getCommittess():Array<String>?
   //fun getCalendar():String?
   fun getNumber():Int
   fun getBillReferences():Array<BillReference>?
   fun getBillIds():Array<String>?

}