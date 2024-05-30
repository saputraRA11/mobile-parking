package com.example.parking.ui.screen.form

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.parking.data.model.Parking.BodyAddParking
import com.example.parking.data.model.User.BodyCreateUser
import com.example.parking.data.preferences.SettingLocalStorage
import com.example.parking.data.remote.response.Parking.AddParkingResponse
import com.example.parking.data.remote.response.Parking.GetParkingOwnerResponse
import com.example.parking.data.remote.response.User.CreateUserResponse
import com.example.parking.data.repository.ParkingRepository
import com.example.parking.data.repository.UserRepository
import com.example.parking.ui.common.UiState
import com.example.parking.ui.utils.convertRole
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class FormViewModel(
    private  val parkingRepository: ParkingRepository,
    private val localStorage: SettingLocalStorage,
    private val userRepository: UserRepository,
): ViewModel() {
    private val _uiStateUser: MutableStateFlow<UiState<String>> = MutableStateFlow(UiState.Loading)
    val uiStateUser: StateFlow<UiState<String>>
        get() = _uiStateUser


    private val _uiStateAddArea: MutableStateFlow<UiState<AddParkingResponse>> = MutableStateFlow(
        UiState.Loading)
    val uiStateAddArea: StateFlow<UiState<AddParkingResponse>>
        get() = _uiStateAddArea

    private val _uiStateAddGuard: MutableStateFlow<UiState<CreateUserResponse>> = MutableStateFlow(
        UiState.Loading)
    val uiStateAddGuard: StateFlow<UiState<CreateUserResponse>>
        get() = _uiStateAddGuard

    private val _uiStateAreaOwner: MutableStateFlow<UiState<GetParkingOwnerResponse>> = MutableStateFlow(UiState.Loading)
    val uiStateAreaOwner: StateFlow<UiState<GetParkingOwnerResponse>>
        get() = _uiStateAreaOwner
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
                    _uiStateAddArea.value = UiState.Error(it.message.toString())
                }. collect{
                    parkingResponse ->
                    _uiStateAddArea.value = UiState.Success(parkingResponse)
                }
            } catch (e:Exception) {
                _uiStateAddArea.value = UiState.Error(e.message.toString())
            }
        }
    }

    fun addGuard(formGuard: AddGuardForm) {
        viewModelScope.launch {
            try {
                val bodyAddGuard: BodyCreateUser = BodyCreateUser(
                    nik = formGuard.nik.value.text,
                    name = formGuard.guardName.value.text,
                    phone_number = "62${formGuard.guardPhone.value.text}",
                    role = convertRole("Penjaga"),
                    status = "NotActive",
                    belong_to_parking_lot_id = formGuard.selectedArea.value
                )
                userRepository.createUser(bodyAddGuard).catch {
                    _uiStateAddGuard.value = UiState.Error(it.message.toString())
                }. collect{
                        userResponse ->
                    _uiStateAddGuard.value = UiState.Success(userResponse)
                }
            } catch (e:Exception) {
                _uiStateAddGuard.value = UiState.Error(e.message.toString())
            }
        }
    }

    fun getAreaByOwner(id: String) {
        viewModelScope.launch {
            try {
                parkingRepository.getParkingByOwner(id).catch {
                    _uiStateAreaOwner.value = UiState.Error(it.message.toString())
                }.collect {
                        data ->
                    _uiStateAreaOwner.value = UiState.Success(data)
                }
            } catch (e:Exception) {
                _uiStateUser.value = UiState.Error(e.message.toString())
            }
        }
    }

    fun resetUiStateUser() {
        _uiStateUser.value = UiState.Loading
    }
    fun resetUiStateAddParking() {
        _uiStateAddArea.value = UiState.Loading
    }

    fun resetUiStateAddGuard() {
        _uiStateAddGuard.value = UiState.Loading
    }

    fun resetUiStateAreaOwner() {
        _uiStateAreaOwner.value = UiState.Loading
    }
}