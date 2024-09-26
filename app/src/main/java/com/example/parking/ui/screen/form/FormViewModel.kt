package com.example.parking.ui.screen.form

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.parking.data.model.Parking.BodyAddParking
import com.example.parking.data.model.Parking.BodyUpdateParking
import com.example.parking.data.model.User.BodyCreateUser
import com.example.parking.data.model.User.QueryGetUser
import com.example.parking.data.preferences.SettingLocalStorage
import com.example.parking.data.remote.response.Parking.AddParkingResponse
import com.example.parking.data.remote.response.Parking.GetDetailParkingResponse
import com.example.parking.data.remote.response.Parking.GetParkingOwnerResponse
import com.example.parking.data.remote.response.Parking.UpdateParkingResponse
import com.example.parking.data.remote.response.User.CreateUserResponse
import com.example.parking.data.remote.response.User.GetUserResponse
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

    private val _uiStateDetailArea: MutableStateFlow<UiState<GetDetailParkingResponse>> = MutableStateFlow(UiState.Loading)
    val uiStateDetailArea: StateFlow<UiState<GetDetailParkingResponse>>
        get() = _uiStateDetailArea

    private val _uiStateParkingId: MutableStateFlow<UiState<String>> = MutableStateFlow(UiState.Loading)
    val uiStateParkingId: StateFlow<UiState<String>>
        get() = _uiStateParkingId

    private val _uiStateParkingUpdate: MutableStateFlow<UiState<String>> = MutableStateFlow(UiState.Loading)
    val uiStateParkingUpdate: StateFlow<UiState<String>>
        get() = _uiStateParkingUpdate
    private val _uiStateUpdateArea: MutableStateFlow<UiState<UpdateParkingResponse>> = MutableStateFlow(
        UiState.Loading)
    val uiStateUpdateArea: StateFlow<UiState<UpdateParkingResponse>>
        get() = _uiStateUpdateArea

    private val _uiStateGetUser: MutableStateFlow<UiState<GetUserResponse>> = MutableStateFlow(
        UiState.Loading)
    val uiStateGetUser: StateFlow<UiState<GetUserResponse>>
        get() = _uiStateGetUser

    private val _uiStateGetUserGlobal: MutableStateFlow<UiState<GetUserResponse>> = MutableStateFlow(
        UiState.Loading)
    val uiStateGetUserGlobal: StateFlow<UiState<GetUserResponse>>
        get() = _uiStateGetUserGlobal
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
    fun getParkingId() {
        viewModelScope.launch {
            try {
                localStorage.getSetting("parkingId").catch {
                    _uiStateParkingId.value = UiState.Error(it.message.toString())
                }.collect {
                        data ->
                    _uiStateParkingId.value = UiState.Success(data)
                }
            } catch (e:Exception) {
                _uiStateParkingId.value = UiState.Error(e.message.toString())
            }
        }
    }

    fun getParkingData() {
        viewModelScope.launch {
            try {
                localStorage.getSetting("parkingUpdate").catch {
                    _uiStateParkingUpdate.value = UiState.Error(it.message.toString())
                }.collect {
                        data ->
                    _uiStateParkingUpdate.value = UiState.Success(data)
                }
            } catch (e:Exception) {
                _uiStateParkingUpdate.value = UiState.Error(e.message.toString())
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

    fun updateArea(formArea: UpdateAreaDto) {
        viewModelScope.launch {
            try {
                val bodyParking: BodyUpdateParking = BodyUpdateParking(
                    address = formArea.address,
                    motor_cost = formArea.motorPrice.toInt(),
                    car_cost = formArea.carPrice.toInt(),
                    park_keeper_ids = formArea.listGuard.map { it -> it.id }.toMutableList()
                )
                parkingRepository.updateParking(formArea.id,bodyParking).catch {
                    _uiStateUpdateArea.value = UiState.Error(it.message.toString())
                }. collect{
                        parkingResponse ->
                    _uiStateUpdateArea.value = UiState.Success(parkingResponse)
                }
            } catch (e:Exception) {
                _uiStateUpdateArea.value = UiState.Error(e.message.toString())
            }
        }
    }

    fun getKeeperUserByOwner(id:String) {
        viewModelScope.launch {
            try {
                userRepository.getUser(
                    QueryGetUser(
                        already_assigned = "false",
                        owner_id = id
                    )
                ).catch {
                    _uiStateGetUser.value = UiState.Error(it.message.toString())
                }. collect{
                        data ->
                    _uiStateGetUser.value = UiState.Success(data)
                }
            } catch (e:Exception) {
                _uiStateGetUser.value = UiState.Error(e.message.toString())
            }
        }
    }

    fun getKeeperOwnerByOwner(id:String) {
        viewModelScope.launch {
            try {
                userRepository.getUser(
                    QueryGetUser(
                        owner_id = id
                    )
                ).catch {
                    _uiStateGetUserGlobal.value = UiState.Error(it.message.toString())
                }. collect{
                        data ->
                    _uiStateGetUserGlobal.value = UiState.Success(data)
                }
            } catch (e:Exception) {
                _uiStateGetUserGlobal.value = UiState.Error(e.message.toString())
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
                    belong_to_parking_lot_id = formGuard.selectedArea.value,
                    owner_id = formGuard.ownerId.value

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

    fun getAreaById(id: String) {
        viewModelScope.launch {
            try {
                parkingRepository.getDetailParking(id).catch {
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

    fun resetUiStateDetailArea() {
        _uiStateDetailArea.value = UiState.Loading
    }
    fun resetUiStateParkingId() {
        _uiStateParkingId.value = UiState.Loading
    }

    fun resetUiStateUpdateArea() {
        _uiStateUpdateArea.value = UiState.Loading
    }

    fun resetUiStateParkingUpdate() {
        _uiStateParkingUpdate.value = UiState.Loading
    }

    fun resetUiStateGetUser() {
        _uiStateGetUser.value = UiState.Loading
    }
    suspend fun saveParkingId(id:String) {
        localStorage.saveSetting("parkingId",id)
    }

    suspend fun saveUpdateDataForm(id:String) {
        localStorage.saveSetting("parkingUpdate",id)
    }
}