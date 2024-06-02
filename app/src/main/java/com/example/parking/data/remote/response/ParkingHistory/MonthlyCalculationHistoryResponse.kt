package com.example.parking.data.remote.response.ParkingHistory

import com.google.gson.annotations.SerializedName

data class MonthlyCalculationHistoryResponse(

	@field:SerializedName("data")
	val data: List<DataItemMonthly?>? = null,

	@field:SerializedName("success")
	val success: Boolean? = null
)

data class DataItemMonthly(
	@field:SerializedName("total_history")
	val totalHistory: Int? = null,

	@field:SerializedName("month")
	val month: String? = null
)
