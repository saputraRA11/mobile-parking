package com.example.parking.data.remote.response.WA

import com.google.gson.annotations.SerializedName

data class OtpResponse(

    @field:SerializedName("data")
	val data: DataOtp? = null,

    @field:SerializedName("success")
	val success: Boolean? = null
)

data class DataOtp(

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("expires_in")
	val expiresIn: String? = null
)
