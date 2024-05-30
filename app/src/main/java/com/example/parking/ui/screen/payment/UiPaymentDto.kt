package com.example.parking.ui.screen.payment

data class Area(
    val id: String = "",
    val name: String = "",
    val guardCount: Int = 0
)

data class EasyparkHistory(
    val areaName: String = "",
    val checkIn: String = "",
    val checkOut: String = "",
    val payment: String = "",
    val amount: String = "",
)

data class KeeperOngoingTransaction(
    val id: String = "",
    val checkIn: String = "",
    val name: String = "",
    val payment: String = "",
)