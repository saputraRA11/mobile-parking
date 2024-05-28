package com.example.parking.ui.screen.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.parking.data.model.Auth.BodyRegister
import com.example.parking.data.model.WA.BodySendOtp
import com.example.parking.data.preferences.SettingLocalStorage
import com.example.parking.data.remote.response.WA.OtpResponse
import com.example.parking.data.remote.response.Auth.RegisterResponse
import com.example.parking.data.repository.UserRepository
import com.example.parking.ui.common.UiState
import com.example.parking.ui.utils.convertRole
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class RegisterViewModel(
    private  val userRepository: UserRepository,
    private val localStorage: SettingLocalStorage
): ViewModel() {
    private val _uiStateRegister: MutableStateFlow<UiState<RegisterResponse>> = MutableStateFlow(
        UiState.Loading)
    val uiStateRegister: StateFlow<UiState<RegisterResponse>>
        get() = _uiStateRegister

    private val _uiStateOtp: MutableStateFlow<UiState<OtpResponse>> = MutableStateFlow(
        UiState.Loading)
    val uiStateOtp: StateFlow<UiState<OtpResponse>>
        get() = _uiStateOtp

    fun registerUsers(formRegister: FormRegister) {
        viewModelScope.launch {
            val bodyRegister: BodyRegister = BodyRegister(
                role = convertRole(formRegister.role.value.text),
                phone_number = "62${formRegister.phone_number.value.text}",
                name = formRegister.name.value.text,
                nik = formRegister.nik.value.text
            )
            try {
                userRepository.registerUser(bodyRegister).catch {
                    _uiStateRegister.value = UiState.Error(it.message.toString())
                }. collect{
                        registerResponse ->
                    _uiStateRegister.value = UiState.Success(registerResponse)
                }
            } catch (e:Exception) {
                _uiStateRegister.value = UiState.Error(e.message.toString())
            }
        }
    }

    fun sendOtp(phone:String) {
        viewModelScope.launch {
            try {
                userRepository.sendOtp(BodySendOtp(phone)).catch {
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

    suspend fun savePhone(phone: String){
        localStorage.saveSetting("otp",phone)
    }

    fun resetUiStateRegister() {
        _uiStateRegister.value = UiState.Loading
    }

    fun resetUiStateOtp() {
        _uiStateOtp.value = UiState.Loading
    }
}