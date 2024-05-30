package com.example.parking.ui.utils

fun convertStatus(status:String):String{
    val newStatus = when(status){
        "Konfirmasi" ->  "Default"
        "Aktif" -> "Active"
        "NotActive" -> "NotActive"
        else -> "Default"
    }

    return newStatus
}
fun convertStatusV2(status:String):String{
    val newStatus = when(status){
        "Default" -> "Konfirmasi"
        "Active" -> "Aktif"
        "NotActive" ->  "NotActive"
        else -> "Default"
    }

    return newStatus
}