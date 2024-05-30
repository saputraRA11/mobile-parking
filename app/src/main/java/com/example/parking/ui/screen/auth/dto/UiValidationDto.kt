package com.example.parking.ui.screen.auth.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OtpData(
    val phone:String,
    val path:String
)