package com.example.parking.ui.screen.payment

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import com.example.parking.data.repository.UserRepository
import com.example.parking.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import androidx.lifecycle.viewModelScope
import com.example.parking.data.model.ParkingHistory.QueryAggregateHistory
import com.example.parking.data.preferences.SettingLocalStorage
import com.example.parking.data.remote.response.Parking.GetParkingOwnerResponse
import com.example.parking.data.remote.response.ParkingHistory.GetHistoryResponse
import com.example.parking.data.repository.ParkingHistoryRepository
import com.example.parking.data.repository.ParkingRepository
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.YearMonth
import java.time.ZoneId
import java.time.format.DateTimeFormatter

class PaymentViewModel(
    private  val userRepository: UserRepository,
    private val localStorage: SettingLocalStorage,
    private val parkingRepostiory: ParkingRepository,
    private val parkingHistoryRepository: ParkingHistoryRepository,
): ViewModel() {
    private val _uiStateUser: MutableStateFlow<UiState<String>> = MutableStateFlow(UiState.Loading)
    val uiStateUser: StateFlow<UiState<String>>
        get() = _uiStateUser
    private val _uiStateAreaOwner: MutableStateFlow<UiState<GetParkingOwnerResponse>> = MutableStateFlow(UiState.Loading)

    val uiStateaAreaOwner: StateFlow<UiState<GetParkingOwnerResponse>>
        get() = _uiStateAreaOwner

    private val _uiStateaEasyparkHistory: MutableStateFlow<UiState<GetHistoryResponse>> = MutableStateFlow(UiState.Loading)
    val uiStateaEasyparkHistory: StateFlow<UiState<GetHistoryResponse>>
        get() = _uiStateaEasyparkHistory

    private val _uiStateKeeperHistory: MutableStateFlow<UiState<GetHistoryResponse>> = MutableStateFlow(UiState.Loading)
    val uiStateKeeperHistory: StateFlow<UiState<GetHistoryResponse>>
        get() = _uiStateKeeperHistory

    private val _uiStateKeeperOngoingTransaction: MutableStateFlow<UiState<GetHistoryResponse>> = MutableStateFlow(UiState.Loading)
    val uiStateKeeperOngoingTransaction: StateFlow<UiState<GetHistoryResponse>>
        get() = _uiStateKeeperOngoingTransaction

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

    @RequiresApi(Build.VERSION_CODES.O)
    fun getEasyparkHistory(id: String) {
        viewModelScope.launch {
            try {
                val currentYearMonth = YearMonth.now()

                val startOfMonth = currentYearMonth.atDay(1)
                val endOfMonth = currentYearMonth.atEndOfMonth()

                val localZoneId = ZoneId.systemDefault()

                val startOfMonthLocal = startOfMonth.atStartOfDay(localZoneId)
                val endOfMonthLocal = endOfMonth.atTime(23, 59, 59, 999999999).atZone(localZoneId)

                val startOfMonthUTC = startOfMonthLocal.withZoneSameInstant(ZoneId.of("UTC"))
                val endOfMonthUTC = endOfMonthLocal.withZoneSameInstant(ZoneId.of("UTC"))

                val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")

                val startOfMonthUTCString = startOfMonthUTC.format(formatter)
                val endOfMonthUTCString = endOfMonthUTC.format(formatter)

                val queryAggregateHistory = QueryAggregateHistory(
                    created_at_start_filter = startOfMonthUTCString,
                    created_at_end_filter = endOfMonthUTCString,
                    easypark_id = id,
                    ticket_status = "NotActive",
                )

                parkingHistoryRepository.aggregateHistory(queryAggregateHistory).catch {
                    _uiStateaEasyparkHistory.value = UiState.Error(it.message.toString())
                }.collect {
                        data ->
                    _uiStateaEasyparkHistory.value = UiState.Success(data)
                }
            } catch (e:Exception) {
                _uiStateaEasyparkHistory.value = UiState.Error(e.message.toString())
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getKeeperOngoingTransaction(id: String) {
        viewModelScope.launch {
            try {
                val currentDate = LocalDate.now()

                val startOfDay = currentDate.atStartOfDay()
                val endOfDay = currentDate.atTime(23, 59, 59, 999999999)

                val localZoneId = ZoneId.systemDefault()

                val startOfDayLocal = startOfDay.atZone(localZoneId)
                val endOfDayLocal = endOfDay.atZone(localZoneId)

                val startOfDayUTC = startOfDayLocal.withZoneSameInstant(ZoneId.of("UTC"))
                val endOfDayUTC = endOfDayLocal.withZoneSameInstant(ZoneId.of("UTC"))

                val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")

                val startOfDayUTCString = startOfDayUTC.format(formatter)
                val endOfDayUTCString = endOfDayUTC.format(formatter)

                val queryAggregateHistory = QueryAggregateHistory(
                    created_at_start_filter = startOfDayUTCString,
                    created_at_end_filter = endOfDayUTCString,
                    keeper_id = id,
                    ticket_status = "Active",
                )

                parkingHistoryRepository.aggregateHistory(queryAggregateHistory).catch {
                    _uiStateKeeperOngoingTransaction.value = UiState.Error(it.message.toString())
                }.collect {
                        data ->
                    _uiStateKeeperOngoingTransaction.value = UiState.Success(data)
                }
            } catch (e:Exception) {
                _uiStateKeeperOngoingTransaction.value = UiState.Error(e.message.toString())
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getKeeperHistory(id: String) {
        viewModelScope.launch {
            try {
                val currentYearMonth = YearMonth.now()

                val startOfMonth = currentYearMonth.atDay(1)
                val endOfMonth = currentYearMonth.atEndOfMonth()

                val localZoneId = ZoneId.systemDefault()

                val startOfMonthLocal = startOfMonth.atStartOfDay(localZoneId)
                val endOfMonthLocal = endOfMonth.atTime(23, 59, 59, 999999999).atZone(localZoneId)

                val startOfMonthUTC = startOfMonthLocal.withZoneSameInstant(ZoneId.of("UTC"))
                val endOfMonthUTC = endOfMonthLocal.withZoneSameInstant(ZoneId.of("UTC"))

                val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")

                val startOfMonthUTCString = startOfMonthUTC.format(formatter)
                val endOfMonthUTCString = endOfMonthUTC.format(formatter)

                val queryAggregateHistory = QueryAggregateHistory(
                    created_at_start_filter = startOfMonthUTCString,
                    created_at_end_filter = endOfMonthUTCString,
                    keeper_id = id,
                    ticket_status = "NotActive",
                )

                Log.d("PaymentViewModel.getKeeperHistory", queryAggregateHistory.toString())

                parkingHistoryRepository.aggregateHistory(queryAggregateHistory).catch {
                    _uiStateKeeperHistory.value = UiState.Error(it.message.toString())
                }.collect {
                        data ->
                    _uiStateKeeperHistory.value = UiState.Success(data)
                }
            } catch (e:Exception) {
                _uiStateKeeperHistory.value = UiState.Error(e.message.toString())
            }
        }
    }

    fun resetUiStateUser() {
        _uiStateUser.value = UiState.Loading
    }

    fun resetUiStateAreaByOwner() {
        _uiStateAreaOwner.value = UiState.Loading
    }

    fun resetUiStateEasyparkHistory() {
        _uiStateaEasyparkHistory.value = UiState.Loading
    }

    fun resetUiStateKeeperOngoingTransaction() {
        _uiStateKeeperOngoingTransaction.value = UiState.Loading
    }

    fun resetUiStateKeeperHistory() {
        _uiStateKeeperHistory.value = UiState.Loading
    }
}