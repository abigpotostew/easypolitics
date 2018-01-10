package bz.stewart.bracken.rest.data.bills

import bz.stewart.bracken.db.bill.data.Action
import bz.stewart.bracken.shared.data.PublicAction

/**
 * Created by stew on 5/18/17.
 */
class PublicActionDelegated(private val action: Action) : PublicAction by action

fun Action.toPublicActionDelegated(): PublicAction {
    return PublicActionDelegated(this)
}

fun toPublicActionCollection(actions: Array<Action>): Collection<PublicAction> {
    return actions.map(Action::toPublicActionDelegated)
}