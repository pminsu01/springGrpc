package com.er.kotlintoy.service

import com.er.kotlintoy.document.BaseDocument
import com.er.kotlintoy.repository.BaseRepository
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux

@Service
@RequiredArgsConstructor
class BaseService(private val baseRepository: BaseRepository) {

    fun getAll(): Flux<BaseDocument> {
        return baseRepository.findAll()
    }
}

