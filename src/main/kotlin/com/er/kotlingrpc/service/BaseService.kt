package com.er.kotlingrpc.service

import com.er.kotlingrpc.document.BaseDocument
import com.er.kotlingrpc.repository.BaseRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux

@Service
class BaseService(private val baseRepository: BaseRepository) {


    fun getAll(): Flux<BaseDocument> {
        // TODO Logic
        return baseRepository.findAll()
    }

    fun getDataByBaseId(baseId: String): Flux<BaseDocument> {
        // TODO Logic
        return baseRepository.findByBaseId(baseId)
    }
}

