package com.example.parking.ui.screen.home

import androidx.lifecycle.ViewModel
import com.example.parking.data.repository.UserRepository
import com.example.parking.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import androidx.lifecycle.viewModelScope
import com.example.parking.data.preferences.SettingLocalStorage
import com.example.parking.data.remote.response.Parking.GetParkingOwnerResponse
import com.example.parking.data.repository.ParkingRepository
import kotlinx.coroutines.launch

class HomeViewModel(
    private  val userRepository: UserRepository,
    private val localStorage: SettingLocalStorage,
    private val parkingRepostiory: ParkingRepository,
): ViewModel() {
    private val _uiStateUser: MutableStateFlow<UiState<String>> = MutableStateFlow(UiState.Loading)
    val uiStateUser: StateFlow<UiState<String>>
        get() = _uiStateUser
    private val _uiStateAreaOwner: MutableStateFlow<UiState<GetParkingOwnerResponse>> = MutableStateFlow(UiState.Loading)
    val uiStateaAreaOwner: StateFlow<UiState<GetParkingOwnerResponse>>
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

    fun resetUiStateUser() {
        _uiStateUser.value = UiState.Loading
    }

    fun resetUiStateAreaByOwner() {
        _uiStateAreaOwner.value = UiState.Loading
    }
}