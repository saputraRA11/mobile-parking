package com.example.parking.data.remote.response.Parking

import com.google.gson.annotations.SerializedName

data class UpdateParkingResponse(

	@field:SerializedName("data")
	val data: DataUpdateParking? = null,

	@field:SerializedName("success")
	val success: Boolean? = null
)

data class DataUpdateParking(

	@field:SerializedName("area_name")
	val areaName: String? = null,

	@field:SerializedName("address")
	val address: String? = null,

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("image_url")
	val imageUrl: String? = null,

	@field:SerializedName("owner_id")
	val ownerId: String? = null,

	@field:SerializedName("motor_cost")
	val motorCost: Int? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("car_cost")
	val carCost: Int? = null,

	@field:SerializedName("id")
	val id: String? = null
)
