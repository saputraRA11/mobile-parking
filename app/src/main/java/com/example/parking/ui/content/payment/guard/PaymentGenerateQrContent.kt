package com.example.parking.ui.content.payment.guard

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.parking.R
import com.example.parking.ui.component.BillText
import com.example.parking.ui.component.PaymentText
import com.example.parking.ui.theme.BluePark

@Composable
fun PaymentGenerateQrContent(
    modifier: Modifier = Modifier,
    backClick: () -> Unit = {},
    generateQr: () -> Unit = {}
) {
    val buttonShape: Shape = RoundedCornerShape(100.dp)

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
                            imageVector = Icons.Default.KeyboardArrowLeft,
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
            PaymentText(modifier = Modifier.padding(bottom = 16.dp))
            Image(
                painter = painterResource(id = R.drawable.onboarding_3),
                contentDescription = "",
                modifier = Modifier.padding(bottom = 16.dp)
            )
            BillText(modifier = Modifier.padding(bottom = 16.dp))
            Button(
                onClick = generateQr,
                colors = ButtonDefaults.buttonColors(
                    containerColor = BluePark
                ),
                shape = buttonShape,
                modifier = Modifier
                    .height(50.dp)
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp)
            ) {
                Text(
                    text = "Buat Kode",
                    color = Color.White,
                    fontSize = 14.sp
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PaymentGenerateQrContentPreview() {
    PaymentGenerateQrContent()
}