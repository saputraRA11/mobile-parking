package com.example.parking.data.model.ParkingHistory

import retrofit2.http.Query

data class MonthlyQueryHistoryDto(
    @Query("owner_id")
    val ownerId:String?= "",
)