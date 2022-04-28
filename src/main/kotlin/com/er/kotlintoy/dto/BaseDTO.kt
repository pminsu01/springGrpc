package com.er.kotlintoy.dto

import lombok.Getter
import lombok.RequiredArgsConstructor
import lombok.Setter


@Getter
@Setter
@RequiredArgsConstructor
class BaseDTO {

    val baseId: String? = null
    val baseName: String? = null
    val baseNumber: String? = null
}