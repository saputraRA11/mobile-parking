package com.example.parking.data.model.User

import retrofit2.http.Query

data class QueryGetUser(
    @Query("take")
    val take:String?= "20",
    @Query("skip")
    val skip:String? = "0",
    @Query("belong_to_parking_lot_id")
    val belong_to_parking_lot_id:String? = null,

    @Query("owner_id")
    val owner_id:String? = null,

    @Query("already_assigned")
    val already_assigned:String? = null
)