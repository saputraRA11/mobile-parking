package com.example.parking.ui.screen.validation

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
import com.example.parking.data.model.Auth.BodyValidation
import com.example.parking.di.Injection
import com.example.parking.ui.common.UiState
import com.example.parking.ui.component.AlertDialogExample
import com.example.parking.ui.content.ValidationContent
import com.example.parking.ui.navigation.Screen
import com.example.parking.ui.utils.ViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Composable
fun ValidationScreen(
    navController: NavHostController = rememberNavController(),
    viewModel: ValidationViewModel = viewModel(
        factory = ViewModelFactory(Injection, LocalContext.current)
    ),
    path:String = "login",
){
    val coroutineScope = rememberCoroutineScope()
    val otpState = remember {
        mutableStateOf("")
    }
    val alertPhone = remember { mutableStateOf(false) }
    val alertValidation = remember { mutableStateOf(false) }
    val customError = remember {
        mutableStateOf("")
    }
    val dataOtpLocal = remember {
        mutableStateOf(OtpData("82211843505","login"))
    }
    val isSuccess = remember {
        mutableStateOf(false)
    }
    LaunchedEffect(key1 = Unit) {
        viewModel.getPhone()

    }

    LaunchedEffect(isSuccess.value) {
        if(isSuccess.value) {
            navController.navigate(Screen.Home.route)
        }
    }


    // for local storage
    viewModel.uiStatePhone.collectAsState(initial = UiState.Loading).value.let {
            uiState ->
        when (uiState) {
            is UiState.Success -> {
                coroutineScope.launch {
                    dataOtpLocal.value = Json.decodeFromString(uiState.data.toString())
                }
            }

            is UiState.Error -> {
                alertPhone.value = true
                customError.value = uiState.errorMessage
            }
            else ->{}
        }
    }

    // for validation
    viewModel.uiStateValidation.collectAsState(initial = UiState.Loading).value.let {
            uiState ->
        when (uiState) {
            is UiState.Success -> {
                coroutineScope.launch {
                    val dataUser = Json.encodeToString(uiState.data?.data)
                    viewModel.saveLocal(dataUser)
                    isSuccess.value = true
                }

            }

            is UiState.Error -> {
                alertValidation.value = true
                customError.value = uiState.errorMessage
            }
            else ->{}
        }
    }

    if(alertPhone.value || alertValidation.value) {
        AlertDialogExample(
            onDismissRequest = {
                if(alertPhone.value) {
                    alertPhone.value = false
                    viewModel.resetUiStatePhone()
                    navController.navigateUp()
                } else {
                    alertValidation.value = false
                    viewModel.resetUiStateValidation()
                }
            },
            onConfirmation = {
                if(alertPhone.value) {
                    alertPhone.value = false
                    viewModel.resetUiStatePhone()
                    navController.navigateUp()
                } else {
                    alertValidation.value = false
                    viewModel.resetUiStateValidation()
                }
            },
            dialogTitle = "Alert",
            dialogText = "Error: ${customError.value}",
        )
    }

    ValidationContent(
        effect = {
            navController.navigateUp()
        },
        onClick = {
            coroutineScope.launch {
                viewModel.validationOtp(
                    BodyValidation(
                        "62${dataOtpLocal.value.phone}",
                        otpState.value.toInt()
                    ),path)
            }
        },
        sendClick = {
            coroutineScope.launch{
                viewModel.sendOtp(phone = dataOtpLocal.value.phone)
            }
        },
        otpState = otpState
    )
}