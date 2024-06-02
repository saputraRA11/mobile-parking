package com.example.parking.data.remote.response.ParkingHistory

import com.google.gson.annotations.SerializedName

data class CalculationHistoryResponse(

	@field:SerializedName("data")
	val data: DataCalculation? = null,

	@field:SerializedName("success")
	val success: Boolean? = null
)

data class DataCalculation(

	@field:SerializedName("total_history")
	val totalHistory: Int? = null,

	@field:SerializedName("sum_all")
	val sumAll: Int? = 0
)
