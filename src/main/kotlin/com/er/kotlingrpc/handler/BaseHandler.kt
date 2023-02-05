package com.er.kotlingrpc.handler

import com.er.kotlingrpc.document.BaseDocument
import com.er.kotlingrpc.repository.BaseRepository
import com.er.kotlingrpc.service.BaseService
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.ServerResponse.ok
import org.springframework.web.reactive.function.server.body
import reactor.core.publisher.Mono

@Component
class BaseHandler(private val repo: BaseRepository, var baseService: BaseService) {

    // Router Handler 연결 테스트
    fun get(req: ServerRequest): Mono<ServerResponse> = ok()
        .contentType(MediaType.APPLICATION_JSON)
        .body<String>(Mono.just("테스트 중입니다.."))

    // MongoDB 전체 조회 Test
    fun getAll(req: ServerRequest): Mono<ServerResponse> =
        ok().contentType(MediaType.APPLICATION_JSON)
            .body<BaseDocument>(baseService.getAll())
            .switchIfEmpty(ServerResponse.notFound().build())

    // BaseId값에 따른 조회 Test
    fun findByBaseId(req: ServerRequest): Mono<ServerResponse> =
        ok().contentType(MediaType.APPLICATION_JSON)
            .body<BaseDocument>(baseService.getDataByBaseId(req.pathVariable("baseId")))
            .switchIfEmpty(ServerResponse.notFound().build())

}