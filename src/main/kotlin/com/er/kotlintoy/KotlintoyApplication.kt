package com.er.kotlintoy

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories

@SpringBootApplication
class KotlintoyApplication

fun main(args: Array<String>) {
    runApplication<KotlintoyApplication>(*args)
}
