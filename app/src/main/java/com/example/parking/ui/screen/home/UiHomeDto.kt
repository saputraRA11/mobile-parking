package com.example.parking.ui.screen.home

data class Area(
    val id: String = "",
    val name: String = "",
    val guardCount: Int = 0
)

data class UpdateParkingHistoryDto(
    val idTransaction:String = "",
    val isConfirm:Boolean = false,
    val areaName: String = "",
    val isActive:Boolean = false,
    val status:String = "Tidak Aktif",
    val paymentType:String = "Cash",
    val location:String = ""
)

data class CreateParkingHistoryDto(
    val vehicleType:String = "Motor",
    val paymentType:String = "Cash",
    val parking_lot_id:String = "",
    val easypark_id:String = "",
    val keeper_id:String = ""
)