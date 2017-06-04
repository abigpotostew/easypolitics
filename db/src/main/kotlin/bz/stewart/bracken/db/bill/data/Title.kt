package bz.stewart.bracken.db.bill.data

import bz.stewart.bracken.shared.data.PublicTitle
import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Created by stew on 3/9/17.
 */
data class Title (
      @JsonProperty("as")val titlaAs:String?,
      val is_for_portion:Boolean,
      val title:String,
      val type:String //[official, short, popular]
      ) : PublicTitle