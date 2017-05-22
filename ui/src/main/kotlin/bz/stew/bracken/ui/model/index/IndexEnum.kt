package bz.stew.bracken.ui.model.index

import bz.stewart.bracken.shared.data.FixedStatus
import bz.stewart.bracken.shared.data.MajorStatus
import kotlin.js.Date
import kotlin.reflect.KClass

/**
 * This is not mapped to any html.
 * indexClass is the class that is being indexed, all for billData instances
 * Created by stew on 2/8/17.
 */
enum class IndexEnum(val indexClass: KClass<*>) {
   NONE(Any::class), FixedStatusIndex(FixedStatus::class), IntroDate(Date::class), LastUpdatedDate(Date::class),
   LastMajorStatus(MajorStatus::class);
}