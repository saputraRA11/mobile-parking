package com.example.parking.data.model.User

data class BodyCreateUser(
    val phone_number:String= "",
    val name:String = "",
    val nik:String = "",
    val role:String = "",
    val status:String = "",
    val belong_to_parking_lot_id:String = ""
)