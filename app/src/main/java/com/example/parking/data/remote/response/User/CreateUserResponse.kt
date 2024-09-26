package com.example.parking.data.remote.response.User

import com.google.gson.annotations.SerializedName

data class CreateUserResponse(

	@field:SerializedName("data")
	val data: DataCreateUser? = null,

	@field:SerializedName("success")
	val success: Boolean? = null
)

data class DataCreateUser(

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
	val parkingLotId: Any? = null,

	@field:SerializedName("status")
	val status: String? = null
)
