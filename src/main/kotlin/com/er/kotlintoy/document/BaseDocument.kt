package com.er.kotlintoy.document

import lombok.AccessLevel
import lombok.Getter
import lombok.NoArgsConstructor
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(value = "testCollection")
class BaseDocument(

    @Id
    val id: Any?,
    val baseId: String,
    val baseName: String,
    var baseNumber: String = "sd",
    var baseAge: String

)