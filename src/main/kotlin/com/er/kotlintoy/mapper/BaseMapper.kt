package com.er.kotlintoy.mapper

import com.er.kotlintoy.document.BaseDocument
import com.er.kotlintoy.dto.BaseDTO
import org.mapstruct.Mapper
import reactor.core.publisher.Flux

@Mapper
interface BaseMapper {
//    fun toDto(baseDocumentMono : Flux<BaseDocument>) : Flux<BaseDTO>
    fun toBaseDto(baseDocument : BaseDocument) : BaseDTO
}