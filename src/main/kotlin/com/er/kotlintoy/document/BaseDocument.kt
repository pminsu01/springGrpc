package com.er.kotlintoy.document

import lombok.*
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Getter
@ToString
@Document(value = "testCollection")
@NoArgsConstructor (access = AccessLevel.PROTECTED)
class BaseDocument {

    @Id
    private var id: Object? = null
    private var baseId: String? = null
    private var baseName: String? = null
    private var baseNumber: String? = null
}