package com.example.parking.ui.screen.register

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.parking.di.Injection
import com.example.parking.ui.common.UiState
import com.example.parking.ui.component.AlertDialogExample
import com.example.parking.ui.component.ButtonCircle
import com.example.parking.ui.content.RegisterContent
import com.example.parking.ui.navigation.Screen
import com.example.parking.ui.screen.validation.OtpData
import com.example.parking.ui.theme.BluePark
import com.example.parking.ui.utils.ViewModelFactory
import kotlinx.coroutines.launch
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Composable
fun RegisterScreen(
    navController: NavHostController = rememberNavController(),
    viewModel: RegisterViewModel = viewModel(
        factory = ViewModelFactory(Injection, LocalContext.current)
    ),
) {
    val formUserUi = remember {
        mutableStateOf(FormRegister())
    }

    val coroutineScope = rememberCoroutineScope()
    val alertRegister = remember { mutableStateOf(false) }
    val alertOtp = remember { mutableStateOf(false) }
    val customError = remember {
        mutableStateOf("")
    }
    val phoneNumber = remember {
        mutableStateOf("")
    }

    val dataOtpLocal = OtpData(
        phone = phoneNumber.value,
        path = "register"
    )

    // for register
    viewModel.uiStateRegister.collectAsState(initial = UiState.Loading).value.let {
        uiState ->
        when (uiState) {
            is UiState.Success -> {
                phoneNumber.value = uiState.data?.data?.phoneNumber.toString()
                viewModel.resetUiStateRegister()
                viewModel.sendOtp(phone = phoneNumber.value)
            }

            is UiState.Error -> {
                alertRegister.value = true
                customError.value = uiState.errorMessage
            }
            else ->{}
        }
    }

    // for otp
    viewModel.uiStateOtp.collectAsState(initial = UiState.Loading).value.let {
            uiState ->
        when (uiState) {
            is UiState.Success -> {
                Dialog(onDismissRequest = {}) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(400.dp)
                            .padding(16.dp),
                        shape = RoundedCornerShape(16.dp),
                    ) {
                        Column(
                            verticalArrangement = Arrangement.spacedBy(20.dp,Alignment.CenterVertically),
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(20.dp)
                        ) {
                            Text(
                                text = "${uiState.data?.data?.message}" ?: "",
                                modifier = Modifier
                                    .fillMaxWidth(),
                                textAlign = TextAlign.Center,
                            )

                            ButtonCircle(
                                modifier = Modifier.fillMaxWidth(),
                                onClick = {
                                    coroutineScope.launch {
                                        viewModel.savePhone(Json.encodeToString(dataOtpLocal))
                                    }
                                    viewModel.resetUiStateOtp()
                                    navController.navigate(Screen.Validation.createRoute(dataOtpLocal.path))
                                },
                                text = "Confirm",
                                backgroundColor = ButtonDefaults.buttonColors(containerColor = BluePark)
                            )
                        }
                    }
                }
            }

            is UiState.Error -> {
                alertRegister.value = true
                customError.value = uiState.errorMessage
            }
            else ->{}
        }
    }



    if(alertRegister.value || alertOtp.value) {
        AlertDialogExample(
            onDismissRequest = {
                if(alertRegister.value) {
                    alertRegister.value = false
                    viewModel.resetUiStateRegister()
                } else {
                    alertOtp.value = false
                    viewModel.resetUiStateOtp()
                }

            },
            onConfirmation = {
                if(alertRegister.value) {
                    alertRegister.value = false
                    viewModel.resetUiStateRegister()
                } else {
                    alertOtp.value = false
                    viewModel.resetUiStateOtp()
                }
            },
            dialogTitle = "Alert",
            dialogText = "Error: ${customError.value}",
        )
    }

    RegisterContent(
        effect = {
            navController.navigateUp()
        },
        registerClick = {
            coroutineScope.launch {
                viewModel.registerUsers(formUserUi.value)
            }
        },
        loginClick = {
            navController.navigate(Screen.Login.route)
        },
        formUserUi = formUserUi
    )
}

