package com.example.parking.data.remote.response.Auth

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

data class ValidationResponse(

	@field:SerializedName("data")
	val data: DataValidation? = DataValidation(),

	@field:SerializedName("success")
	val success: Boolean? = false
)

@Serializable
data class TokenMeta(

	@field:SerializedName("expired_in")
	val expiredIn: String? = "",

	@field:SerializedName("token_type")
	val tokenType: String? = ""
)

@Serializable
data class User(

	@field:SerializedName("nik")
	val nik: String? = "",

	@field:SerializedName("role")
	val role: String? = "",

//	@field:SerializedName("updated_at")
//	val updatedAt: String = "",

	@field:SerializedName("phone")
	val phone: String? = "",

	@field:SerializedName("name")
	val name: String? = "",

	@field:SerializedName("parking_lot_id")
	val parkingId: String? = "",

//	@field:SerializedName("created_at")
//	val createdAt: String? = "",

//	@field:SerializedName("otp")
//	val otp: Int? = 0,

	@field:SerializedName("id")
	val id: String? = "none",

	@field:SerializedName("status")
	val status: String? = ""
)


@Serializable
data class DataValidation(

	@field:SerializedName("token_meta")
	val tokenMeta: TokenMeta? = TokenMeta(),

	@field:SerializedName("user")
	val user: User? = User(),

	@field:SerializedName("token")
	val token: String? = ""
)
