package bz.stewart.bracken.rest.query

import com.fasterxml.jackson.annotation.JsonInclude

/**
 * Created by stew on 4/1/17.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
class BillQuery {

   var congress:String? = null
}