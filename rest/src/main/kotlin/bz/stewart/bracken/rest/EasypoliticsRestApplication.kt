package bz.stewart.bracken.rest

import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class EasypoliticsRestApplication: CommandLineRunner {

    //@Autowired()
    //var repository:BillsRepository? = null

//   @Autowired
//   private val context: ApplicationContext? = null

    override fun run(vararg args: String?) {


        /*repository?.save(Bill(getNumber="1", bill_id = "hr1"))
        repository?.save(Bill(getNumber="2", bill_id = "hr2"))
        println(repository?.findAll()?.count())
        println(repository?.findAll())
        println("Found with findByNumber(1):")
        println(repository?.findByNumber("1"))*/

       //repository?.searchByNumber("100")
        println("YO PIZZA")
    }
}

fun main(args: Array<String>) {
    SpringApplication.run(EasypoliticsRestApplication::class.java, *args)
}
