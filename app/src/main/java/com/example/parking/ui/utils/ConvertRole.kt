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

fun convertRoleV2(role:String):String{
    val newRole = when(role){
        "ParkOwner" ->  "owner"
        "ParkKeeper" -> "guard"
        "Easypark" -> "user"
        else -> "Default"
    }

    return newRole
}