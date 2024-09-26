package com.example.parking.ui.screen.auth.pages

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
import com.example.parking.data.model.Auth.BodyValidation
import com.example.parking.data.remote.response.Auth.DataValidation
import com.example.parking.di.Injection
import com.example.parking.ui.common.UiState
import com.example.parking.ui.component.AlertDialogExample
import com.example.parking.ui.content.auth.ValidationContent
import com.example.parking.ui.navigation.Screen
import com.example.parking.ui.screen.auth.AuthViewModel
import com.example.parking.ui.utils.ViewModelFactory
import com.example.parking.ui.utils.convertRoleV2
import kotlinx.coroutines.launch
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Composable
fun ValidationScreen(
    navController: NavHostController = rememberNavController(),
    viewModel: AuthViewModel = viewModel(
        factory = ViewModelFactory(Injection, LocalContext.current)
    )
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
    val phoneNumber = remember {
        mutableStateOf("")
    }
    val isSuccess = remember {
        mutableStateOf(false)
    }
    val authorized = remember {
        mutableStateOf("")
    }
    LaunchedEffect(key1 = Unit) {
        viewModel.getPhone()

    }

    LaunchedEffect(isSuccess.value) {
        if(isSuccess.value) {
            navController.navigate(Screen.Home.createRoute(authorized.value))
        }
    }


    // for local storage
    viewModel.uiStatePhone.collectAsState(initial = UiState.Loading).value.let {
            uiState ->
        when (uiState) {
            is UiState.Success -> {
                coroutineScope.launch {
                    phoneNumber.value = uiState.data.toString()
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
                    Log.d("debug validation","${uiState.data?.data}")
                    val dataUser = Json.encodeToString(uiState.data?.data)
                    viewModel.saveUser(dataUser)
                    authorized.value = convertRoleV2(uiState.data?.data?.user?.role.toString())
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

    // for user auth
    LaunchedEffect(true) {
        viewModel.authenticationUser()
    }
    val isAuth = remember {
        mutableStateOf(false)
    }
    val dataUserLocal = remember {
        mutableStateOf(DataValidation())
    }
//    LaunchedEffect(isAuth.value) {
//        if(isAuth.value){
//            navController.navigate(Screen.Home.createRoute(convertRoleV2(dataUserLocal.value.user?.role.toString())))
//        }
//    }

    viewModel.uiStateUser.collectAsState(initial = UiState.Loading).value.let {
            uiState ->
        when (uiState) {
            is UiState.Success -> {

                Log.d("Debug User Id in validation","${uiState.data}")
                if(uiState.data != "") {

                    dataUserLocal.value = Json.decodeFromString(uiState.data.toString())
                    isAuth.value=true
                }
            }
            else ->{}
        }
    }
    // ======

    ValidationContent(
        effect = {
            navController.navigateUp()
        },
        onClick = {
            coroutineScope.launch {
                if(otpState.value == "") {
                    viewModel.setErrorValidation("Tolong inputkan otp!")
                } else {
                    viewModel.validationOtp(
                        BodyValidation(
                            phoneNumber.value,
                            otpState.value.toInt()
                        ))
                }
            }
        },
        sendClick = {
            coroutineScope.launch{
                viewModel.sendOtp(phone = phoneNumber.value)
            }
        },
        otpState = otpState
    )
}