package com.example.parking.ui.screen.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.parking.data.model.Auth.BodyRegister
import com.example.parking.data.model.Auth.BodyValidation
import com.example.parking.data.model.WA.BodySendOtp
import com.example.parking.data.preferences.SettingLocalStorage
import com.example.parking.data.remote.response.Auth.RegisterResponse
import com.example.parking.data.remote.response.Auth.ValidationResponse
import com.example.parking.data.remote.response.WA.OtpResponse
import com.example.parking.data.repository.UserRepository
import com.example.parking.ui.common.UiState
import com.example.parking.ui.screen.auth.dto.FormRegister
import com.example.parking.ui.utils.convertRole
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class AuthViewModel(
    private  val userRepository: UserRepository,
    private val localStorage: SettingLocalStorage
): ViewModel() {
    private val _uiStateOtp: MutableStateFlow<UiState<OtpResponse>> = MutableStateFlow(
        UiState.Loading)

    private val _uiStateUser: MutableStateFlow<UiState<String>> = MutableStateFlow(UiState.Loading)
    val uiStateUser: StateFlow<UiState<String>>
        get() = _uiStateUser

    val uiStateOtp: StateFlow<UiState<OtpResponse>>
        get() = _uiStateOtp

    private val _uiStateRegister: MutableStateFlow<UiState<RegisterResponse>> = MutableStateFlow(
        UiState.Loading)
    val uiStateRegister: StateFlow<UiState<RegisterResponse>>
        get() = _uiStateRegister

    private val _uiStateValidation: MutableStateFlow<UiState<ValidationResponse>> = MutableStateFlow(
        UiState.Loading)
    val uiStateValidation: StateFlow<UiState<ValidationResponse>>
        get() = _uiStateValidation

    private val _uiStatePhone: MutableStateFlow<UiState<String>> = MutableStateFlow(
        UiState.Loading)
    val uiStatePhone: StateFlow<UiState<String>>
        get() = _uiStatePhone

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
    fun sendOtp(phone:String,isLogin:Boolean = false) {
        viewModelScope.launch {
            try {
                userRepository.sendOtp(BodySendOtp(if(isLogin) "62${phone}" else phone)).catch {
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

    fun validationOtp(formOtp: BodyValidation) {
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
    suspend fun savePhone(phone: String,isLogin: Boolean = false){
        localStorage.saveSetting("otp", (if(isLogin) "62${phone}" else phone))
    }
    suspend fun saveUser(data: String){
        localStorage.saveSetting("user",data)
    }
    fun resetUiStateOtp() {
        _uiStateOtp.value = UiState.Loading
    }
    fun resetUiStateRegister() {
        _uiStateRegister.value = UiState.Loading
    }
    fun resetUiStateValidation() {
        _uiStateValidation.value = UiState.Loading
    }

    fun resetUiStatePhone() {
        _uiStatePhone.value = UiState.Loading
    }
}