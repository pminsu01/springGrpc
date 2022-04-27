package com.er.kotlintoy.dto

import lombok.Data
import lombok.Getter
import lombok.Setter


@Getter
@Setter
class BaseDTO {

    private val baseId: String? = null
    private val baseName: String? = null
    private val baseNumber: String? = null
}