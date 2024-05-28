package com.example.parking.data.remote.response.ParkingHistory

import com.google.gson.annotations.SerializedName

data class GetHistoryResponse(

	@field:SerializedName("data")
	val data: DataHistory? = null,

	@field:SerializedName("success")
	val success: Boolean? = null
)

data class DataHistory(

	@field:SerializedName("meta")
	val meta: Meta? = null,

	@field:SerializedName("parking_history")
	val parkingHistory: List<ParkingHistoryItem?>? = null
)

data class Query(

	@field:SerializedName("ticket_status")
	val ticketStatus: String? = null,

	@field:SerializedName("keeper_id")
	val keeperId: Any? = null,

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
	val easyparkId: String? = null
)

data class ParkingLot(

	@field:SerializedName("area_name")
	val areaName: String? = null,

	@field:SerializedName("address")
	val address: String? = null,

	@field:SerializedName("image_url")
	val imageUrl: String? = null
)

data class Meta(

	@field:SerializedName("query")
	val query: Query? = null,

	@field:SerializedName("total_data")
	val totalData: Int? = null
)

data class ParkingHistoryItem(

	@field:SerializedName("ticket_status")
	val ticketStatus: String? = null,

	@field:SerializedName("amount")
	val amount: Int? = null,

	@field:SerializedName("updated_at")
	val updatedAt: Any? = null,

	@field:SerializedName("total_amount")
	val totalAmount: Int? = null,

	@field:SerializedName("vehicle_type")
	val vehicleType: String? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("payment")
	val payment: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("parking_lot")
	val parkingLot: ParkingLot? = null
)
