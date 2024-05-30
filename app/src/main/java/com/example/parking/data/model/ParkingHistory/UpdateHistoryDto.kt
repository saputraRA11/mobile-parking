package com.example.parking.data.model.ParkingHistory

data class BodyUpdateHistory(
    val vehicle_type: String? = null,
    val ticket_status: String? = null,
    val payment: String? = null,
    val parking_lot_id: String? = null,
    val easypark_id: String? = null,
    val keeper_id: String? = null
)