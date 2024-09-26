package com.example.parking.data.model.Parking

import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
data class BodyAddParking(
     val area_name: String = "",
     val address: String = "",
     val car_cost: Int = 0,
     val motor_cost: Int = 0,
     val owner_id: String = ""
)