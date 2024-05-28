package com.example.parking.ui.utils


fun convertRole(role:String):String{
    val newRole = when(role){
        "Pengelola" ->  "ParkOwner"
        "Penjaga" -> "ParkKeeper"
        "Pengguna" -> "Easypark"
        else -> "Default"
    }

    return newRole
}