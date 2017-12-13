package bz.stewart.bracken.rest.data.bills

import bz.stewart.bracken.db.bill.data.Action
import bz.stewart.bracken.shared.data.PublicAction

/**
 * Created by stew on 5/18/17.
 */
class PublicActionDelegated (private val action:Action):PublicAction by action

//{
//   override fun getActedAt(): String {
//      return action.getActedAt()
//   }
//
//   override fun getText(): String {
//      return action.getText()
//   }
//
//   override fun getActionCode(): String {
//      return action.getActionCode()
//   }
//
//   override fun getCommittess(): Array<String>? {
//      TODO()
//   }
//
//   override fun getNumber(): Int {
//      TODO()
//   }
//
//   override fun getBillReferences(): Array<Reference>? {
//      TODO()
//   }
//
//   override fun getBillIds(): Array<String>? {
//      TODO()
//   }
//}

fun Action.toPublicActionDelegated(): PublicAction {
   return PublicActionDelegated(this)
}

fun toPublicActionCollection(actions: Array<Action>): Collection<PublicAction> {
   return actions.map(Action::toPublicActionDelegated)
}