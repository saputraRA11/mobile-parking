package com.example.parking.data.remote.response.ParkingHistory

import com.google.gson.annotations.SerializedName

data class CreateHistoryResponse(

	@field:SerializedName("data")
	val data: DataCreateHistory? = null,

	@field:SerializedName("success")
	val success: Boolean? = null
)

data class TransactionHistory(

	@field:SerializedName("transaction_id")
	val transactionId: Any? = null,

	@field:SerializedName("status_message")
	val statusMessage: Any? = null,

	@field:SerializedName("fraud_status")
	val fraudStatus: Any? = null,

	@field:SerializedName("transaction_status")
	val transactionStatus: Any? = null,

	@field:SerializedName("status_code")
	val statusCode: Any? = null,

	@field:SerializedName("signature_key")
	val signatureKey: Any? = null,

	@field:SerializedName("merchant_id")
	val merchantId: Any? = null,

	@field:SerializedName("gross_amount")
	val grossAmount: Any? = null,

	@field:SerializedName("settlement_time")
	val settlementTime: Any? = null,

	@field:SerializedName("payment_type")
	val paymentType: Any? = null,

	@field:SerializedName("transaction_time")
	val transactionTime: Any? = null,

	@field:SerializedName("currency")
	val currency: Any? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("order_id")
	val orderId: Any? = null
)

data class DataCreateHistory(

	@field:SerializedName("transaction_history")
	val transactionHistory: TransactionHistory? = null,

	@field:SerializedName("parking_history")
	val parkingHistory: ParkingHistoryCreate? = null
)

data class ParkingHistoryCreate(

	@field:SerializedName("ticket_status")
	val ticketStatus: String? = null,

	@field:SerializedName("transaction_id")
	val transactionId: String? = null,

	@field:SerializedName("keeper_id")
	val keeperId: String? = null,

	@field:SerializedName("amount")
	val amount: Any? = null,

	@field:SerializedName("updated_at")
	val updatedAt: Any? = null,

	@field:SerializedName("owner_id")
	val ownerId: String? = null,

	@field:SerializedName("vehicle_type")
	val vehicleType: String? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("payment")
	val payment: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("parking_lot_id")
	val parkingLotId: String? = null,

	@field:SerializedName("easypark_id")
	val easyparkId: String? = null
)
