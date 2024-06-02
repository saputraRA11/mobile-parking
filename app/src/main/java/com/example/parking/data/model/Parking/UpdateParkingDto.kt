package com.example.parking.data.model.Parking

data class BodyUpdateParking(
    val address: String = "",
    val car_cost: Int = 0,
    val motor_cost: Int = 0,
    val park_keeper_ids:MutableList<String> = mutableListOf()
)