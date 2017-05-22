package bz.stewart.bracken.db.data

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
data class Sponsor (val bioguide_id:String= "",
                    val name:String= "",
                    @JsonDeserialize(using = FlexibleDateParser::class) @JsonSerialize(using = DbDateSerializer::class)val sponsored_at: Date? = null,
                    val state:String= "",
                    val title:String= "",
                    val type:String? = null,
                    val original_cosponsor:String? = null,
                    @JsonDeserialize(using = FlexibleDateParser::class) @JsonSerialize(using = DbDateSerializer::class)val withdrawn_at: Date? = null,
                    val district:String? = null)
