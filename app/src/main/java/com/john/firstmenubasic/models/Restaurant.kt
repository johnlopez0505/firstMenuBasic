package com.john.firstmenubasic.models

class Restaurant (
    var name: String,
    var city: String,
    var province: String,
    var phone: String,
    var image: String
){
    operator fun component1(): String = name
    operator fun component2(): String = city
    operator fun component3(): String = province
    operator fun component4(): String = phone
    operator fun component5(): String = image

    override fun toString(): String {
        return "Restaurant(name='$name', city='$city', province='$province', " +
                "phone='$phone', image='$image')"
    }
}