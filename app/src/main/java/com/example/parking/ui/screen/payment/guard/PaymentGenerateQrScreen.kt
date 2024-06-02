package com.example.parking.ui.screen.payment.guard

import android.annotation.SuppressLint
import android.net.Uri
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.parking.di.Injection
import com.example.parking.ui.common.UiState
import com.example.parking.ui.component.AlertDialogExample
import com.example.parking.ui.content.payment.guard.PaymentGenerateQrContent
import com.example.parking.ui.content.payment.guard.type.PaymentCashContent
import com.example.parking.ui.navigation.Screen
import com.example.parking.ui.screen.payment.HistoryPaymentDto
import com.example.parking.ui.screen.payment.PaymentViewModel
import com.example.parking.ui.utils.ViewModelFactory
import kotlinx.coroutines.launch
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import okhttp3.internal.http2.Huffman

@SuppressLint("SuspiciousIndentation")
@Composable()
fun PaymentGenerateQrScreen(
    navController: NavHostController,
    viewModel: PaymentViewModel = viewModel(
        factory = ViewModelFactory(Injection, LocalContext.current)
    )
){
    val coroutineScope = rememberCoroutineScope()
    val historyPayment = remember {
        mutableStateOf(HistoryPaymentDto("","",""))
    }

    val alertHistoryId = remember { mutableStateOf(false) }
    val alertHistory = remember { mutableStateOf(false) }
    val alertGenerate = remember { mutableStateOf(false) }
    val isSuccess = remember { mutableStateOf(false) }
    val customError = remember {
        mutableStateOf("")
    }

    val pricing = remember {
        mutableStateOf("")
    }

    LaunchedEffect(true) {
        viewModel.getHistoryId()
    }

    LaunchedEffect(isSuccess.value) {
        if(isSuccess.value) {
            viewModel.resetDetailHistory()
            viewModel.resetUiStateGenerateQr()
            isSuccess.value = false
            navController.navigate(Screen.Payment.createRoute("qr"))
        }
    }

    viewModel.uiStateDetailHistory.collectAsState(initial = UiState.Loading).value.let {
            uiState ->
        when (uiState) {
            is UiState.Error -> {
                alertHistory.value = true
                customError.value = uiState.errorMessage
            }
            is UiState.Success -> {
                historyPayment.value = historyPayment.value.copy(totalAmount = uiState.data?.data?.parkingHistory?.amount.toString())
            }
            is UiState.Loading -> {}
        }
    }

    viewModel.uiStateHistoryId.collectAsState(initial = UiState.Loading).value.let {
            uiState ->
        when (uiState) {
            is UiState.Error -> {
                alertHistoryId.value = true
                customError.value = uiState.errorMessage
            }
            is UiState.Success -> {
                historyPayment.value = historyPayment.value.copy(parkingHistoryId = uiState.data.toString())
                coroutineScope.launch {
                    viewModel.getHistoryById(id=uiState.data.toString())
                }
            }
            is UiState.Loading -> {}
        }
    }

    viewModel.uiStateGenerateQr.collectAsState(initial = UiState.Loading).value.let {
            uiState ->
        when (uiState) {
            is UiState.Error -> {
                alertGenerate.value = true
                customError.value = uiState.errorMessage
            }
            is UiState.Success -> {
                coroutineScope.launch {
                    Log.d("debug raw data","data: ${uiState.data?.data}")
                    Log.d("action","action data ${uiState.data?.data?.transaction?.actions}")
                    historyPayment.value = HistoryPaymentDto(
                        parkingHistoryId = historyPayment.value.parkingHistoryId,
                        historyUrl = uiState.data?.data?.transaction?.actions?.take(1)?.first()?.url.toString(),
                        totalAmount = uiState.data?.data?.parkingHistory?.totalAmount.toString(),

                    )
                        Log.d("debug data pertama","data: ${historyPayment.value}")
                        val dataQr = Json.encodeToString(historyPayment.value)
                        viewModel.saveDataQr(dataQr)
                        Log.d("debug data kedua","data: ${dataQr}")
                        isSuccess.value = true
                }
            }
            is UiState.Loading -> {}
        }
    }

    PaymentGenerateQrContent(
        historyPayment = historyPayment,
        backClick = {
            navController.navigateUp()
        },
        generateQr = {
            viewModel.generateQr(historyPayment.value.parkingHistoryId)
        }
    )

    if(alertGenerate.value) {
        AlertDialogExample(
            onDismissRequest = {
                alertGenerate.value = false
                viewModel.resetUiStateGenerateQr()
            },
            onConfirmation = {
                alertGenerate.value = false
                viewModel.resetUiStateGenerateQr()
            },
            dialogTitle = "Alert",
            dialogText = "Error: ${customError.value}",
        )
    }

    if(alertHistoryId.value) {
        AlertDialogExample(
            onDismissRequest = {
                alertHistoryId.value = false
                viewModel.resetUiStateHistoryId()
            },
            onConfirmation = {
                alertHistoryId.value = false
                viewModel.resetUiStateHistoryId()
            },
            dialogTitle = "Alert",
            dialogText = "Error: ${customError.value}",
        )
    }

    if(alertHistory.value) {
        AlertDialogExample(
            onDismissRequest = {
                alertHistory.value = false
                viewModel.resetDetailHistory()
            },
            onConfirmation = {
                alertHistory.value = false
                viewModel.resetDetailHistory()
            },
            dialogTitle = "Alert",
            dialogText = "Error: ${customError.value}",
        )
    }
}