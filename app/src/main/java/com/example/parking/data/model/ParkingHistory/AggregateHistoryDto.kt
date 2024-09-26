package com.example.parking.data.model.ParkingHistory

import retrofit2.http.Query

data class QueryAggregateHistory(
    @Query("take")
    val take:String?= "20",

    @Query("skip")
    val skip:String? = "0",

    @Query("created_at_start_filter")
    val created_at_start_filter:String? = null,

    @Query("created_at_end_filter")
    val created_at_end_filter:String? = null,

    @Query("easypark_id")
    val easypark_id:String? = null,

    @Query("owner_id")
    val owner_id:String? = null,

    @Query("keeper_id")
    val keeper_id:String? = null,

    @Query("ticket_status")
    val ticket_status:String? = null,

    @Query("payment_type")
    val payment_type:String? = null
)