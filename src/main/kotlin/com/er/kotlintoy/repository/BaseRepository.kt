package com.er.kotlintoy.repository

import com.er.kotlintoy.document.BaseDocument
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux

@Repository
interface BaseRepository : ReactiveMongoRepository<BaseDocument, String> {
    fun findByBaseId(baseId: String): Flux<BaseDocument>
}