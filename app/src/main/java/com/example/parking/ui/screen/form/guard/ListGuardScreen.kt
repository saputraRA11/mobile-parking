package com.example.parking.ui.screen.form.guard

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
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
import com.example.parking.ui.content.payment.management.guard.DetailGuardListContent
import com.example.parking.ui.screen.form.AssignGuard
import com.example.parking.ui.screen.form.FormViewModel
import com.example.parking.ui.screen.form.UpdateAreaDto
import com.example.parking.ui.screen.form.transformUserKeeperGlobal
import com.example.parking.ui.screen.form.transformUserKeeperStranger
import com.example.parking.ui.utils.ViewModelFactory
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json

@Composable
fun ListGuardScreen(
    navController: NavHostController = rememberNavController(),
    viewModel: FormViewModel = viewModel(
        factory = ViewModelFactory(Injection, LocalContext.current)
    )
) {
    LaunchedEffect(true) {
        viewModel.authenticationUser()
    }
    val dataUserLocal = remember {
        mutableStateOf(DataValidation())
    }
    val listGuard = remember {
        mutableStateOf(mutableListOf(""))
    }
    val coroutineScope = rememberCoroutineScope()

    val customError = remember {
        mutableStateOf("")
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

    DetailGuardListContent(
        onClick = {
            navController.navigateUp()
        },
        listGuard = listGuard
    )

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
                    viewModel.getKeeperOwnerByOwner(dataUserLocal.value.user?.id.toString())
                }
            }
            is UiState.Loading -> {}
        }
    }


    viewModel.uiStateGetUserGlobal.collectAsState(initial = UiState.Loading).value.let {
            uiState ->
        when (uiState) {
            is UiState.Error -> {
                alertGetUser.value = true
                customError.value = uiState.errorMessage
            }
            is UiState.Success -> {
                listGuard.value = transformUserKeeperGlobal(uiState.data?.data?.user).value
            }
            is UiState.Loading -> {}
        }
    }
}