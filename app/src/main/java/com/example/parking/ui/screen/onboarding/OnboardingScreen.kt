package com.example.parking.ui.screen.onboarding

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.parking.data.remote.response.Auth.DataValidation
import com.example.parking.di.Injection
import com.example.parking.ui.common.UiState
import com.example.parking.ui.component.ButtonCircle
import com.example.parking.ui.component.ImageParkName
import com.example.parking.ui.component.SliderOnboarding
import com.example.parking.ui.navigation.Screen
import com.example.parking.ui.screen.auth.AuthViewModel
import com.example.parking.ui.theme.BluePark
import com.example.parking.ui.utils.ViewModelFactory
import com.example.parking.ui.utils.convertRoleV2
import kotlinx.serialization.json.Json

@Composable
fun OnboardingScreen(
    navController: NavHostController = rememberNavController(),
    modifier:Modifier = Modifier,
    viewModel: AuthViewModel = viewModel(
        factory = ViewModelFactory(Injection, LocalContext.current)
    )
) {
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
                if(uiState.data != "") {
                    dataUserLocal.value = Json.decodeFromString(uiState.data.toString())
                    isAuth.value=true
                }
            }
            else ->{}
        }
    }
    // ======

    Scaffold(
        modifier = modifier
            .fillMaxSize()
            ,
        topBar = {
            Column(
                modifier = modifier
                .padding(20.dp)
            ) {
                ImageParkName()
            }
        },
        bottomBar = {
            Column(
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth()
            ) {
                ButtonCircle(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        navController.navigate(Screen.Auth.createRoute("login"))
                    },
                    text = "Masuk",
                    backgroundColor = ButtonDefaults.buttonColors(containerColor = BluePark)
                )

                ButtonCircle(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        navController.navigate(Screen.Auth.createRoute("register"))
                    },
                    text = "Pengguna Baru? Buat akun",
                    backgroundColor = ButtonDefaults.buttonColors(containerColor = Color.White, contentColor = BluePark),
                    isOutlined = true,
                    border = BorderStroke(1.dp, BluePark)
                )
            }
        }
    ) {
        innerPadding ->
        Column (
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            SliderOnboarding(modifier = modifier)
        }
    }
}

@Preview(
    showBackground = true
)
@Composable
fun OnboardingPreview() {
    OnboardingScreen()
}