package com.example.parking.data.remote.response.Auth

import com.google.gson.annotations.SerializedName

data class RegisterResponse(

	@field:SerializedName("data")
	val data: DataRegisterResponse? = null,

	@field:SerializedName("success")
	val success: Boolean? = null,

	@field:SerializedName("message")
	val message: String? = null
)


data class DataRegisterResponse(

	@field:SerializedName("nik")
	val nik: String? = null,

	@field:SerializedName("role")
	val role: String? = null,

	@field:SerializedName("updated_at")
	val updatedAt: Any? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("created_at")
	val createdAt: Any? = null,

	@field:SerializedName("phone_number")
	val phoneNumber: String? = null,

	@field:SerializedName("otp")
	val otp: Any? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("status")
	val status: String? = null
)
