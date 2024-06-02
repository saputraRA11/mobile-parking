package com.example.parking.ui.screen.home.management

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
import androidx.navigation.compose.rememberNavController
import com.example.parking.data.remote.response.Auth.DataValidation
import com.example.parking.di.Injection
import com.example.parking.ui.common.UiState
import com.example.parking.ui.component.AlertDialogExample
import com.example.parking.ui.content.home.management.HomeManagementContent
import com.example.parking.ui.navigation.Screen
import com.example.parking.ui.screen.home.Area
import com.example.parking.ui.screen.home.HomeViewModel
import com.example.parking.ui.utils.ViewModelFactory
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json

@Composable
fun HomeManagementScreen(
    navController: NavHostController = rememberNavController(),
    viewModel: HomeViewModel = viewModel(
        factory = ViewModelFactory(Injection, LocalContext.current)
    ),
){
    LaunchedEffect(Unit) {
        viewModel.authenticationUser()
    }

    val coroutineScope = rememberCoroutineScope()

    val dataUserLocal = remember {
        mutableStateOf(DataValidation())
    }

    val dataAreaLocal = remember {
        mutableListOf<Area>()
    }

    val alertArea = remember { mutableStateOf(false) }
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
                dataUserLocal.value = Json.decodeFromString(uiState.data.toString())
                coroutineScope.launch {
                    viewModel.getAreaByOwner(dataUserLocal.value.user?.id.toString())
                }
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

    HomeManagementContent(
        listGuard = {
        navController.navigate(Screen.Form.createRoute("guard","list"))
        },
        addGuard = {
            navController.navigate(Screen.Form.createRoute("guard","add"))
        },
        addArea = {
            navController.navigate(Screen.Form.createRoute("area","add"))
        },
        paymentClick = {
            navController.navigate(Screen.Payment.createRoute("owner"))
        },
        updateArea = {
            id ->
            coroutineScope.launch {
                viewModel.saveParkingId(id)
            }

            navController.navigate(Screen.Form.createRoute("area","update"))
        },
        listArea = dataAreaLocal
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
}