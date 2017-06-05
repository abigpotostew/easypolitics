package bz.stewart.bracken.db.leglislators.data

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
data class SocialData(val twitter: String? = null,
                      val facebook: String? = null,
                      val youtube: String? = null,
                      val youtube_id: String? = null,
                      val instagram: String? = null,
                      val instagram_id: Long? = null,
                      val twitter_id: Long? = null
                     )