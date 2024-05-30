package com.example.parking.ui.screen.payment.main

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.parking.data.remote.response.Auth.DataValidation
import com.example.parking.di.Injection
import com.example.parking.ui.common.UiState
import com.example.parking.ui.component.AlertDialogExample
import com.example.parking.ui.content.payment.main.PaymentContent
import com.example.parking.ui.screen.payment.EasyparkHistory
import com.example.parking.ui.screen.payment.KeeperOngoingTransaction
import com.example.parking.ui.screen.payment.PaymentViewModel
import com.example.parking.ui.utils.ViewModelFactory
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PaymentScreen(
    navController: NavHostController,
    viewModel: PaymentViewModel = viewModel(
        factory = ViewModelFactory(Injection, LocalContext.current)
    ),
    ){
    LaunchedEffect(true) {
        viewModel.authenticationUser()
    }

    val coroutineScope = rememberCoroutineScope()

    val dataUserLocal = remember {
        mutableStateOf(DataValidation())
    }

    val dataEasyparkHistoryLocal = remember {
        mutableListOf<EasyparkHistory>()
    }
    val alertEasyparkHistory = remember { mutableStateOf(false) }

    val alertUser = remember { mutableStateOf(false) }
    val customError = remember {
        mutableStateOf("")
    }

    viewModel.uiStateUser.collectAsState(initial = UiState.Loading).value.let {
            uiState ->
        when (uiState) {
            is UiState.Error -> {
                alertUser.value = true
                customError.value = uiState.errorMessage
            }
            is UiState.Success -> {
                Log.d("check","user:${uiState.data.toString()}")
                dataUserLocal.value = Json.decodeFromString(uiState.data.toString())
                coroutineScope.launch {
                    viewModel.getEasyparkHistory(dataUserLocal.value.user?.id.toString())
                }
            }
            is UiState.Loading -> {}
        }
    }

    viewModel.uiStateaEasyparkHistory.collectAsState(initial = UiState.Loading).value.let {
            uiState ->
        when (uiState) {
            is UiState.Error -> {
                alertEasyparkHistory.value = true
                customError.value = uiState.errorMessage
            }
            is UiState.Success -> {
                dataEasyparkHistoryLocal.clear()
                uiState.data?.data?.parkingHistory?.map {
                        history ->
                    dataEasyparkHistoryLocal.add(
                        EasyparkHistory(
                            areaName = history?.parkingLot?.areaName.toString(),
                            checkIn = history?.checkInDate.toString(),
                            checkOut = history?.checkOutDate.toString(),
                            payment = history?.payment.toString(),
                            amount = history?.totalAmount.toString()
                        )
                    )
                }
            }
            is UiState.Loading -> {}
        }
    }

    PaymentContent(
        homeClick = {
            navController.navigateUp()
        },
        listHistory = dataEasyparkHistoryLocal
    )

    if(alertEasyparkHistory.value) {
        AlertDialogExample(
            onDismissRequest = {
                alertEasyparkHistory.value = false
                viewModel.resetUiStateEasyparkHistory()
            },
            onConfirmation = {
                alertEasyparkHistory.value = false
                viewModel.resetUiStateEasyparkHistory()
            },
            dialogTitle = "Alert",
            dialogText = "Error: ${customError.value}",
        )
    }
}