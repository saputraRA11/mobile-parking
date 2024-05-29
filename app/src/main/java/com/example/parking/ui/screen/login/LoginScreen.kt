package com.example.parking.ui.screen.login

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
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.parking.di.Injection
import com.example.parking.ui.common.UiState
import com.example.parking.ui.component.AlertDialogExample
import com.example.parking.ui.component.ButtonCircle
import com.example.parking.ui.content.LoginContent
import com.example.parking.ui.navigation.Screen
import com.example.parking.ui.screen.validation.OtpData
import com.example.parking.ui.theme.BluePark
import com.example.parking.ui.utils.ViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Composable
fun LoginScreen(
    navController: NavHostController = rememberNavController(),
    viewModel: LoginViewModel = viewModel(
        factory = ViewModelFactory(Injection, LocalContext.current)
    )
){
    val phoneNumber = remember {
        mutableStateOf(TextFieldValue(""))
    }

    val coroutineScope = rememberCoroutineScope()
    val dataOtpLocal = OtpData(
        phone = phoneNumber.value.text,
        path = "login"
    )

    val alertLogin = remember {
        mutableStateOf(false)
    }

    val customError = remember {
        mutableStateOf("")
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
                                    navController.navigate(Screen.Validation.createRoute("login"))
                                },
                                text = "Confirm",
                                backgroundColor = ButtonDefaults.buttonColors(containerColor = BluePark)
                            )
                        }
                    }
                }
            }

            is UiState.Error -> {
                alertLogin.value = true
                customError.value = uiState.errorMessage
            }
            else ->{}
        }
    }

    LoginContent(
        effect = {
            navController.navigateUp()
        },
        loginClick = {
            coroutineScope.launch{
                viewModel.sendOtp(phoneNumber.value.text)
            }
        },
        registerClick = {
            navController.navigate(Screen.Register.route)
        },
        phoneNumber = phoneNumber
    )

    if(alertLogin.value) {
        AlertDialogExample(
            onDismissRequest = {
                if(alertLogin.value) {
                    alertLogin.value = false
                    viewModel.resetUiStateOtp()
                } else {
                    alertLogin.value = false
                    viewModel.resetUiStateOtp()
                }

            },
            onConfirmation = {
                if(alertLogin.value) {
                    alertLogin.value = false
                    viewModel.resetUiStateOtp()
                } else {
                    alertLogin.value = false
                    viewModel.resetUiStateOtp()
                }
            },
            dialogTitle = "Alert",
            dialogText = "Error: ${customError.value}",
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LoginPreview(){
    LoginScreen()
}
