package com.example.parking.ui.screen.home

import androidx.lifecycle.ViewModel
import com.example.parking.data.model.User
import com.example.parking.data.repository.UserRepository
import com.example.parking.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class HomeViewModel(
    private val userRepository: UserRepository
): ViewModel() {
    private val _uiState: MutableStateFlow<UiState<User>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<User>>
        get() = _uiState

    fun getUsers(phone: String) {
        viewModelScope.launch{
            userRepository.getMyUsers(phone)
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { user ->
                    _uiState.value = UiState.Success(user)
                }
        }
    }
}