package com.example.parking.data.remote.response.ParkingHistory

import com.google.gson.annotations.SerializedName

data class ActiveHistoryResponse(

	@field:SerializedName("data")
	val data: DataResponse? = null,

	@field:SerializedName("success")
	val success: Boolean? = null
)

data class ParkingLotActiveHistory(

	@field:SerializedName("area_name")
	val areaName: String? = null,

	@field:SerializedName("address")
	val address: String? = null,

	@field:SerializedName("image_url")
	val imageUrl: String? = null
)

data class DataResponse(

	@field:SerializedName("ticket_status")
	val ticketStatus: String? = null,

	@field:SerializedName("amount")
	val amount: Any? = null,

	@field:SerializedName("check_out_date")
	val checkOutDate: Any? = null,

	@field:SerializedName("updated_at")
	val updatedAt: Any? = null,

	@field:SerializedName("total_amount")
	val totalAmount: Any? = null,

	@field:SerializedName("forecast_amount")
	val forecastAmount: Any? = null,

	@field:SerializedName("vehicle_type")
	val vehicleType: String? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("payment")
	val payment: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("check_in_date")
	val checkInDate: Any? = null,

	@field:SerializedName("parking_lot")
	val parkingLot: ParkingLotActiveHistory? = null
)
