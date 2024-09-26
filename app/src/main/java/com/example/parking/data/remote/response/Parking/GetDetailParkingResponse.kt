package com.example.parking.data.remote.response.Parking

import com.google.gson.annotations.SerializedName

data class GetDetailParkingResponse(

	@field:SerializedName("data")
	val data: DataDetailParking? = null,

	@field:SerializedName("success")
	val success: Boolean? = null
)

data class KeepersItem(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: String? = null
)

data class DataDetailParking(

	@field:SerializedName("area_name")
	val areaName: String? = null,

	@field:SerializedName("address")
	val address: String? = null,

	@field:SerializedName("updated_at")
	val updatedAt: Any? = null,

	@field:SerializedName("image_url")
	val imageUrl: String? = null,

	@field:SerializedName("owner_id")
	val ownerId: String? = null,

	@field:SerializedName("keepers")
	val keepers: List<KeepersItem?>? = null,

	@field:SerializedName("motor_cost")
	val motorCost: Any? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("car_cost")
	val carCost: Any? = null,

	@field:SerializedName("id")
	val id: String? = null
)
