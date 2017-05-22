package bz.stewart.bracken.db.data

import bz.stewart.bracken.shared.data.PublicRelatedBill
import com.fasterxml.jackson.annotation.JsonInclude

/**
 * Created by stew on 3/9/17.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
data class RelatedBill(val type: String, //bill or amendment
                       val reason: String, //ruled-by, included-in, rule,supersedes, unknown, related,identical,amendment
                       val bill_id: String?,
                       val amendment_id: String?, //only if type is amendment
                      val identified_by:String? //string
                      ) : PublicRelatedBill