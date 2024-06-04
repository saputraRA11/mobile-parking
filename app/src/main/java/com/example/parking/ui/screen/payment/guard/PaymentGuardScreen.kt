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
import com.example.parking.ui.screen.payment.EasyparkHistory
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

    val dataMonthlyHistoryLocal = remember {
        mutableListOf<EasyparkHistory>()
    }

    val dataDailyHistoryLocal = remember {
        mutableListOf<EasyparkHistory>()
    }

    val alertKeeperOngoingTransaction = remember { mutableStateOf(false) }

    val alertUser = remember { mutableStateOf(false) }
    val alertMonthlyHistory = remember { mutableStateOf(false) }
    val alertDailyHistory = remember { mutableStateOf(false) }
    val alertCalculation = remember { mutableStateOf(false) }
    val customError = remember {
        mutableStateOf("")
    }
    val priceCalculation = remember {
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
                dataUserLocal.value = Json.decodeFromString(uiState.data.toString())
                coroutineScope.launch {
                    val userId = dataUserLocal.value.user?.id.toString()
                    Log.d("debug id user",userId)
                    viewModel.getKeeperOngoingTransaction(userId)
                    viewModel.getMonthlyHistory(userId, iskeeper = true)
                    viewModel.getDailyHistory(userId)
                    viewModel.getCalculationMonthly(id = userId)
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

    viewModel.uiStateCalculation.collectAsState(initial = UiState.Loading).value.let {
            uiState ->
        when (uiState) {
            is UiState.Error -> {
                alertCalculation.value = true
                customError.value = uiState.errorMessage
            }
            is UiState.Success -> {
                Log.d("debug montly price","${uiState.data?.data}")
                priceCalculation.value = uiState.data?.data?.sumAll.toString()
                Log.d("debug montly price state","${priceCalculation}")
            }
            is UiState.Loading -> {}
        }
    }

    viewModel.uiStateMonthlyHistory.collectAsState(initial = UiState.Loading).value.let {
            uiState ->
        when (uiState) {
            is UiState.Error -> {
                alertMonthlyHistory.value = true
                customError.value = uiState.errorMessage
            }
            is UiState.Success -> {
                dataMonthlyHistoryLocal.clear()
                uiState.data?.data?.parkingHistory?.map {
                        history ->
                    dataMonthlyHistoryLocal.add(
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

    viewModel.uiStateDailyHistory.collectAsState(initial = UiState.Loading).value.let {
            uiState ->
        when (uiState) {
            is UiState.Error -> {
                alertDailyHistory.value = true
                customError.value = uiState.errorMessage
            }
            is UiState.Success -> {
                dataDailyHistoryLocal.clear()
                uiState.data?.data?.parkingHistory?.map {
                        history ->
                    dataDailyHistoryLocal.add(
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

    PaymentGuardContent(
        homeClick = {
            navController.navigateUp()
        },
        detailPayment = {
            id,type ->
            coroutineScope.launch {
                viewModel.saveParkingHistoryId(id)
            }
            when(type.lowercase()) {
                "qr" -> navController.navigate(Screen.Payment.createRoute("generate"))
                "cash" -> navController.navigate(Screen.Payment.createRoute("cash"))
                else  -> navController.navigate(Screen.Payment.createRoute("none"))
            }
        },
        listKeeperOngoingTransactionLocal = dataKeeperOngoingTransactionLocal,
        priceMonthly = priceCalculation,
        listMonthlyHistory = dataMonthlyHistoryLocal,
        listDailyHistory = dataDailyHistoryLocal
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

    if(alertCalculation.value) {
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

    if(alertMonthlyHistory.value) {
        AlertDialogExample(
            onDismissRequest = {
                alertMonthlyHistory.value = false
                viewModel.resetUiStateMonthlyHistory()
            },
            onConfirmation = {
                alertMonthlyHistory.value = false
                viewModel.resetUiStateMonthlyHistory()
            },
            dialogTitle = "Alert",
            dialogText = "Error: ${customError.value}",
        )
    }

    if(alertDailyHistory.value) {
        AlertDialogExample(
            onDismissRequest = {
                alertDailyHistory.value = false
                viewModel.resetUiStateDailyHistory()
            },
            onConfirmation = {
                alertDailyHistory.value = false
                viewModel.resetUiStateDailyHistory()
            },
            dialogTitle = "Alert",
            dialogText = "Error: ${customError.value}",
        )
    }
}