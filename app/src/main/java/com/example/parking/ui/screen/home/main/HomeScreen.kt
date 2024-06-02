package com.example.parking.ui.screen.home.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.parking.data.remote.response.Auth.DataValidation
import com.example.parking.di.Injection
import com.example.parking.ui.common.UiState
import com.example.parking.ui.component.AlertDialogExample
import com.example.parking.ui.content.home.main.HomeScreenContent
import com.example.parking.ui.navigation.Screen
import com.example.parking.ui.screen.home.Area
import com.example.parking.ui.screen.home.HomeViewModel
import com.example.parking.ui.screen.home.UpdateParkingHistoryDto
import com.example.parking.ui.utils.ViewModelFactory
import com.example.parking.ui.utils.convertStatusV2
import kotlinx.serialization.json.Json

@Composable
fun HomeScreen(
    navController: NavHostController = rememberNavController(),
    viewModel: HomeViewModel = viewModel(
        factory = ViewModelFactory(Injection, LocalContext.current)
    ),
){
    val dataUserLocal = remember {
        mutableStateOf(DataValidation())
    }

    val dataFormUpdate = remember {
        mutableStateOf(UpdateParkingHistoryDto())
    }
    val alertUser = remember { mutableStateOf(false) }
    val alertUpdate = remember { mutableStateOf(false) }
    val alertHistory = remember { mutableStateOf(false) }
    val customError = remember {
        mutableStateOf("")
    }

    val isAuth = remember {
        mutableStateOf(false)
    }

    LaunchedEffect(true) {
        viewModel.authenticationUser()
    }

    LaunchedEffect(dataFormUpdate.value.isConfirm) {
        dataFormUpdate.value = dataFormUpdate.value.copy(status = "Konfirmasi")
    }

    LaunchedEffect(dataFormUpdate.value.isActive) {
        dataFormUpdate.value = dataFormUpdate.value.copy(status = "Aktif")
    }

    viewModel.uiStateUser.collectAsState(initial = UiState.Loading).value.let {
        uiState ->
        when (uiState) {
            is UiState.Error -> {
                alertUser.value = true
                customError.value = uiState.errorMessage
            }
            is UiState.Success -> {
                dataUserLocal.value = Json.decodeFromString(uiState.data.toString())
                isAuth.value = true
            }
            is UiState.Loading -> {}
        }
    }

    viewModel.uiStateActiveHistoryUser.collectAsState(initial = UiState.Loading).value.let {
            uiState ->
        when (uiState) {
            is UiState.Error -> {
                if(customError.value == "Ticket is not issue"){
                    dataFormUpdate.value = dataFormUpdate.value.copy(
                        isConfirm = false,
                        isActive = false,
                        status = "Tidak Aktif"
                    )
                } else {
                    alertHistory.value = true
                    customError.value = uiState.errorMessage
                }
            }
            is UiState.Success -> {
                dataFormUpdate.value = dataFormUpdate.value.copy(
                    status = convertStatusV2(uiState.data?.data?.ticketStatus.toString()),
                    isActive = uiState.data?.data?.ticketStatus.toString() == "Active",
                    isConfirm = uiState.data?.data?.ticketStatus.toString() == "Default",
                    idTransaction = uiState.data?.data?.id.toString(),
                    areaName = uiState.data?.data?.parkingLot?.areaName.toString()
                )
            }
            is UiState.Loading -> {}
        }
    }

    viewModel.uiStateUpdateHistory.collectAsState(initial = UiState.Loading).value.let {
            uiState ->
        when (uiState) {
            is UiState.Error -> {
                alertUpdate.value = true
                customError.value = uiState.errorMessage
            }
            is UiState.Success -> {
                dataFormUpdate.value = dataFormUpdate.value.copy(
                    status = convertStatusV2(uiState.data?.data?.parkingHistory?.ticketStatus.toString()),
                    isActive = uiState.data?.data?.parkingHistory?.ticketStatus.toString() == "Active",
                    isConfirm = uiState.data?.data?.parkingHistory?.ticketStatus.toString() == "Default"
                )
            }
            is UiState.Loading -> {}
        }
    }

    if(isAuth.value){
        HomeScreenContent(
            userDataLocal = dataUserLocal,
            dataForm = dataFormUpdate,
            paymentClick = {
                navController.navigate(Screen.Payment.createRoute("user"))
            },
            onRefresh = {
                if(isAuth.value){
                    viewModel.detailHistoryByUser(dataUserLocal.value.user?.id.toString())
                }
            },
            onSubmit = {
                dataFormUpdate.value = dataFormUpdate.value.copy(status = "Active")
                viewModel.updateParkingHistory(bodyForm = dataFormUpdate.value)
            }
        )
    }

    if(alertHistory.value) {
        AlertDialogExample(
            onDismissRequest = {
                alertHistory.value = false
                viewModel.resetUiStateActiveHistoryUser()
            },
            onConfirmation = {
                alertHistory.value = false
                viewModel.resetUiStateActiveHistoryUser()
            },
            dialogTitle = "Alert",
            dialogText = "Error: ${customError.value}",
        )
    }

    if(alertUpdate.value) {
        AlertDialogExample(
            onDismissRequest = {
                alertUpdate.value = false
                viewModel.resetUiStateUpdateHistory()
            },
            onConfirmation = {
                alertUpdate.value = false
                viewModel.resetUiStateUpdateHistory()
            },
            dialogTitle = "Alert",
            dialogText = "Error: ${customError.value}",
        )
    }
    if(alertUser.value) {
        AlertDialogExample(
            onDismissRequest = {
                    alertUser.value = false
                    viewModel.resetUiStateUser()
                    navController.navigateUp()
            },
            onConfirmation = {
                    alertUser.value = false
                    viewModel.resetUiStateUser()
                    navController.navigateUp()
            },
            dialogTitle = "Alert",
            dialogText = "Error: ${customError.value}",
        )
    }
}