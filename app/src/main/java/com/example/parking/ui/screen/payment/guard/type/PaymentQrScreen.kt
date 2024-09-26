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
import com.example.parking.ui.content.payment.guard.type.PaymentQrContent
import com.example.parking.ui.screen.payment.HistoryPaymentDto
import com.example.parking.ui.screen.payment.PaymentViewModel
import com.example.parking.ui.utils.ViewModelFactory
import kotlinx.serialization.json.Json

@Composable()
fun PaymentQrScreen(
    navController: NavHostController,
    viewModel: PaymentViewModel = viewModel(
        factory = ViewModelFactory(Injection, LocalContext.current)
    ),

){
    val historyPayment = remember {
        mutableStateOf(HistoryPaymentDto("","",""))
    }

    val alertDataQr = remember { mutableStateOf(false) }
    val customError = remember {
        mutableStateOf("")
    }
    LaunchedEffect(true) {
        viewModel.getDataQr()
    }

    viewModel.uiStateDataQr.collectAsState(initial = UiState.Loading).value.let {
            uiState ->
        when (uiState) {
            is UiState.Error -> {
                alertDataQr.value = true
                customError.value = uiState.errorMessage
            }
            is UiState.Success -> {
                historyPayment.value = Json.decodeFromString(uiState.data.toString())
            }
            is UiState.Loading -> {}
        }
    }

    PaymentQrContent(
        historyPayment = historyPayment,
        backClick = {
            navController.navigateUp()
        }
    )

    if(alertDataQr.value) {
        AlertDialogExample(
            onDismissRequest = {
                alertDataQr.value = false
                viewModel.resetUiStateDataQr()
            },
            onConfirmation = {
                alertDataQr.value = false
                viewModel.resetUiStateDataQr()
            },
            dialogTitle = "Alert",
            dialogText = "Error: ${customError.value}",
        )
    }
}