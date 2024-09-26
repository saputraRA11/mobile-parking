package com.example.parking.ui.screen.payment

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.parking.data.remote.response.Parking.KeepersItem
import com.example.parking.data.remote.response.ParkingHistory.DataItemMonthly
import com.example.parking.ui.screen.form.GuardIdentity
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

data class Area(
    val id: String = "",
    val name: String = "",
    val guardCount: Int = 0
)

data class EasyparkHistory(
    val areaName: String = "",
    val checkIn: String = "",
    val checkOut: String = "",
    val payment: String = "",
    val amount: String = "",
)

data class ManagamenetHistoryDto(
    val totalIncome: MutableState<Int> = mutableStateOf(0),
    val totalUser: MutableState<Int> = mutableStateOf(0),
    val months: MutableState<MutableList<String>> = mutableStateOf(mutableListOf()),
    val totals:MutableState<MutableList<Int>> = mutableStateOf(mutableListOf())
)

data class KeeperOngoingTransaction(
    val id: String = "",
    val checkIn: String = "",
    val name: String = "",
    val payment: String = "",
)

@Serializable
data class HistoryPaymentDto(
    @field:SerializedName("parkingId")
    val parkingHistoryId:String,

    @field:SerializedName("totalAmount")
    val totalAmount: String,

    @field:SerializedName("historyUrl")
    val historyUrl:String
)

fun transformMonths(data: List<DataItemMonthly?>?): MutableState<MutableList<String>> {
    val newList: MutableState<MutableList<String>> = mutableStateOf(mutableListOf())

    data?.map {
        it?.month?.let { month -> newList.value.add(month) }
    }

    return newList
}

fun transformPrice(data: List<DataItemMonthly?>?): MutableState<MutableList<Int>> {
    val newList: MutableState<MutableList<Int>> = mutableStateOf(mutableListOf())

    data?.map {
        it?.totalHistory?.let { price -> newList.value.add(price) }
    }

    return newList
}