package com.er.kotlingrpc.repository

import com.er.kotlingrpc.document.BaseDocument
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux

@Repository
interface BaseRepository : ReactiveMongoRepository<BaseDocument, String> {
    fun findByBaseId(baseId: String): Flux<BaseDocument>
}