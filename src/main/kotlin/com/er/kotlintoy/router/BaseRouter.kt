package com.er.kotlintoy.router

import com.er.kotlintoy.handler.BaseHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.router


@Configuration
class BaseRouter(private val handler: BaseHandler) {

    @Bean
    fun routerFunction() =
        router {
            "v1".nest {
                listOf( // API EndPoint
                    GET("/test", handler::get),
                    GET("/retrieveAll", handler::getAll),
                    GET("/retrieve/{baseId}", handler::findByBaseId)
                )
            }
        }
}


// grpcurl -plaintext -d '{"baseId":"erer"}' com.er.kotlintoy.grpc.v1.BaseProtoService/retrieveBaseOnDB

