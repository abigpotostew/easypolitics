package bz.stewart.bracken.shared.data

import bz.stewart.bracken.shared.view.ViewConstant
import bz.stewart.bracken.shared.view.ViewConstants

/**
 * Created by stew on 2/21/17.
 */
enum class MajorStatus(val viewString: ViewConstant = ViewConstants.Empty,
                       private val shortCode: String) : VisibleType {
   NONE(ViewConstants.Empty, ""),
   INTRODUCED(ViewConstants.Introduced, "intro"),
   FAILED(ViewConstants.Failed, "fail"),
   PASSED_HOUSE(ViewConstants.PassedHouse, "passh"),
   PASSED_SENATE(ViewConstants.PassedSenate, "passsen"),
   SIGNED_PRESIDENT(ViewConstants.SignedByPresident, "pres"),
   LAW(ViewConstants.EnactedLaw, "law");

   override fun lowercaseName(): String {
      return viewString.lowercaseName()
   }

   override fun capitalizedName(): String {
      return viewString.capitalizedName()
   }

   override fun niceFormat(): String {
      return viewString.niceFormat()
   }

   override fun shortLabel(): String {
      return viewString.shortLabel()
   }

   //TODO make this generic. This is duplicate from FixedStatus.valueAt()
   companion object {
      fun valueAt(i: Int): MajorStatus {
         for (fs: MajorStatus in MajorStatus.values()) {
            if (fs.ordinal == i) {
               return fs
            }
         }
         throw IllegalArgumentException("Can't convert int of value $i to MajorStatus.")
      }
   }

   override fun shortCode(): String {
      return shortCode
   }
}