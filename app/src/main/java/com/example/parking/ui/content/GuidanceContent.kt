package com.example.parking.ui.content

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.RadioButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.parking.R
import com.example.parking.ui.component.BillText
import com.example.parking.ui.component.CardCamera
import com.example.parking.ui.component.CardDeposit
import com.example.parking.ui.component.CardDepositCash
import com.example.parking.ui.component.CashText
import com.example.parking.ui.component.CustomIcon
import com.example.parking.ui.component.DailyPayment
import com.example.parking.ui.component.HeadLineGuidance
import com.example.parking.ui.component.PaymentQrText
import com.example.parking.ui.component.PaymentText
import com.example.parking.ui.component.UserPayment
import com.example.parking.ui.component.VisitorsList
import com.example.parking.ui.theme.BluePark
import com.example.parking.ui.theme.GreyShadow

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun GuidanceScreenContent(
    modifier: Modifier = Modifier,
    phone: String = "kosong",
    onRefresh: () -> Unit = {}
) {
    val isKarcis = remember {
        mutableStateOf(true)
    }

    val refreshing = remember {
        mutableStateOf(false)
    }

    val status = remember {
        mutableStateOf(0)
    }

    val pullRefreshState = rememberPullRefreshState(
        refreshing = refreshing.value,
        onRefresh = {
            if (status.value == 2) {
                status.value = 0
            } else {
                status.value++
            }

            onRefresh()
        }
    )

    Scaffold(
        topBar = {
            Row(
                horizontalArrangement = Arrangement.Absolute.SpaceBetween,
                modifier = modifier
                    .border(
                        (2).dp,
                        brush = Brush.verticalGradient(listOf(Color.Transparent, GreyShadow)),
                        shape = RoundedCornerShape(10.dp)
                    )
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                HeadLineGuidance(modifier = modifier)
            }
        },
        bottomBar = {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier
                    .fillMaxWidth(),
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = modifier
                        .background(if (isKarcis.value) BluePark else Color.White)
                        .shadow(elevation = 0.6.dp)
                        .weight(1f)
                        .height(100.dp)
                        .clickable { isKarcis.value = true }
                ) {
                    CustomIcon(
                        color = if (isKarcis.value) Color.White else Color.Black,
                        IconVector = ImageVector.vectorResource(id = R.drawable.icon_karcis),
                        isOutlined = false,
                        modifier = Modifier
                            .size(44.dp),
                        effect = {
                            isKarcis.value = true
                        }
                    )

                    Text(
                        text = "Karcis",
                        color = if (isKarcis.value) Color.White else Color.Black,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.W300,
                        modifier = Modifier
                            .clickable { isKarcis.value = true }
                    )
                }

                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = modifier
                        .background(if (!isKarcis.value) BluePark else Color.White)
                        .border(
                            brush = Brush.verticalGradient(
                                listOf(
                                    GreyShadow,
                                    Color.Transparent
                                ), startY = 1f, endY = 10f
                            ), width = 2.dp, shape = RoundedCornerShape(0.dp)
                        )
                        .height(100.dp)
                        .weight(1f)
                        .clickable { isKarcis.value = false }
                ) {
                    CustomIcon(
                        color = if (!isKarcis.value) Color.White else Color.Black,
                        IconVector = ImageVector.vectorResource(id = R.drawable.icon_dompet),
                        isOutlined = false,
                        modifier = Modifier
                            .size(38.dp),
                        effect = {
                            isKarcis.value = false
                        }
                    )

                    Text(
                        color = if (!isKarcis.value) Color.White else Color.Black,
                        text = "Pembayaran",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.W300,
                        modifier = Modifier
                            .clickable { isKarcis.value = false }
                    )
                }
            }
        },
        modifier = Modifier
            .fillMaxSize()
    ) { innerPadding ->
        Column(
            modifier = Modifier
        ) {

            if (isKarcis.value) {
                Box(
                    Modifier
                        .pullRefresh(pullRefreshState)
                        .verticalScroll(rememberScrollState())
                ) {
                    // Display data
                    CardCamera(
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize()
                            .padding(20.dp)
                    )
                    PullRefreshIndicator(
                        refreshing = refreshing.value,
                        pullRefreshState,
                        Modifier.align(Alignment.TopCenter)
                    )
                }
            } else {
                ButtonPayment(modifier = Modifier)
            }
        }
    }
}

@Composable
fun ButtonPayment(modifier: Modifier = Modifier, cornerRadius: Int = 16) {
    val isButton = remember { mutableStateOf(true) }

    val buttonShape: Shape = RoundedCornerShape(cornerRadius.dp)

    Column(modifier = modifier.padding()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, top = 16.dp, end = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = { isButton.value = true },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isButton.value) BluePark else Color.Gray
                ),
                shape = buttonShape,
                modifier = Modifier
                    .weight(1f)
                    .height(38.dp)
            ) {
                Text(
                    text = "Jumlah Setoran",
                    color = Color.White,
                    fontSize = 14.sp
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            Button(
                onClick = { isButton.value = false },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (!isButton.value) BluePark else Color.Gray
                ),
                shape = buttonShape,
                modifier = Modifier
                    .height(38.dp)
            ) {
                Text(
                    text = "Pembayaran Pengguna",
                    color = Color.White,
                    fontSize = 14.sp
                )
            }
        }

        if (isButton.value) {
            DailyPayment()
            CardDeposit()
            VisitorsList()
        } else {
            Column {
                DailyPayment()
                UserPayment(modifier = Modifier.height(170.dp))
                Text(
                    text = "Bulan ini",
                    style = MaterialTheme.typography.bodyLarge.copy(fontSize = 16.sp),
                    modifier = Modifier.padding(start = 16.dp)
                )
                VisitorsList()
            }
        }
    }
}

@Composable
fun PaymentNonTunai(modifier: Modifier = Modifier) {
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
                    IconButton(onClick = { /*TODO*/ }) {
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
                onClick = { },
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

@Composable
fun PaymentQr(modifier: Modifier = Modifier) {

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
                    IconButton(onClick = { /*TODO*/ }) {
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

@Composable
fun PaymentCash(modifier: Modifier = Modifier) {
    val buttonShape: Shape = RoundedCornerShape(100.dp)

    Scaffold(
        topBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = { /*TODO*/ }) {
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
            CashText()
            CardDepositCash()
            Button(
                onClick = { },
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
                    text = "Konfirmasi Pembayaran",
                    color = Color.White,
                    fontSize = 14.sp
                )
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
private fun ButtonPaymentPreview() {
    ButtonPayment(modifier = Modifier)
}

@Preview(showBackground = true)
@Composable
fun GuidanceContentPreview() {
    GuidanceScreenContent()
}

@Preview(showBackground = true)
@Composable
fun PaymentNonTunaiPreview() {
    PaymentNonTunai()
}

@Preview(showBackground = true)
@Composable
fun PaymentQrPreview() {
    PaymentQr()
}

@Preview(showBackground = true)
@Composable
fun PaymentCashPreview() {
    PaymentCash()
}