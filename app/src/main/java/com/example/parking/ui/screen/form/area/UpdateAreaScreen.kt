package com.example.parking.ui.screen.form.area

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.parking.R
import com.example.parking.di.Injection
import com.example.parking.ui.common.UiState
import com.example.parking.ui.component.AlertDialogExample
import com.example.parking.ui.content.home.management.area.UpdateAreaContent
import com.example.parking.ui.navigation.Screen
import com.example.parking.ui.screen.form.FormViewModel
import com.example.parking.ui.screen.form.GuardIdentity
import com.example.parking.ui.screen.form.UpdateAreaFormDto
import com.example.parking.ui.screen.form.clearForm
import com.example.parking.ui.screen.form.deleteItemKeeper
import com.example.parking.ui.screen.form.transformKeeperItem
import com.example.parking.ui.screen.form.transformUpdateAreaDto
import com.example.parking.ui.screen.home.Area
import com.example.parking.ui.utils.ViewModelFactory
import kotlinx.coroutines.launch
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Composable
fun UpdateParkingAreaScreen(
    navController: NavHostController = rememberNavController(),
    viewModel: FormViewModel = viewModel(
        factory = ViewModelFactory(Injection, LocalContext.current)
    )
) {
    val updateAreaFormDto = remember {
        mutableStateOf(UpdateAreaFormDto())
    }
    val customError = remember {
        mutableStateOf("")
    }

    val isSuccess = remember {
        mutableStateOf(false)
    }

    val coroutineScope = rememberCoroutineScope()
    val alertDetailArea = remember { mutableStateOf(false) }
    val alertParkingId = remember { mutableStateOf(false) }
    val alertUpdateArea = remember { mutableStateOf(false) }

    LaunchedEffect(true) {
        viewModel.getParkingId()
    }

    LaunchedEffect(isSuccess.value) {
        if(isSuccess.value){
            isSuccess.value = false
            updateAreaFormDto.value = clearForm()
            viewModel.resetUiStateUpdateArea()
            navController.navigateUp()
        }
    }

    viewModel.uiStateUpdateArea.collectAsState(initial = UiState.Loading).value.let {
            uiState ->
        when (uiState) {
            is UiState.Error -> {
                alertUpdateArea.value = true
                customError.value = uiState.errorMessage
            }
            is UiState.Success -> {
                isSuccess.value = true
            }
            is UiState.Loading -> {}
        }
    }

    viewModel.uiStateParkingId.collectAsState(initial = UiState.Loading).value.let {
            uiState ->
        when (uiState) {
            is UiState.Error -> {
                alertParkingId.value = true
                customError.value = uiState.errorMessage
            }
            is UiState.Success -> {
                viewModel.getAreaById(uiState.data.toString())
            }
            is UiState.Loading -> {}
        }
    }

    viewModel.uiStateDetailArea.collectAsState(initial = UiState.Loading).value.let {
            uiState ->
        when (uiState) {
            is UiState.Error -> {
                alertDetailArea.value = true
                customError.value = uiState.errorMessage
            }
            is UiState.Success -> {
                val result = uiState.data?.data
                updateAreaFormDto.value.id.value= result?.id.toString()
                updateAreaFormDto.value.address.value= TextFieldValue(result?.address.toString())
                updateAreaFormDto.value.carPrice.value=TextFieldValue(result?.carCost.toString().replace(".0",""))
                updateAreaFormDto.value.motorPrice.value=TextFieldValue(result?.motorCost.toString().replace(".0",""))
                Log.d("debug json","${updateAreaFormDto.value}")
                Log.d("debug data keepers","${result?.keepers}")
                updateAreaFormDto.value.listGuard.value = transformKeeperItem(result?.keepers).value

            }
            is UiState.Loading -> {}
        }
    }

    UpdateAreaContent(
        backClick = {
            navController.navigateUp()
        },
        submitClick = {
            Log.d("debug json","${updateAreaFormDto.value}")
            viewModel.updateArea(formArea = transformUpdateAreaDto(updateAreaFormDto.value))
        },
        detailGuardClick = {
             coroutineScope.launch {
                 val updateParkingJson = Json.encodeToString(transformUpdateAreaDto(updateAreaFormDto.value))
                 Log.d("debug json",updateParkingJson)
                 viewModel.saveUpdateDataForm(updateParkingJson)
             }
            navController.navigate(Screen.Form.createRoute("guard","assign"))
        },
        updateAreaForm = updateAreaFormDto,
        onDelete = {
            index ->
            updateAreaFormDto.value.listGuard.value = deleteItemKeeper(updateAreaFormDto.value.listGuard,index).value
        }
    )

    if(alertDetailArea.value) {
        AlertDialogExample(
            onDismissRequest = {
                alertDetailArea.value = false
                viewModel.resetUiStateDetailArea()
            },
            onConfirmation = {
                alertDetailArea.value = false
                viewModel.resetUiStateDetailArea()
            },
            dialogTitle = "Alert",
            dialogText = "Error: ${customError.value}",
        )
    }

    if(alertParkingId.value) {
        AlertDialogExample(
            onDismissRequest = {
                alertParkingId.value = false
                viewModel.resetUiStateParkingId()
            },
            onConfirmation = {
                alertParkingId.value = false
                viewModel.resetUiStateParkingId()
            },
            dialogTitle = "Alert",
            dialogText = "Error: ${customError.value}",
        )
    }

    if(alertUpdateArea.value) {
        AlertDialogExample(
            onDismissRequest = {
                alertUpdateArea.value = false
                viewModel.resetUiStateUpdateArea()
            },
            onConfirmation = {
                alertUpdateArea.value = false
                viewModel.resetUiStateUpdateArea()
            },
            dialogTitle = "Alert",
            dialogText = "Error: ${customError.value}",
        )
    }
}
