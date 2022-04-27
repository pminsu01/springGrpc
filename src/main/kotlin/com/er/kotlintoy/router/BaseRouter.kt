package com.er.kotlintoy.router

import com.er.kotlintoy.handler.BaseHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.RequestPredicates.path
import org.springframework.web.reactive.function.server.RouterFunctions.nest
import org.springframework.web.reactive.function.server.router


@Configuration
class BaseRouter(private val handler: BaseHandler) {

    @Bean
    fun routerFunction() =
        router {
            "v1".nest {
                listOf( // API EndPoint
                    GET("/test", handler::get),
                    GET("/retrieves", handler::getAll),
                    GET("/retrieve/{baseId}", handler::findByBaseId)
                )
            }
        }
}