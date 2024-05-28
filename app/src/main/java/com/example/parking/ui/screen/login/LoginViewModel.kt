package com.example.parking.ui.screen.login
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.parking.data.model.WA.BodySendOtp
import com.example.parking.data.preferences.SettingLocalStorage
import com.example.parking.data.remote.response.WA.OtpResponse
import com.example.parking.data.repository.UserRepository
import com.example.parking.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class LoginViewModel(
    private  val userRepository: UserRepository,
    private val localStorage: SettingLocalStorage
): ViewModel() {
    private val _uiStateOtp: MutableStateFlow<UiState<OtpResponse>> = MutableStateFlow(
        UiState.Loading)
    val uiStateOtp: StateFlow<UiState<OtpResponse>>
        get() = _uiStateOtp

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

    suspend fun savePhone(phone: String){
        localStorage.saveSetting("otp",phone)
    }

    fun resetUiStateOtp() {
        _uiStateOtp.value = UiState.Loading
    }
}