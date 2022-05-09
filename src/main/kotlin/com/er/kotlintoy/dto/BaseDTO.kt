package com.er.kotlintoy.dto

import lombok.Getter
import lombok.RequiredArgsConstructor
import lombok.Setter


@Getter
@Setter
@RequiredArgsConstructor
class BaseDTO (

    val baseId: String?,
    val baseName: String?,
    val baseNumber: String?
)