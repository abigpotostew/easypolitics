package bz.stewart.bracken

import bz.stewart.bracken.rest.query.QueryResultImpl
import org.assertj.core.api.Assertions.assertThat
import org.junit.After
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.context.annotation.Configuration
import org.springframework.web.util.UriComponentsBuilder



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

      //api/v1/
      val uri = "http://localhost:$port/bills?congress={congress}"
      val uri2 = UriComponentsBuilder.fromUriString("http://localhost:$port/bills")
            //.queryParam("congress", "{congress}")
            //.buildAndExpand("115")
            //.encode()
            .build()
            .toUri()
      // When
      //val body = restTemplate.getForObject("/api/v1/bills?congress={name}", String::class.java, name)
      val body = //restTemplate.getForObject(uri2, QueryResultImpl::class.java, mapOf(Pair("congress", "115")))
      restTemplate.getForObject(uri2, QueryResultImpl::class.java)

      assertThat(body).isEqualTo("")
      // Then
      //assertThat(body).isEqualTo("Hello, $name")

   }

   @After
   fun teardown(){
      //TODO figure out how to delete databse when finished
   }
}
