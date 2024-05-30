package com.example.parking.ui.screen.home.guard

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.parking.data.remote.response.Auth.DataValidation
import com.example.parking.di.Injection
import com.example.parking.ui.common.UiState
import com.example.parking.ui.component.AlertDialogExample
import com.example.parking.ui.content.home.guard.HomeGuidanceContent
import com.example.parking.ui.navigation.Screen
import com.example.parking.ui.screen.home.Area
import com.example.parking.ui.screen.home.CreateParkingHistoryDto
import com.example.parking.ui.screen.home.HomeViewModel
import com.example.parking.ui.utils.ViewModelFactory
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json

@Composable
fun HomeGuardScreen(
    navController: NavHostController = rememberNavController(),
    viewModel: HomeViewModel = viewModel(
        factory = ViewModelFactory(Injection, LocalContext.current)
    ),
){
    val coroutineScope = rememberCoroutineScope()

    val dataUserLocal = remember {
        mutableStateOf(DataValidation())
    }

    val dataArea = remember {
        mutableStateOf(Area())
    }

    val dataCreateForm = remember {
        mutableStateOf(CreateParkingHistoryDto())
    }

    val isSuccessUser = remember {
        mutableStateOf(false)
    }
    val alertUser = remember { mutableStateOf(false) }
    val alertSave = remember { mutableStateOf(false) }
    val alertDetailArea = remember { mutableStateOf(false) }
    val customError = remember {
        mutableStateOf("")
    }

    LaunchedEffect(true) {
        viewModel.authenticationUser()
    }
    LaunchedEffect(isSuccessUser.value) {
        if(isSuccessUser.value){
            viewModel.getAreaById(dataUserLocal.value.user?.parkingId.toString())
        }
    }

    viewModel.uiStateDetailArea.collectAsState(initial = UiState.Loading).value.let {
            uiState ->
        when (uiState) {
            is UiState.Error -> {
                alertDetailArea.value = true
                customError.value = uiState.errorMessage
            }
            is UiState.Success -> {
                dataArea.value = Area(
                    id = uiState.data?.data?.id.toString(),
                    name = uiState.data?.data?.areaName.toString()
                )
                dataCreateForm.value = dataCreateForm.value.copy(parking_lot_id = uiState.data?.data?.id.toString())
            }
            is UiState.Loading -> {}
        }
    }

    viewModel.uiStateSaveHistory.collectAsState(initial = UiState.Loading).value.let {
            uiState ->
        when (uiState) {
            is UiState.Error -> {
                alertSave.value = true
                customError.value = uiState.errorMessage
            }
            is UiState.Success -> {
            }
            is UiState.Loading -> {}
        }
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
                dataCreateForm.value = dataCreateForm.value.copy(keeper_id = dataUserLocal.value.user?.id.toString())
                isSuccessUser.value = true
            }
            is UiState.Loading -> {}
        }
    }

    HomeGuidanceContent(
        paymentClick = {
            navController.navigate(Screen.Payment.createRoute("guard"))
        },
        formCreate = dataCreateForm,
        areaParking = dataArea,
        onConfirm = {
            coroutineScope.launch {
                viewModel.saveParkingHistory(bodyForm = dataCreateForm.value)
            }
        }
    )

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

    if(alertDetailArea.value) {
        AlertDialogExample(
            onDismissRequest = {
                alertDetailArea.value = false
                viewModel.resetUiStateDetailArea()
            },
            onConfirmation = {
                alertDetailArea.value = false
                viewModel.resetUiStateDetailArea()
            },
            dialogTitle = "Alert",
            dialogText = "Error: ${customError.value}",
        )
    }

    if(alertSave.value) {
        AlertDialogExample(
            onDismissRequest = {
                alertSave.value = false
                viewModel.resetUiStateSaveHistory()
            },
            onConfirmation = {
                alertSave.value = false
                viewModel.resetUiStateSaveHistory()
            },
            dialogTitle = "Alert",
            dialogText = "Error: ${customError.value}",
        )
    }
}
