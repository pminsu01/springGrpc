package com.er.kotlintoy.service


import com.er.kotlintoy.armeria.grpc.v1.BaseProtoServiceGrpcKt
import com.er.kotlintoy.armeria.grpc.v1.BaseRequest
import com.er.kotlintoy.armeria.grpc.v1.BaseResonse
import com.er.kotlintoy.repository.BaseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.springframework.stereotype.Service


@Service
class BaseGrpcService(var baseRepository: BaseRepository) : BaseProtoServiceGrpcKt.BaseProtoServiceCoroutineImplBase() {

    override suspend fun retrieveBaseOnDB(request: BaseRequest): BaseResonse {

        var baseId: String? = null
        var baseName: String? = null
        var baseNumber: String? = null

        withContext(Dispatchers.IO) {
            baseRepository.findByBaseId(request.baseId).toStream()
        }.map { o -> {
                baseId = o.baseId
                baseName = o.baseName
                baseNumber = o.baseNumber
            }
        }

        return BaseResonse.newBuilder().setBaseId(baseId)
            .setBaseName(baseName)
            .setBaseNumber(baseNumber)
            .build()
    }
}

