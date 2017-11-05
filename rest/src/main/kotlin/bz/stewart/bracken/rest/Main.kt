package bz.stewart.bracken.rest

import org.springframework.boot.SpringApplication

class Main {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(EasypoliticsRestApplication::class.java, *args)
        }
    }
}