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
import com.example.parking.data.model.ParkingHistory.BodyUpdateHistory
import com.example.parking.data.model.ParkingHistory.CalculationHistoryDto
import com.example.parking.data.model.ParkingHistory.MonthlyQueryHistoryDto
import com.example.parking.data.model.ParkingHistory.QueryAggregateHistory
import com.example.parking.data.model.Payment.BodyCreatePayment
import com.example.parking.data.preferences.SettingLocalStorage
import com.example.parking.data.remote.response.Parking.GetParkingOwnerResponse
import com.example.parking.data.remote.response.ParkingHistory.CalculationHistoryResponse
import com.example.parking.data.remote.response.ParkingHistory.GetDetailHistoryResponse
import com.example.parking.data.remote.response.ParkingHistory.GetHistoryResponse
import com.example.parking.data.remote.response.ParkingHistory.MonthlyCalculationHistoryResponse
import com.example.parking.data.remote.response.ParkingHistory.UpdateHistoryResponse
import com.example.parking.data.remote.response.Payment.CreatePaymentResponse
import com.example.parking.data.repository.ParkingHistoryRepository
import com.example.parking.data.repository.ParkingRepository
import com.example.parking.data.repository.PaymentRepository
import com.example.parking.ui.screen.home.UpdateParkingHistoryDto
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.YearMonth
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalAdjusters
import java.util.Date

class PaymentViewModel(
    private  val userRepository: UserRepository,
    private val localStorage: SettingLocalStorage,
    private val parkingRepostiory: ParkingRepository,
    private val parkingHistoryRepository: ParkingHistoryRepository,
    private val paymentRepository: PaymentRepository,
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

    private val _uiStateKeeperOngoingTransaction: MutableStateFlow<UiState<GetHistoryResponse>> = MutableStateFlow(UiState.Loading)
    val uiStateKeeperOngoingTransaction: StateFlow<UiState<GetHistoryResponse>>
        get() = _uiStateKeeperOngoingTransaction

    private val _uiStateGenerateQr: MutableStateFlow<UiState<CreatePaymentResponse>> = MutableStateFlow(UiState.Loading)
    val uiStateGenerateQr: StateFlow<UiState<CreatePaymentResponse>>
        get() = _uiStateGenerateQr

    private val _uiStateDetailHistory: MutableStateFlow<UiState<GetDetailHistoryResponse>> = MutableStateFlow(UiState.Loading)
    val uiStateDetailHistory: StateFlow<UiState<GetDetailHistoryResponse>>
        get() = _uiStateDetailHistory

    private val _uiStateHistoryId: MutableStateFlow<UiState<String>> = MutableStateFlow(UiState.Loading)
    val uiStateHistoryId: StateFlow<UiState<String>>
        get() = _uiStateHistoryId

    private val _uiStateDataQr: MutableStateFlow<UiState<String>> = MutableStateFlow(UiState.Loading)
    val uiStateDataQr: StateFlow<UiState<String>>
        get() = _uiStateDataQr

       private val _uiStateUpdateHistory: MutableStateFlow<UiState<UpdateHistoryResponse>> = MutableStateFlow(UiState.Loading)
    val uiStateUpdateHistory: StateFlow<UiState<UpdateHistoryResponse>>
        get() = _uiStateUpdateHistory


    private val _uiStateMonthly: MutableStateFlow<UiState<MonthlyCalculationHistoryResponse>> = MutableStateFlow(UiState.Loading)
    val uiStateMontly: StateFlow<UiState<MonthlyCalculationHistoryResponse>>
        get() = _uiStateMonthly

    private val _uiStateCalculation: MutableStateFlow<UiState<CalculationHistoryResponse>> = MutableStateFlow(UiState.Loading)
    val uiStateCalculation: StateFlow<UiState<CalculationHistoryResponse>>
        get() = _uiStateCalculation
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

    fun getDataQr() {
        viewModelScope.launch {
            try {
                localStorage.getSetting("dataQr").catch {
                    _uiStateDataQr.value = UiState.Error(it.message.toString())
                }.collect {
                        user ->
                    _uiStateDataQr.value = UiState.Success(user)
                }
            } catch (e:Exception) {
                _uiStateDataQr.value = UiState.Error(e.message.toString())
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
//                    created_at_start_filter = startOfDayUTCString,
//                    created_at_end_filter = endOfDayUTCString,
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

    suspend fun saveParkingHistoryId(id:String) {
        localStorage.saveSetting("historyId",id)
    }
    suspend fun saveDataQr(data:String) {
        localStorage.saveSetting("dataQr",data)
    }
    fun getHistoryId() {
        viewModelScope.launch {
            try {
                localStorage.getSetting("historyId").catch {
                    _uiStateHistoryId.value = UiState.Error(it.message.toString())
                }.collect {
                        historyId ->
                    _uiStateHistoryId.value = UiState.Success(historyId)
                }
            } catch (e:Exception) {
                _uiStateHistoryId.value = UiState.Error(e.message.toString())
            }
        }
    }

    fun getHistoryById(id: String) {
        viewModelScope.launch {
            try {
                parkingHistoryRepository.detailHistory(id).catch {
                    _uiStateDetailHistory.value = UiState.Error(it.message.toString())
                }.collect {
                        data ->
                    _uiStateDetailHistory.value = UiState.Success(data)
                }
            } catch (e:Exception) {
                _uiStateDetailHistory.value = UiState.Error(e.message.toString())
            }
        }
    }

    fun generateQr(id: String) {
        viewModelScope.launch {
            try {
                paymentRepository.createPayment(bodyCreatePayment = BodyCreatePayment(id)).catch {
                    _uiStateGenerateQr.value = UiState.Error(it.message.toString())
                }.collect {
                        data ->
                    _uiStateGenerateQr.value = UiState.Success(data)
                }
            } catch (e:Exception) {
                _uiStateGenerateQr.value = UiState.Error(e.message.toString())
            }
        }
    }

    fun updateParkingHistoryCash(id:String) {
        viewModelScope.launch {
            val bodyUpdate = BodyUpdateHistory(
                ticket_status = "NotActive"
            )
            try {
                parkingHistoryRepository.updateHistory(id,bodyUpdate).catch {
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

    @RequiresApi(Build.VERSION_CODES.O)
    fun getMonthlyChart(id:String) {
        viewModelScope.launch {
            try {
                parkingHistoryRepository.monthlyChartHistory(
                   queryMonthly = MonthlyQueryHistoryDto(
                       id
                   )
                ).catch {
                    _uiStateMonthly.value = UiState.Error(it.message.toString())
                }.collect {
                        data ->
                    _uiStateMonthly.value = UiState.Success(data)
                }
            } catch (e:Exception) {
                _uiStateMonthly.value = UiState.Error(e.message.toString())
            }
        }
    }
    @RequiresApi(Build.VERSION_CODES.O)
    fun getCalculationMonthly(id:String,isOwner:Boolean = true) {
        viewModelScope.launch {
            try {
                val currentYearMonth = YearMonth.now()
                val startOfMonth = currentYearMonth.atDay(1)
                val endOfMonth = currentYearMonth.atEndOfMonth()
                val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
                val startOfMonthFormatted = startOfMonth.format(formatter)
                val endOfMonthFormatted = endOfMonth.format(formatter)
                val createdAtStartFilter = "$startOfMonthFormatted 00:00:00.000z"
                val createdAtEndFilter = "$endOfMonthFormatted 23:59:59.999z"
                var filter = CalculationHistoryDto(
                    createdAtStartFilter = createdAtStartFilter,
                    createdAtEndFilter = createdAtEndFilter,
                )
                
                if(isOwner){
                    filter = filter.copy(ownerId = id)
                } else {
                    filter = filter.copy(keeperId = id)
                }
                parkingHistoryRepository.calculationHistory(
                    filter
                ).catch {
                    _uiStateCalculation.value = UiState.Error(it.message.toString())
                }.collect {
                        data ->
                    _uiStateCalculation.value = UiState.Success(data)
                }
            } catch (e:Exception) {
                _uiStateCalculation.value = UiState.Error(e.message.toString())
            }
        }
    }
    fun resetUiStateUser() {
        _uiStateUser.value = UiState.Loading
    }

    fun resetUiStateAreaByOwner() {
        _uiStateAreaOwner.value = UiState.Loading
    }

    fun resetDetailHistory() {
        _uiStateDetailHistory.value = UiState.Loading
    }

    fun resetUiStateGenerateQr() {
        _uiStateGenerateQr.value = UiState.Loading
    }

    fun resetUiStateHistoryId() {
        _uiStateHistoryId.value = UiState.Loading
    }

    fun resetUiStateDataQr() {
        _uiStateDataQr.value = UiState.Loading
    }

    fun resetUiStateEasyparkHistory() {
        _uiStateaEasyparkHistory.value = UiState.Loading
    }

    fun resetUiStateKeeperOngoingTransaction() {
        _uiStateKeeperOngoingTransaction.value = UiState.Loading
    }
    fun resetUiStateUpdateHistory() {
        _uiStateUpdateHistory.value = UiState.Loading
    }

    fun resetUiStateMonthly() {
        _uiStateMonthly.value = UiState.Loading
    }

    fun resetUiStateCalculation() {
        _uiStateCalculation.value = UiState.Loading
    }
}