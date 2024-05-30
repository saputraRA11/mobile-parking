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
import com.example.parking.ui.screen.home.HomeViewModel
import com.example.parking.ui.utils.ViewModelFactory
import kotlinx.serialization.json.Json

@Composable
fun HomeScreen(
    navController: NavHostController = rememberNavController(),
    viewModel: HomeViewModel = viewModel(
        factory = ViewModelFactory(Injection, LocalContext.current)
    ),
){
    val checkUpdate = remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        if(!checkUpdate.value){
            viewModel.authenticationUser()
            checkUpdate.value = true
        }
    }

    val dataUserLocal = remember {
        mutableStateOf(DataValidation())
    }
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
            }
            is UiState.Loading -> {}
        }
    }


    HomeScreenContent(
        paymentClick = {
            navController.navigate(Screen.Payment.createRoute("user"))
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
}