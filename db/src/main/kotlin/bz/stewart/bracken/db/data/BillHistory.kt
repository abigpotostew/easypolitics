
package bz.stewart.bracken.db.data

import bz.stewart.bracken.shared.data.PublicBillHistory
import bz.stewart.bracken.db.data.parse.DbDateSerializer
import bz.stewart.bracken.db.data.parse.FlexibleDateParser
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import java.util.*

/**
 * Created by stew on 3/9/17.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
data class BillHistory(val active:String = "",
                       val vetoed:String= "",
                       val enacted:String= "",
                       val awaiting_signature:String= "",
                       @JsonDeserialize(using = FlexibleDateParser::class) @JsonSerialize(using = DbDateSerializer::class)val active_at: Date? = null,//"2009-10-07T14:35:00-04:00"
                       val senate_passage_result:String? = null,//pass or fail
                       val house_passage_result:String? = null,//pass or fail
                       @JsonDeserialize(using = FlexibleDateParser::class) @JsonSerialize(using = DbDateSerializer::class)val house_passage_result_at: Date? = null,
                       @JsonDeserialize(using = FlexibleDateParser::class) @JsonSerialize(using = DbDateSerializer::class)val senate_passage_result_at: Date? = null,
                       val awaiting_signature_since:String? = null,
                       val senate_cloture_result:String? = null,
                       @JsonDeserialize(using = FlexibleDateParser::class) @JsonSerialize(using = DbDateSerializer::class)val senate_cloture_result_at: Date? = null,
                       val house_override_result:String? = null,//pass fail
                       @JsonDeserialize(using = FlexibleDateParser::class) @JsonSerialize(using = DbDateSerializer::class)val house_override_result_at: Date? = null,
                       val senate_override_result:String? = null,//pass or fail
                       @JsonDeserialize(using = FlexibleDateParser::class) @JsonSerialize(using = DbDateSerializer::class)val senate_override_result_at: Date? = null,
                       @JsonDeserialize(using = FlexibleDateParser::class) @JsonSerialize(using = DbDateSerializer::class)val enacted_at: Date? = null,
                       @JsonDeserialize(using = FlexibleDateParser::class) @JsonSerialize(using = DbDateSerializer::class)val vetoed_at: Date? = null
                       ) : PublicBillHistory