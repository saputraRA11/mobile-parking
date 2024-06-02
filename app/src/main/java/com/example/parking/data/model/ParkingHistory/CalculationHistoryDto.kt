package com.example.parking.data.model.ParkingHistory

import retrofit2.http.Query

data class CalculationHistoryDto(
    @Query("owner_id")
    val ownerId:String?= null,

    @Query("keeper_id")
    val keeperId:String?= null,

    @Query("created_at_start_filter")
    val createdAtStartFilter:String,

    @Query("created_at_end_filter")
    val createdAtEndFilter:String,
)