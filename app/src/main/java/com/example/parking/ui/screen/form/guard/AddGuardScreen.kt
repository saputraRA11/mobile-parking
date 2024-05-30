package com.example.parking.ui.screen.form.guard


import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.parking.ui.content.payment.management.guard.AddGuardContent
import com.example.parking.ui.screen.form.AddGuardForm

@Composable
fun AddGuardFormScreen(
    navController: NavHostController = rememberNavController(),
    phone: String = ""
) {
   val addGuardForm = remember {
       mutableStateOf(AddGuardForm())
   }
    AddGuardContent(
        backClick = {
            navController.navigateUp()
        },
        submitClick = {
            navController.navigateUp()
        },
        addGuardForm = addGuardForm
    )
}

@Preview(showBackground = true)
@Composable
fun AddGuardFormPreview() {
    AddGuardFormScreen()
}
