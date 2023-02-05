package com.er.kotlingrpc.service


import com.er.kotlingrpc.mapper.BaseMapper
import com.er.kotlingrpc.repository.BaseRepository
import com.er.kotlintoy.grpc.v1.BaseProtoServiceGrpcKt
import com.er.kotlintoy.grpc.v1.BaseRequest
import com.er.kotlintoy.grpc.v1.BaseResponse
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

typealias dsv = HashMap<Int,Int>




