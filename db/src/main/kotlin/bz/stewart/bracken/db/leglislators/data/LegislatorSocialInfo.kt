package bz.stewart.bracken.db.leglislators.data

import com.fasterxml.jackson.annotation.JsonInclude

/**
 * Created by stew on 6/4/17.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
data class LegislatorSocialInfo(val id:IdData, val social: SocialData)
