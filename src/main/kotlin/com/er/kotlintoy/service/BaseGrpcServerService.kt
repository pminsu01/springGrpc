package com.er.kotlintoy.service


import com.er.kotlintoy.document.BaseDocument
import com.er.kotlintoy.grpc.v1.BaseProtoServiceGrpc
import com.er.kotlintoy.grpc.v1.BaseProtoServiceGrpcKt
import com.er.kotlintoy.grpc.v1.BaseRequest
import com.er.kotlintoy.grpc.v1.BaseResponse
import com.er.kotlintoy.mapper.BaseMapper
import com.er.kotlintoy.repository.BaseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import net.devh.boot.grpc.server.service.GrpcService
import org.mapstruct.factory.Mappers


@GrpcService
class BaseGrpcServerService(var baseRepository: BaseRepository) : BaseProtoServiceGrpcKt.BaseProtoServiceCoroutineImplBase() {


    override suspend fun retrieveBaseOnDB(request: BaseRequest): BaseResponse {

        val converter = Mappers.getMapper(BaseMapper::class.java) // or PersonConverterImpl()
        val getBaseDocument = baseRepository.findByBaseId(request.baseId)
        var baseDTO = getBaseDocument.map(converter::toBaseDto)


        return BaseResponse.newBuilder()
            .setBaseId(withContext(Dispatchers.IO) {
                baseDTO.blockFirst()
            }?.baseId)
            .setBaseName(withContext(Dispatchers.IO) {
                baseDTO.blockFirst()
            }?.baseName)
            .setBaseNumber(withContext(Dispatchers.IO) {
                baseDTO.blockFirst()
            }?.baseNumber)
            .build()
    }
}




