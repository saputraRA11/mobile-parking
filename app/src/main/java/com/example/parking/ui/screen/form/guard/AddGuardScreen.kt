package com.example.parking.ui.screen.form.guard


import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.parking.data.remote.response.Auth.DataValidation
import com.example.parking.di.Injection
import com.example.parking.ui.common.UiState
import com.example.parking.ui.component.AlertDialogExample
import com.example.parking.ui.content.payment.management.guard.AddGuardContent
import com.example.parking.ui.screen.form.AddGuardForm
import com.example.parking.ui.screen.form.FormViewModel
import com.example.parking.ui.screen.home.Area
import com.example.parking.ui.utils.ViewModelFactory
import kotlinx.serialization.json.Json

@Composable
fun AddGuardFormScreen(
    navController: NavHostController = rememberNavController(),
    viewModel: FormViewModel = viewModel(
        factory = ViewModelFactory(Injection, LocalContext.current)
    )
) {
    val dataAreaLocal = remember {
        mutableListOf<Area>()
    }

   val addGuardForm = remember {
       mutableStateOf(AddGuardForm())
   }

    val alertGuard = remember { mutableStateOf(false) }
    val alertArea = remember { mutableStateOf(false) }
    val customError = remember {
        mutableStateOf("")
    }
    val dataUserLocal = remember {
        mutableStateOf(DataValidation())
    }
    val alertUser = remember {
        mutableStateOf(false)
    }
    val isAuth = remember {
        mutableStateOf(false)
    }

    val isSuccess = remember {
        mutableStateOf(false)
    }

    viewModel.uiStateAreaOwner.collectAsState(initial = UiState.Loading).value.let {
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

    viewModel.uiStateAddGuard.collectAsState(initial = UiState.Loading).value.let {
            uiState ->
        when (uiState) {
            is UiState.Error -> {
                alertArea.value = true
                customError.value = uiState.errorMessage
            }
            is UiState.Success -> {
                isSuccess.value = true
            }
            is UiState.Loading -> {}
        }
    }

    if(alertArea.value) {
        AlertDialogExample(
            onDismissRequest = {
                alertArea.value = false
                viewModel.resetUiStateAreaOwner()
                navController.navigateUp()
            },
            onConfirmation = {
                alertArea.value = false
                viewModel.resetUiStateAreaOwner()
                navController.navigateUp()
            },
            dialogTitle = "Alert",
            dialogText = "Error: ${customError.value}",
        )
    }

    if(alertGuard.value) {
        AlertDialogExample(
            onDismissRequest = {
                alertGuard.value = false
                viewModel.resetUiStateAddGuard()
            },
            onConfirmation = {
                alertGuard.value = false
                viewModel.resetUiStateAddGuard()
            },
            dialogTitle = "Alert",
            dialogText = "Error: ${customError.value}",
        )
    }

    LaunchedEffect(isSuccess.value) {
        if(isSuccess.value){
            navController.navigateUp()
        }
    }

    LaunchedEffect(key1 = true) {
        viewModel.authenticationUser()
    }

    LaunchedEffect(isAuth.value) {
        if(isAuth.value){
            viewModel.getAreaByOwner(dataUserLocal.value.user?.id.toString())
        }
    }

    AddGuardContent(
        backClick = {
            navController.navigateUp()
        },
        submitClick = {
            viewModel.addGuard(addGuardForm.value)
        },
        addGuardForm = addGuardForm,
        listArea = dataAreaLocal
    )

    viewModel.uiStateUser.collectAsState(initial = UiState.Loading).value.let {
            uiState ->
        when (uiState) {
            is UiState.Error -> {
                alertUser.value = true
                customError.value = uiState.errorMessage
            }
            is UiState.Success -> {
                isAuth.value = true
                dataUserLocal.value = Json.decodeFromString(uiState.data.toString())
            }
            else ->{}
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AddGuardFormPreview() {
    AddGuardFormScreen()
}
