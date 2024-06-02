package com.example.parking.data.remote.response.Payment

import com.google.gson.annotations.SerializedName

data class CreatePaymentResponse(

	@field:SerializedName("data")
	val data: DataCreatePayment? = null,

	@field:SerializedName("success")
	val success: Boolean? = null
)

data class DataCreatePayment(

	@field:SerializedName("parking_history")
	val parkingHistory: ParkingHistory? = null,

	@field:SerializedName("transaction")
	val transaction: Transaction? = null
)

data class Transaction(

	@field:SerializedName("status_message")
	val statusMessage: String? = null,

	@field:SerializedName("transaction_id")
	val transactionId: String? = null,

	@field:SerializedName("fraud_status")
	val fraudStatus: String? = null,

	@field:SerializedName("transaction_status")
	val transactionStatus: String? = null,

	@field:SerializedName("status_code")
	val statusCode: String? = null,

	@field:SerializedName("merchant_id")
	val merchantId: String? = null,

	@field:SerializedName("gross_amount")
	val grossAmount: String? = null,

	@field:SerializedName("payment_type")
	val paymentType: String? = null,

	@field:SerializedName("transaction_time")
	val transactionTime: String? = null,

	@field:SerializedName("currency")
	val currency: String? = null,

	@field:SerializedName("expiry_time")
	val expiryTime: String? = null,

	@field:SerializedName("order_id")
	val orderId: String? = null,

	@field:SerializedName("actions")
	val actions: List<ActionsItem?>? = null
)

data class ActionsItem(

	@field:SerializedName("method")
	val method: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("url")
	val url: String? = null
)

data class ParkingHistory(

	@field:SerializedName("ticket_status")
	val ticketStatus: String? = null,

	@field:SerializedName("transaction_id")
	val transactionId: String? = null,

	@field:SerializedName("keeper_id")
	val keeperId: String? = null,

	@field:SerializedName("amount")
	val amount: Any? = null,

	@field:SerializedName("check_out_date")
	val checkOutDate: String? = null,

	@field:SerializedName("owner_id")
	val ownerId: String? = null,

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

	@field:SerializedName("parking_lot_id")
	val parkingLotId: String? = null,

	@field:SerializedName("easypark_id")
	val easyparkId: String? = null,

	@field:SerializedName("check_in_date")
	val checkInDate: String? = null
)
