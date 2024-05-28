package com.example.parking.data.model.Parking

data class BodyUpdateParking(
    val area_name: String = "",
    val address: String = "",
    val car_cost: Int = 0,
    val motor_cost: Int = 0,
    val owner_id: String = ""
)