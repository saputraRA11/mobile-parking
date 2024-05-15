package com.example.parking.ui.screen.home

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.parking.di.Injection
import com.example.parking.ui.common.UiState
import com.example.parking.ui.content.HomeScreenContent
import com.example.parking.ui.utils.ViewModelFactory

@Composable
fun HomeScreen(
    navController: NavHostController = rememberNavController(),
    viewModel: HomeViewModel = viewModel(
        factory = ViewModelFactory(Injection, LocalContext.current)
    ),
    phone:String = ""
){
    LaunchedEffect(key1 = true) {
        viewModel.getUsers(phone)
    }

    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let {
        uiState ->

        when (uiState) {
            is UiState.Loading -> {
                Text(text = "Loading")
            }

            is UiState.Success -> {
                uiState.data.let {
                    user ->
                    when(user?.role?.name) {
                        "PELANGGAN" -> {
                            HomeScreenContent(
                                isCustomer = true,
                                phone = user.phone
                            )
                        }

                        "PENJAGA" -> {
                            HomeScreenContent(
                                isCustomer = false,
                                phone = user.phone
                            )
                        }
                    }
                }
            } else ->{
                Text(text = "error")
            }
        }
    }
}