package com.er.kotlintoy.service


import com.er.kotlintoy.grpc.v1.BaseProtoServiceGrpc
import com.er.kotlintoy.grpc.v1.BaseProtoServiceGrpcKt
import com.er.kotlintoy.grpc.v1.BaseRequest
import com.er.kotlintoy.grpc.v1.BaseResponse
import com.er.kotlintoy.repository.BaseRepository
import io.grpc.stub.StreamObserver
import net.devh.boot.grpc.server.service.GrpcService


@GrpcService
class BaseGrpcServerService() : BaseProtoServiceGrpc.BaseProtoServiceImplBase() {

     override fun retrieveBaseOnDB(request: BaseRequest, streamObserver: StreamObserver<BaseResponse>) {

       val response = BaseResponse.newBuilder()
            .setBaseId("baseId")
            .setBaseName("baseName")
            .setBaseNumber("BaseNumber")
            .build()

         streamObserver.onNext(response)
         streamObserver.onCompleted()
    }
}

