package com.example.parking.ui.screen.home

import androidx.lifecycle.ViewModel
import com.example.parking.data.repository.UserRepository
import com.example.parking.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import androidx.lifecycle.viewModelScope
import com.example.parking.data.model.ParkingHistory.BodyCreateHistory
import com.example.parking.data.model.ParkingHistory.BodyUpdateHistory
import com.example.parking.data.model.User.BodyCreateUser
import com.example.parking.data.preferences.SettingLocalStorage
import com.example.parking.data.remote.response.Parking.GetParkingOwnerResponse
import com.example.parking.data.remote.response.Parking.GetParkingResponse
import com.example.parking.data.remote.response.ParkingHistory.ActiveHistoryResponse
import com.example.parking.data.remote.response.ParkingHistory.CreateHistoryResponse
import com.example.parking.data.remote.response.ParkingHistory.UpdateHistoryResponse
import com.example.parking.data.repository.ParkingHistoryRepository
import com.example.parking.data.repository.ParkingRepository
import com.example.parking.ui.utils.convertStatusV2
import kotlinx.coroutines.launch

class HomeViewModel(
    private  val userRepository: UserRepository,
    private val localStorage: SettingLocalStorage,
    private val parkingRepostiory: ParkingRepository,
    private val parkingHistoryRepository: ParkingHistoryRepository
): ViewModel() {
    private val _uiStateUser: MutableStateFlow<UiState<String>> = MutableStateFlow(UiState.Loading)
    val uiStateUser: StateFlow<UiState<String>>
        get() = _uiStateUser

    private val _uiStateAreaOwner: MutableStateFlow<UiState<GetParkingOwnerResponse>> = MutableStateFlow(UiState.Loading)
    val uiStateaAreaOwner: StateFlow<UiState<GetParkingOwnerResponse>>
        get() = _uiStateAreaOwner

    private val _uiStateDetailArea: MutableStateFlow<UiState<GetParkingResponse>> = MutableStateFlow(UiState.Loading)
    val uiStateDetailArea: StateFlow<UiState<GetParkingResponse>>
        get() = _uiStateDetailArea

    private val _uiStateSaveHistory: MutableStateFlow<UiState<CreateHistoryResponse>> = MutableStateFlow(UiState.Loading)
    val uiStateSaveHistory: StateFlow<UiState<CreateHistoryResponse>>
        get() = _uiStateSaveHistory

    private val _uiStateActiveHistoryUser: MutableStateFlow<UiState<ActiveHistoryResponse>> = MutableStateFlow(UiState.Loading)
    val uiStateActiveHistoryUser: StateFlow<UiState<ActiveHistoryResponse>>
        get() = _uiStateActiveHistoryUser

    private val _uiStateUpdateHistory: MutableStateFlow<UiState<UpdateHistoryResponse>> = MutableStateFlow(UiState.Loading)
    val uiStateUpdateHistory: StateFlow<UiState<UpdateHistoryResponse>>
        get() = _uiStateUpdateHistory
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

    fun getAreaByOwner(id: String) {
        viewModelScope.launch {
            try {
                parkingRepostiory.getParkingByOwner(id).catch {
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

    fun getAreaById(id: String) {
        viewModelScope.launch {
            try {
                parkingRepostiory.getDetailParking(id).catch {
                    _uiStateDetailArea.value = UiState.Error(it.message.toString())
                }.collect {
                        data ->
                    _uiStateDetailArea.value = UiState.Success(data)
                }
            } catch (e:Exception) {
                _uiStateDetailArea.value = UiState.Error(e.message.toString())
            }
        }
    }

    fun saveParkingHistory(bodyForm:CreateParkingHistoryDto) {
        viewModelScope.launch {
            val bodyCreate = BodyCreateHistory(
                parking_lot_id = bodyForm.parking_lot_id,
                easypark_id = bodyForm.easypark_id,
                keeper_id = bodyForm.keeper_id,
                vehicle_type = bodyForm.vehicleType,
                payment = bodyForm.paymentType
            )
            try {
                parkingHistoryRepository.createHistory(bodyCreate).catch {
                    _uiStateSaveHistory.value = UiState.Error(it.message.toString())
                }.collect {
                        data ->
                    _uiStateSaveHistory.value = UiState.Success(data)
                }
            } catch (e:Exception) {
                _uiStateSaveHistory.value = UiState.Error(e.message.toString())
            }
        }
    }

    fun updateParkingHistory(bodyForm:UpdateParkingHistoryDto) {
        viewModelScope.launch {
            val bodyUpdate = BodyUpdateHistory(
                payment = bodyForm.paymentType,
                ticket_status = bodyForm.status
            )
            try {
                parkingHistoryRepository.updateHistory(bodyForm.idTransaction,bodyUpdate).catch {
                    _uiStateUpdateHistory.value = UiState.Error(it.message.toString())
                }.collect {
                        data ->
                    _uiStateUpdateHistory.value = UiState.Success(data)
                }
            } catch (e:Exception) {
                _uiStateUpdateHistory.value = UiState.Error(e.message.toString())
            }
        }
    }

    fun detailHistoryByUser(id:String){
        viewModelScope.launch {
            try {
                parkingHistoryRepository.detailHistoryByUser(id).catch {
                    _uiStateActiveHistoryUser.value = UiState.Error(it.message.toString())
                }.collect {
                        data ->
                    _uiStateActiveHistoryUser.value = UiState.Success(data)
                }
            } catch (e:Exception) {
                _uiStateActiveHistoryUser.value = UiState.Error(e.message.toString())
            }
        }
    }

    fun resetUiStateUser() {
        _uiStateUser.value = UiState.Loading
    }

    fun resetUiStateAreaByOwner() {
        _uiStateAreaOwner.value = UiState.Loading
    }

    fun resetUiStateSaveHistory() {
        _uiStateSaveHistory.value = UiState.Loading
    }
    fun resetUiStateUpdateHistory() {
        _uiStateUpdateHistory.value = UiState.Loading
    }
    fun resetUiStateDetailArea() {
        _uiStateDetailArea.value = UiState.Loading
    }
    fun resetUiStateActiveHistoryUser() {
        _uiStateActiveHistoryUser.value = UiState.Loading
    }
}