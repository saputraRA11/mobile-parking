package com.example.parking.ui.screen.form

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.parking.data.model.Parking.BodyAddParking
import com.example.parking.data.preferences.SettingLocalStorage
import com.example.parking.data.remote.response.Parking.AddParkingResponse
import com.example.parking.data.repository.ParkingRepository
import com.example.parking.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class FormViewModel(
    private  val parkingRepository: ParkingRepository,
    private val localStorage: SettingLocalStorage
): ViewModel() {
    private val _uiStateUser: MutableStateFlow<UiState<String>> = MutableStateFlow(UiState.Loading)
    val uiStateUser: StateFlow<UiState<String>>
        get() = _uiStateUser


    private val _uiStateParking: MutableStateFlow<UiState<AddParkingResponse>> = MutableStateFlow(
        UiState.Loading)
    val uiStateParking: StateFlow<UiState<AddParkingResponse>>
        get() = _uiStateParking
    fun authenticationUser() {
        viewModelScope.launch {
            try {
                localStorage.getSetting("user").catch {
                    _uiStateUser.value = UiState.Error(it.message.toString())
                }.collect {
                        user ->
                    _uiStateUser.value = UiState.Success(user)
                }
            } catch (e:Exception) {
                _uiStateUser.value = UiState.Error(e.message.toString())
            }
        }
    }

    fun resetUiStateUser() {
        _uiStateUser.value = UiState.Loading
    }
    fun addParking(formArea: AddAreaForm) {
        viewModelScope.launch {
            try {
                val bodyParking: BodyAddParking = BodyAddParking(
                    area_name = formArea.namaArea.value.text,
                    address = formArea.jalanLengkap.value.text,
                    car_cost = formArea.biayaMobil.value.text.toInt(),
                    motor_cost = formArea.biayaMotor.value.text.toInt(),
                    owner_id = formArea.userId.value
                )
                parkingRepository.addParking(bodyParking).catch {
                    _uiStateParking.value = UiState.Error(it.message.toString())
                }. collect{
                    parkingResponse ->
                    _uiStateParking.value = UiState.Success(parkingResponse)
                }
            } catch (e:Exception) {
                _uiStateParking.value = UiState.Error(e.message.toString())
            }
        }
    }

    fun resetUiStateParking() {
        _uiStateParking.value = UiState.Loading
    }
}