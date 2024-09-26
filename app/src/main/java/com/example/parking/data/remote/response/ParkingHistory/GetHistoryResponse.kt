package com.example.parking.data.remote.response.ParkingHistory

import com.google.gson.annotations.SerializedName

data class GetHistoryResponse(

	@field:SerializedName("data")
	val data: Data? = null,

	@field:SerializedName("success")
	val success: Boolean? = null
)

data class Meta(

	@field:SerializedName("query")
	val query: Query? = null,

	@field:SerializedName("total_data")
	val totalData: Int? = null
)

data class ParkingLot(

	@field:SerializedName("area_name")
	val areaName: String? = null,

	@field:SerializedName("address")
	val address: String? = null,

	@field:SerializedName("image_url")
	val imageUrl: String? = null
)

data class EasyparkR(

	@field:SerializedName("name")
	val name: String? = null
)

data class Data(

	@field:SerializedName("meta")
	val meta: Meta? = null,

	@field:SerializedName("parking_history")
	val parkingHistory: List<ParkingHistoryItem?>? = null
)

data class Query(

	@field:SerializedName("ticket_status")
	val ticketStatus: String? = null,

	@field:SerializedName("keeper_id")
	val keeperId: String? = null,

	@field:SerializedName("take")
	val take: Int? = null,

	@field:SerializedName("payment_type")
	val paymentType: Any? = null,

	@field:SerializedName("created_at_end_filter")
	val createdAtEndFilter: String? = null,

	@field:SerializedName("owner_id")
	val ownerId: Any? = null,

	@field:SerializedName("created_at_start_filter")
	val createdAtStartFilter: String? = null,

	@field:SerializedName("skip")
	val skip: Int? = null,

	@field:SerializedName("easypark_id")
	val easyparkId: Any? = null
)

data class ParkingHistoryItem(

	@field:SerializedName("ticket_status")
	val ticketStatus: String? = null,

	@field:SerializedName("easypark")
	val easypark: EasyparkR? = null,

	@field:SerializedName("amount")
	val amount: Any? = null,

	@field:SerializedName("check_out_date")
	val checkOutDate: String? = null,

	@field:SerializedName("forecast_amount")
	val forecastAmount: Any? = null,

	@field:SerializedName("vehicle_type")
	val vehicleType: String? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("total_amount")
	val totalAmount: Any? = null,

	@field:SerializedName("payment")
	val payment: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("check_in_date")
	val checkInDate: String? = null,

	@field:SerializedName("parking_lot")
	val parkingLot: ParkingLot? = null
)
