package com.example.parking.ui.screen.form.guard

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
import com.example.parking.ui.content.payment.management.guard.AssignGuardListContent
import com.example.parking.ui.screen.form.AssignGuard
import com.example.parking.ui.screen.form.FormViewModel
import com.example.parking.ui.screen.form.UpdateAreaDto
import com.example.parking.ui.screen.form.transformUserKeeperStranger
import com.example.parking.ui.utils.ViewModelFactory
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json

@Composable
fun AssignListGuardScreen(
    navController: NavHostController = rememberNavController(),
    viewModel: FormViewModel = viewModel(
        factory = ViewModelFactory(Injection, LocalContext.current)
    )
) {

   val coroutineScope = rememberCoroutineScope()
   val guardListState = remember {
        mutableStateOf(mutableListOf(AssignGuard()))
    }

    val updateAreaDto = remember {
        mutableStateOf(UpdateAreaDto())
    }

    val customError = remember {
        mutableStateOf("")
    }

    val alertDataUpdate = remember {
        mutableStateOf(false)
    }

    val alertGetUser = remember {
        mutableStateOf(false)
    }

    val alertUser = remember {
        mutableStateOf(false)
    }

    val isSuccess = remember {
        mutableStateOf(false)
    }

    LaunchedEffect(true) {
    }

    LaunchedEffect(Unit) {
        viewModel.authenticationUser()
        viewModel.getParkingData()
    }

    val dataUserLocal = remember {
        mutableStateOf(DataValidation())
    }

    viewModel.uiStateParkingUpdate.collectAsState(initial = UiState.Loading).value.let {
            uiState ->
        when (uiState) {
            is UiState.Error -> {
                alertDataUpdate.value = true
                customError.value = uiState.errorMessage
            }
            is UiState.Success -> {
                updateAreaDto.value = Json.decodeFromString(uiState.data.toString())
            }
            is UiState.Loading -> {}
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
                coroutineScope.launch {
                    viewModel.getKeeperUserByOwner(dataUserLocal.value.user?.id.toString())
                }
            }
            is UiState.Loading -> {}
        }
    }


    viewModel.uiStateGetUser.collectAsState(initial = UiState.Loading).value.let {
            uiState ->
        when (uiState) {
            is UiState.Error -> {
                alertGetUser.value = true
                customError.value = uiState.errorMessage
            }
            is UiState.Success -> {
                guardListState.value = transformUserKeeperStranger(uiState.data?.data?.user).value
            }
            is UiState.Loading -> {}
        }
    }

    AssignGuardListContent(
        onBack = {
            navController.navigateUp()
        },
        listGuard = guardListState,
//        onCheck = {
//                index,id,status ->
//            if(status){
//                // add data
//            } else {
//                // removve data
//            }
//        }
    )

    if(alertDataUpdate.value) {
        AlertDialogExample(
            onDismissRequest = {
                alertDataUpdate.value = false
                viewModel.resetUiStateParkingUpdate()
            },
            onConfirmation = {
                alertDataUpdate.value = false
                viewModel.resetUiStateParkingUpdate()
            },
            dialogTitle = "Alert",
            dialogText = "Error: ${customError.value}",
        )
    }

    if(alertGetUser.value) {
        AlertDialogExample(
            onDismissRequest = {
                alertGetUser.value = false
                viewModel.resetUiStateGetUser()
            },
            onConfirmation = {
                alertGetUser.value = false
                viewModel.resetUiStateGetUser()
            },
            dialogTitle = "Alert",
            dialogText = "Error: ${customError.value}",
        )
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
}