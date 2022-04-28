package com.er.kotlintoy.document

import lombok.AccessLevel
import lombok.Getter
import lombok.NoArgsConstructor
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Getter
@Document(value = "testCollection")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
class BaseDocument {

    @Id
    var id: Object? = null
    var baseId: String? = null
    var baseName: String? = null
    var baseNumber: String? = null
}