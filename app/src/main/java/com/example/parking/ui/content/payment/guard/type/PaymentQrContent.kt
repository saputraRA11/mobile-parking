package com.example.parking.ui.content.payment.guard.type

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.parking.R
import com.example.parking.ui.component.BillText
import com.example.parking.ui.component.PaymentQrText

@Composable
fun PaymentQrContent(
    modifier: Modifier = Modifier,
    backClick:() -> Unit = {}
) {
    Scaffold(
        topBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = backClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                            contentDescription = "Back",
                            modifier = Modifier.size(53.dp)
                        )
                    }
                }
            }
        },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        Column(
            modifier = modifier
                .padding(innerPadding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            PaymentQrText(modifier = Modifier.padding(bottom = 16.dp))
            Image(
                painter = painterResource(id = R.drawable.barcodepayment),
                contentDescription = "",
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .size(250.dp)
            )
            BillText(modifier = Modifier.padding(bottom = 16.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PaymentQrPreview() {
    PaymentQrContent()
}