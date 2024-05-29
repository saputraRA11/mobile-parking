package com.example.parking.ui.screen.validation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.parking.data.model.WA.BodySendOtp
import com.example.parking.data.model.Auth.BodyValidation
import com.example.parking.data.preferences.SettingLocalStorage
import com.example.parking.data.remote.response.WA.OtpResponse
import com.example.parking.data.remote.response.Auth.ValidationResponse
import com.example.parking.data.repository.UserRepository
import com.example.parking.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class ValidationViewModel(
    private  val userRepository: UserRepository,
    private val localStorage: SettingLocalStorage

): ViewModel() {

    private val _uiStateValidation: MutableStateFlow<UiState<ValidationResponse>> = MutableStateFlow(
        UiState.Loading)
    val uiStateValidation: StateFlow<UiState<ValidationResponse>>
        get() = _uiStateValidation

    private val _uiStatePhone: MutableStateFlow<UiState<String>> = MutableStateFlow(
        UiState.Loading)
    val uiStatePhone: StateFlow<UiState<String>>
        get() = _uiStatePhone

    private val _uiStateOtp: MutableStateFlow<UiState<OtpResponse>> = MutableStateFlow(
        UiState.Loading)
    val uiStateOtp: StateFlow<UiState<OtpResponse>>
        get() = _uiStateOtp


    fun validationOtp(formOtp: BodyValidation, path:String = "login") {
        viewModelScope.launch {
            try {
                userRepository.validation(formOtp).catch {
                    _uiStateValidation.value = UiState.Error(it.message.toString())
                }. collect{
                        validationResponse ->
                    _uiStateValidation.value = UiState.Success(validationResponse)
                }
            } catch (e:Exception) {
                _uiStateValidation.value = UiState.Error(e.message.toString())
            }
        }
    }

    fun getPhone() {
        viewModelScope.launch {
            try {
               localStorage.getSetting("otp").catch {
                   _uiStateValidation.value = UiState.Error(it.message.toString())
               }.collect {
                   phone ->
                   _uiStatePhone.value = UiState.Success(phone)
               }
            } catch (e:Exception) {
                _uiStateValidation.value = UiState.Error(e.message.toString())
            }
        }
    }

    fun sendOtp(phone:String) {
        viewModelScope.launch {
            try {
                userRepository.sendOtp(BodySendOtp("62${phone}")).catch {
                    _uiStateOtp.value = UiState.Error(it.message.toString())
                }. collect{
                        otpResponse ->
                    _uiStateOtp.value = UiState.Success(otpResponse)
                }
            } catch (e:Exception) {
                _uiStateOtp.value = UiState.Error(e.message.toString())
            }
        }
    }

    suspend fun saveLocal(data: String){
        localStorage.saveSetting("user",data)
    }

    suspend fun clearLocal(){
        localStorage.clearSettings()
    }
    fun resetUiStateValidation() {
        _uiStateValidation.value = UiState.Loading
    }

    fun resetUiStatePhone() {
        _uiStatePhone.value = UiState.Loading
    }
}