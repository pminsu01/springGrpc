package com.er.kotlingrpc.document

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(value = "testCollection")
class BaseDocument(

    @Id
    val id: Long?,
    val baseId: String,
    val baseName: String,
    var baseNumber: String

)