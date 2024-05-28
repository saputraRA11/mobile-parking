package com.example.parking.data.model.ParkingHistory


data class BodyCreateHistory(
    val vehicle_type: String = "",
    val payment: String = "",
    val parking_lot_id: String = "",
    val easypark_id: String = "",
    val keeper_id: String = ""
)