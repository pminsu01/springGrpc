package com.er.kotlintoy.router

import com.er.kotlintoy.handler.BaseHandler
import com.er.kotlintoy.service.BaseGrpcService
import com.linecorp.armeria.server.ServerBuilder
import com.linecorp.armeria.server.grpc.GrpcService
import com.linecorp.armeria.server.logging.AccessLogWriter
import com.linecorp.armeria.server.logging.LoggingService
import com.linecorp.armeria.spring.ArmeriaServerConfigurator
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.router


@Configuration
class BaseRouter(private val handler: BaseHandler, var baseGrpcService: BaseGrpcService) {

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

    // ArmeriaServer Configuration
    @Bean
    fun armeriaServerConfigurator(): ArmeriaServerConfigurator {

        return ArmeriaServerConfigurator { builder: ServerBuilder ->
            builder.decorator(LoggingService.newDecorator())
            builder.accessLogWriter(AccessLogWriter.combined(), false)
                .service(GrpcService.builder()
                    .addService(baseGrpcService)
                    .build())
        }
    }
}


// grpcurl -plaintext -d '{"message":"horiga"}' com.er.kotlintoy.armeria.grpc.v1.BaseProtoService/retrieveBaseOnDB

