package bz.stewart.bracken.rest

import bz.stewart.bracken.rest.query.BasicQueryResult
import bz.stewart.bracken.rest.query.QueryResultImpl
import org.junit.After
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.context.annotation.Configuration
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.web.util.UriComponentsBuilder
import java.net.URI


//disabling because it's not fckin working
//@RunWith(SpringRunner::class)
//@SpringBootTest(classes = arrayOf(BillControllerTestConfig::class), webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BillControllerTests {

   @Autowired
   lateinit var restTemplate: TestRestTemplate

   @Configuration
   internal //@ImportResource(value = {"path/to/resource.xml"}) if you need to load additional xml configuration
   class TestConfig {
//      @Bean
//      fun mongoDatabase(): BillMongoDb {
//         return TestUtils.generateTestDb()
//      }
//      @Bean
//      fun restTemplateBuilder():RestTemplateBuilder {
//         return RestTemplateBuilder()
//               //.additionalMessageConverters(...)
//            //.customizers();
//      }
   }

//	@PostConstruct
//	fun setupTestDb(){
//
//	}

   @Value("\${local.server.port}")
   var port: Int = 0

   //@Test
   fun contextLoads() {
   }


   //@Test
   fun `CRa aaazY test name dawgie`() {

      //api/v1
      val uriStrings = listOf(
            "http://localhost:$port/bills",
            "http://localhost:$port/bills?congress=115",
            "http://localhost:$port/api/v1/bills",
            "http://localhost:$port/api/v1/bills?congress=115",
            "/api/v1/bills?congress=115",
            "/api/v1/bills",
            "/bills",
            "/bills?congress=115",
            "/bills&congress=115")
      for(uri in uriStrings) {
         val b1 = restTemplate.getForObject(uri, String::class.java)
         val b2 = restTemplate.getForObject(uri, QueryResultImpl::class.java)
         val b3 = restTemplate.getForObject(uri, BasicQueryResult::class.java)
         b3.meta
      }



      val uri2 = UriComponentsBuilder.fromUriString("http://localhost:$port/bills")
            //.queryParam("congress", "{congress}")
            //.buildAndExpand("115")
            //.encode()
            .build()
            .toUri()
      val uriV2 = UriComponentsBuilder.fromUriString("http://localhost:$port/api/v1/bills")
            //.queryParam("congress", "{congress}")
            //.buildAndExpand("115")
            //.encode()
            .build()
            .toUri()

      val uriV3 = UriComponentsBuilder.fromUriString("http://localhost:$port/api/v1/bills")
            .queryParam("congress", "115")
            //.buildAndExpand("115")
            //.encode()
            .build()
            .toUri()


      // When
      //val body = restTemplate.getForObject("/api/v1/bills?congress={name}", String::class.java, name)
      //val body = //restTemplate.getForObject(uri2, QueryResultImpl::class.java, mapOf(Pair("congress", "115")))


      val allUri:List<URI> = listOf(uri2, uriV2, uriV3)
      for (uri in allUri){
         val b1 = restTemplate.getForObject(uri, String::class.java)
         val b2 = restTemplate.getForObject(uri, QueryResultImpl::class.java)
         val b3 = restTemplate.getForObject(uri, BasicQueryResult::class.java)
         b3.meta
      }


      //assertThat(body).isEqualTo("")
      // Then
      //assertThat(body).isEqualTo("Hello, $name")

   }

   @After
   fun teardown(){
      //TODO figure out how to delete databse when finished
   }
}
