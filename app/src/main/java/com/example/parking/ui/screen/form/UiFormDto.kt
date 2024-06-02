package com.example.parking.ui.screen.form

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.TextFieldValue
import com.example.parking.data.remote.response.Parking.KeepersItem
import com.example.parking.data.remote.response.User.UserItem
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

data class AddAreaForm(
    val namaArea : MutableState<TextFieldValue> =  mutableStateOf(TextFieldValue()),
    val jalanLengkap : MutableState<TextFieldValue> = mutableStateOf(TextFieldValue()),
    val biayaMobil : MutableState<TextFieldValue> = mutableStateOf(TextFieldValue()),
    val biayaMotor : MutableState<TextFieldValue> = mutableStateOf(TextFieldValue()),
    val userId:MutableState<String> = mutableStateOf("")
)
data class AddGuardForm(
    val guardPhone : MutableState<TextFieldValue> = mutableStateOf(TextFieldValue()),
    val guardName : MutableState<TextFieldValue> = mutableStateOf(TextFieldValue()),
    val nik : MutableState<TextFieldValue> = mutableStateOf(TextFieldValue()),
    val selectedArea : MutableState<String> = mutableStateOf(""),
)

data class AssignGuard(
    val index:MutableState<Int> = mutableStateOf(0),
    val id:MutableState<String> = mutableStateOf(""),
    val name:MutableState<String> = mutableStateOf(""),
    val isAssign:MutableState<Boolean>  = mutableStateOf(false),
)

@Serializable
data class UpdateAreaDto(
    @field:SerializedName("address")
    val address:String = "",

    @field:SerializedName("carPrice")
    val carPrice:String = "",

    @field:SerializedName("motorPrice")
    val motorPrice:String = "",

    @field:SerializedName("listGuard")
    val listGuard:List<GuardIdentity> = mutableListOf(),

    @field:SerializedName("id")
    val id:String = "",
)

@Serializable
data class GuardIdentity(
    @field:SerializedName("id")
    val id:String = "",

    @field:SerializedName("name")
    val name:String = "",
)

data class UpdateAreaFormDto(
    val address: MutableState<TextFieldValue> = mutableStateOf(TextFieldValue("")),

    val carPrice:MutableState<TextFieldValue> = mutableStateOf(TextFieldValue("")),

    val motorPrice:MutableState<TextFieldValue> = mutableStateOf(TextFieldValue("")),

    val listGuard:MutableState<MutableList<GuardIdentity>> = mutableStateOf(mutableListOf()),

    val id:MutableState<String> = mutableStateOf(""),

    val isAddess:MutableState<Boolean> = mutableStateOf(false),
    val isCar:MutableState<Boolean> = mutableStateOf(false),
    val isMotor:MutableState<Boolean> = mutableStateOf(false)

)

fun transformUpdateAreaDto(data:UpdateAreaFormDto):UpdateAreaDto{
    return UpdateAreaDto(
        listGuard = data.listGuard.value,
        carPrice = data.carPrice.value.text,
        motorPrice = data.motorPrice.value.text,
        address = data.address.value.text,
        id = data.id.value
    )
}
fun deleteItemKeeper(listGuard:MutableState<MutableList<GuardIdentity>>,index:Int): MutableState<MutableList<GuardIdentity>> {
    val newListGuard:MutableState<MutableList<GuardIdentity>> = mutableStateOf(mutableListOf())
    listGuard.value.forEachIndexed{
        id,item ->
        if(index != id) newListGuard.value.add(item)
    }

    return newListGuard
}
fun transformKeeperItem(data:List<KeepersItem?>?): MutableState<MutableList<GuardIdentity>> {
    val listGuard:MutableState<MutableList<GuardIdentity>> = mutableStateOf(mutableListOf())
    data?.map {
        listGuard.value.add(
            GuardIdentity(id = it?.id.toString(), name = it?.name.toString())
        )
    }

    return listGuard
}

fun transformUserKeeperStranger(data:List<UserItem?>?): MutableState<MutableList<AssignGuard>> {
    val listGuardStranger = mutableStateOf(mutableListOf(AssignGuard()))
    data?.forEachIndexed() {
        index,it ->
        listGuardStranger.value.add(
            AssignGuard(
                name = mutableStateOf(it?.name.toString()),
                id = mutableStateOf(it?.id.toString()),
                isAssign = mutableStateOf(false),
                index = mutableStateOf(index)
            )
        )
    }

    return listGuardStranger
}

fun clearForm():UpdateAreaFormDto{

    return UpdateAreaFormDto(
        listGuard = mutableStateOf(mutableListOf()) ,
        motorPrice = mutableStateOf(TextFieldValue("")),
        carPrice =  mutableStateOf(TextFieldValue("")),
        address = mutableStateOf(TextFieldValue("")),
        id = mutableStateOf("")
    )
}



data class Area(
    val id: String = "",
    val name: String = "",
    val guardCount: Int = 0
)