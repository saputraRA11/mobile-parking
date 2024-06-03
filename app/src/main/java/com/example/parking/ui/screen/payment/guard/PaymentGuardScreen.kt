package com.example.parking.ui.screen.payment.guard

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
import com.example.parking.ui.content.payment.guard.PaymentGuardContent
import com.example.parking.ui.navigation.Screen
import com.example.parking.ui.screen.payment.ParkingHistory
import com.example.parking.ui.screen.payment.KeeperOngoingTransaction
import com.example.parking.ui.screen.payment.PaymentViewModel
import com.example.parking.ui.utils.ViewModelFactory
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PaymentGuardScreen(
    navController: NavHostController,
    viewModel: PaymentViewModel = viewModel(
        factory = ViewModelFactory(Injection, LocalContext.current)
    )
){
    LaunchedEffect(true) {
        viewModel.authenticationUser()
    }

    val coroutineScope = rememberCoroutineScope()

    val dataUserLocal = remember {
        mutableStateOf(DataValidation())
    }

    val dataKeeperOngoingTransactionLocal = remember {
        mutableListOf<KeeperOngoingTransaction>()
    }
    val alertKeeperOngoingTransaction = remember { mutableStateOf(false) }

    val dataKeeperHistoryLocal = remember {
        mutableListOf<ParkingHistory>()
    }
    val alertKeeperHistory = remember { mutableStateOf(false) }


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
                    viewModel.getKeeperOngoingTransaction(dataUserLocal.value.user?.id.toString())
                    viewModel.getKeeperHistory(dataUserLocal.value.user?.id.toString())
                }
            }
            is UiState.Loading -> {}
        }
    }

    viewModel.uiStateKeeperOngoingTransaction.collectAsState(initial = UiState.Loading).value.let {
            uiState ->
        when (uiState) {
            is UiState.Error -> {
                alertKeeperOngoingTransaction.value = true
                customError.value = uiState.errorMessage
            }
            is UiState.Success -> {
                dataKeeperOngoingTransactionLocal.clear()
                uiState.data?.data?.parkingHistory?.map {
                        history ->
                    Log.d("PaymentGuardScreen.viewModel.uiStateKeeperOngoingTransaction", history.toString())
                    dataKeeperOngoingTransactionLocal.add(
                        KeeperOngoingTransaction(
                            id = history?.id.toString(),
                            name = history?.easypark?.name.toString(),
                            checkIn = history?.checkInDate.toString(),
                            payment = history?.payment.toString(),
                        )
                    )
                }
            }
            is UiState.Loading -> {}
        }
    }

    viewModel.uiStateKeeperHistory.collectAsState(initial = UiState.Loading).value.let {
            uiState ->
        when (uiState) {
            is UiState.Error -> {
                alertKeeperHistory.value = true
                customError.value = uiState.errorMessage
            }
            is UiState.Success -> {
                dataKeeperHistoryLocal.clear()
                uiState.data?.data?.parkingHistory?.map {
                        history ->
                    Log.d("PaymentGuardScreen.viewModel.uiStateKeeperHistory", history.toString())
                    dataKeeperHistoryLocal.add(
                        ParkingHistory(
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

    PaymentGuardContent(
        homeClick = {
            navController.navigateUp()
        },
        detailPayment = {
            id,type ->
            when(type) {
                "qr" -> navController.navigate(Screen.Payment.createRoute("generate"))
                "cash" -> navController.navigate(Screen.Payment.createRoute("cash"))
                else  -> navController.navigate(Screen.Payment.createRoute("none"))
            }
        },
        listKeeperOngoingTransactionLocal = dataKeeperOngoingTransactionLocal,
        listParkingHistory = dataKeeperHistoryLocal
    )

    if(alertKeeperOngoingTransaction.value) {
        AlertDialogExample(
            onDismissRequest = {
                alertKeeperOngoingTransaction.value = false
                viewModel.resetUiStateKeeperOngoingTransaction()
            },
            onConfirmation = {
                alertKeeperOngoingTransaction.value = false
                viewModel.resetUiStateKeeperOngoingTransaction()
            },
            dialogTitle = "Alert",
            dialogText = "Error: ${customError.value}",
        )
    }

    if(alertKeeperHistory.value) {
        AlertDialogExample(
            onDismissRequest = {
                alertKeeperOngoingTransaction.value = false
                viewModel.resetUiStateKeeperHistory()
            },
            onConfirmation = {
                alertKeeperOngoingTransaction.value = false
                viewModel.resetUiStateKeeperHistory()
            },
            dialogTitle = "Alert",
            dialogText = "Error: ${customError.value}",
        )
    }
}