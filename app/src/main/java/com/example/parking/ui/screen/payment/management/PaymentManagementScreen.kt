package com.example.parking.ui.screen.payment.management

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.parking.data.remote.response.Auth.DataValidation
import com.example.parking.di.Injection
import com.example.parking.ui.common.UiState
import com.example.parking.ui.component.AlertDialogExample
import com.example.parking.ui.content.home.management.HomeManagementContent
import com.example.parking.ui.content.payment.management.PaymentManagementContent
import com.example.parking.ui.navigation.Screen
import com.example.parking.ui.screen.home.Area
import com.example.parking.ui.screen.payment.ManagamenetHistoryDto
import com.example.parking.ui.screen.payment.PaymentViewModel
import com.example.parking.ui.screen.payment.transformMonths
import com.example.parking.ui.screen.payment.transformPrice
import com.example.parking.ui.utils.ViewModelFactory
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PaymentManagementScreen(
    navController: NavHostController,
    viewModel: PaymentViewModel = viewModel(
        factory = ViewModelFactory(Injection, LocalContext.current)
    )
){
    LaunchedEffect(Unit) {
        viewModel.authenticationUser()
    }

    val coroutineScope = rememberCoroutineScope()

    val dataUserLocal = remember {
        mutableStateOf(DataValidation())
    }

    val counting = remember {
        mutableStateOf(ManagamenetHistoryDto())
    }

    val dataAreaLocal = remember {
        mutableListOf<Area>()
    }

    val alertArea = remember { mutableStateOf(false) }
    val alertUser = remember { mutableStateOf(false) }
    val alertCalculation = remember { mutableStateOf(false) }
    val alertChart = remember { mutableStateOf(false) }
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
                val userId = dataUserLocal.value.user?.id.toString()
                dataUserLocal.value = Json.decodeFromString(uiState.data.toString())
                viewModel.getAreaByOwner(userId)
                viewModel.getCalculationMonthly(userId)
                viewModel.getMonthlyChart(userId)
            }
            is UiState.Loading -> {}
        }
    }

    viewModel.uiStateaAreaOwner.collectAsState(initial = UiState.Loading).value.let {
            uiState ->
        when (uiState) {
            is UiState.Error -> {
                alertArea.value = true
                customError.value = uiState.errorMessage
            }
            is UiState.Success -> {
                dataAreaLocal.clear()
                uiState.data?.data?.map {
                        area ->
                    dataAreaLocal.add(
                        Area(
                            id = area?.id.toString(),
                            name = area?.areaName.toString()
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
                if(uiState.data?.data?.sumAll !== null){
                    counting.value.totalIncome.value = uiState.data.data.sumAll
                    counting.value.totalUser.value = uiState.data.data.totalHistory!!
                }
            }
            is UiState.Loading -> {}
        }
    }

    viewModel.uiStateMontly.collectAsState(initial = UiState.Loading).value.let {
            uiState ->
        when (uiState) {
            is UiState.Error -> {
                alertChart.value = true
                customError.value = uiState.errorMessage
            }
            is UiState.Success -> {
                val result = uiState.data?.data
                counting.value.months.value = transformMonths(result?.toMutableList()).value
                counting.value.totals.value = transformPrice(result?.toMutableList()).value
            }
            is UiState.Loading -> {}
        }
    }

    PaymentManagementContent(
        homeClick = {
            navController.navigateUp()
        },
        detailAreaClick = {
            id ->
            coroutineScope.launch {
                viewModel.saveParkingHistoryId(id)
            }
            navController.navigate(Screen.Form.createRoute("area","detail"))
        },
        listArea = dataAreaLocal,
        counting = counting
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

    if(alertArea.value) {
        AlertDialogExample(
            onDismissRequest = {
                alertArea.value = false
                viewModel.resetUiStateAreaByOwner()
            },
            onConfirmation = {
                alertArea.value = false
                viewModel.resetUiStateAreaByOwner()
            },
            dialogTitle = "Alert",
            dialogText = "Error: ${customError.value}",
        )
    }

    if(alertCalculation.value) {
        AlertDialogExample(
            onDismissRequest = {
                alertCalculation.value = false
                viewModel.resetUiStateCalculation()
            },
            onConfirmation = {
                alertCalculation.value = false
                viewModel.resetUiStateCalculation()
            },
            dialogTitle = "Alert",
            dialogText = "Error: ${customError.value}",
        )
    }
}