package com.er.kotlingrpc.mapper

import com.er.kotlingrpc.document.BaseDocument
import com.er.kotlingrpc.dto.BaseDTO
import com.er.kotlintoy.grpc.v1.BaseResponse
import org.mapstruct.CollectionMappingStrategy
import org.mapstruct.Mapper

@Mapper(collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED)
interface BaseMapper {
    fun toBaseDto(baseDocument : BaseDocument) : BaseDTO
    fun toBaseGrpcStream(baseDTO: BaseDTO) : BaseResponse
}