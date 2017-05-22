package bz.stewart.bracken.rest

import org.springframework.boot.autoconfigure.web.WebMvcRegistrationsAdapter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.annotation.AnnotationUtils
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition
import org.springframework.web.servlet.mvc.method.RequestMappingInfo
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping
import java.lang.reflect.Method


/**
 * Created by stew on 3/19/17.
 */
@Configuration
class BillsControllerConfiguration {

   public val API_BASE_PATH = "api/v1"


   // found on http://stackoverflow.com/a/39907655/595265
   @Bean
   fun webMvcRegistrationsHandlerMapping(): WebMvcRegistrationsAdapter {
      return object : WebMvcRegistrationsAdapter() {
         override fun getRequestMappingHandlerMapping(): RequestMappingHandlerMapping {
            return object : RequestMappingHandlerMapping() {


               override fun registerHandlerMethod(handler: Any, method: Method, mapping: RequestMappingInfo) {
                  var mapping = mapping
                  val beanType = method.getDeclaringClass()
                  if (AnnotationUtils.findAnnotation<RestController>(beanType, RestController::class.java) != null) {
                     val apiPattern = PatternsRequestCondition(API_BASE_PATH)
                           .combine(mapping.patternsCondition)

                     mapping = RequestMappingInfo(mapping.name, apiPattern,
                                                  mapping.methodsCondition, mapping.paramsCondition,
                                                  mapping.headersCondition, mapping.consumesCondition,
                                                  mapping.producesCondition, mapping.customCondition)
                  }

                  super.registerHandlerMethod(handler, method, mapping)
               }
            }
         }
      }
   }
}