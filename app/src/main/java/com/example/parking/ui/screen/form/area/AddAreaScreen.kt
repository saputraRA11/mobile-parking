package com.example.parking.ui.screen.form.area

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
import com.example.parking.ui.content.payment.management.area.AddAreaContent
import com.example.parking.ui.screen.form.AddAreaForm
import com.example.parking.ui.screen.form.FormViewModel
import com.example.parking.ui.utils.ViewModelFactory
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json

@Composable
fun AddAreaFormScreen(
    navController: NavHostController = rememberNavController(),
    viewModel: FormViewModel = viewModel(
        factory = ViewModelFactory(Injection, LocalContext.current)
    ),
) {
    val coroutineScope = rememberCoroutineScope()
    val addAreaForm = remember {
        mutableStateOf(AddAreaForm())
    }
    val dataUserLocal = remember {
        mutableStateOf(DataValidation())
    }
    val alertUser = remember {
        mutableStateOf(false)
    }
    val alertForm = remember {
        mutableStateOf(false)
    }
    val isSuccess = remember {
        mutableStateOf(false)
    }
    val customError = remember {
        mutableStateOf("")
    }

    LaunchedEffect(key1 = true) {
        viewModel.authenticationUser()
    }

    LaunchedEffect(isSuccess.value) {
        if(isSuccess.value){
            navController.navigateUp()
        }
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

            else ->{}
        }
    }

    viewModel.uiStateParking.collectAsState(initial = UiState.Loading).value.let {
            uiState ->
        when (uiState) {
            is UiState.Error -> {
                alertForm.value = true
                customError.value = uiState.errorMessage
            }
            is UiState.Success -> {
                isSuccess.value = true
            }

            else ->{}
        }
    }

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

    if(alertForm.value) {
        AlertDialogExample(
            onDismissRequest = {
                alertForm.value = false
                viewModel.resetUiStateParking()
            },
            onConfirmation = {
                alertForm.value = false
                viewModel.resetUiStateParking()
            },
            dialogTitle = "Alert",
            dialogText = "Error: ${customError.value}",
        )
    }

    AddAreaContent(
        backClick = {
            navController.navigateUp()
        },
        submitClick = {
            coroutineScope.launch {
                addAreaForm.value.userId.value = dataUserLocal.value.user?.id.toString()
                viewModel.addParking(addAreaForm.value)
            }
        },
        addAreaForm = addAreaForm
    )

}