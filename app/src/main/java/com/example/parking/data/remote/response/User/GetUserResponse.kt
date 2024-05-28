package com.example.parking.data.remote.response.User

import com.google.gson.annotations.SerializedName

data class GetUserResponse(

	@field:SerializedName("data")
	val data: DataGetUser? = null,

	@field:SerializedName("success")
	val success: Boolean? = null
)

data class Query(

	@field:SerializedName("take")
	val take: Int? = null,

	@field:SerializedName("belong_to_parking_lot_id")
	val belongToParkingLotId: String? = null,

	@field:SerializedName("skip")
	val skip: Int? = null
)

data class DataGetUser(

	@field:SerializedName("meta")
	val meta: Meta? = null,

	@field:SerializedName("user")
	val user: List<UserItem?>? = null
)

data class UserItem(

	@field:SerializedName("nik")
	val nik: String? = null,

	@field:SerializedName("role")
	val role: String? = null,

	@field:SerializedName("updated_at")
	val updatedAt: Any? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("phone_number")
	val phoneNumber: String? = null,

	@field:SerializedName("otp")
	val otp: Any? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("parking_lot_id")
	val parkingLotId: String? = null,

	@field:SerializedName("status")
	val status: String? = null
)

data class Meta(

	@field:SerializedName("query")
	val query: Query? = null,

	@field:SerializedName("total_data")
	val totalData: Int? = null
)
