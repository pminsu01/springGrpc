package com.er.kotlingrpc.dto


class BaseDTO(

    val id: Any?,
    val baseId: String,
    val baseName: String,
    var baseNumber: String,
    var baseStrList : List<String>,
    var carList: List<Car>,
) {

    class Car(
        var name: String,
        var madeYear: String,
    )

}