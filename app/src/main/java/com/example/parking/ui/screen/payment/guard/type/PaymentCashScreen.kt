package com.example.parking.ui.screen.payment.guard.type

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.parking.di.Injection
import com.example.parking.ui.common.UiState
import com.example.parking.ui.component.AlertDialogExample
import com.example.parking.ui.content.payment.guard.type.PaymentCashContent
import com.example.parking.ui.screen.payment.PaymentViewModel
import com.example.parking.ui.utils.ViewModelFactory
import com.example.parking.ui.utils.convertStatusV2

@Composable()
fun PaymentCashScreen(
    navController: NavHostController,
    viewModel: PaymentViewModel = viewModel(
        factory = ViewModelFactory(Injection, LocalContext.current)
    )
){
    LaunchedEffect(true) {
        viewModel.getHistoryId()
    }

    val parkingHistoryId = remember {
        mutableStateOf("")
    }

    val alertHistoryId = remember { mutableStateOf(false) }
    val alertUpdate = remember { mutableStateOf(false) }
    val isEnabled = remember { mutableStateOf(true) }
    val customError = remember {
        mutableStateOf("")
    }

    PaymentCashContent(
        backClick = {
            navController.navigateUp()
        },
        onSubmit = {
            viewModel.updateParkingHistoryCash(parkingHistoryId.value)
        },
        isEnabled = isEnabled
    )

    viewModel.uiStateHistoryId.collectAsState(initial = UiState.Loading).value.let {
            uiState ->
        when (uiState) {
            is UiState.Error -> {
                alertHistoryId.value = true
                customError.value = uiState.errorMessage
            }
            is UiState.Success -> {
                parkingHistoryId.value = uiState.data.toString()
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
                isEnabled.value = false
            }
            is UiState.Loading -> {}
        }
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
}