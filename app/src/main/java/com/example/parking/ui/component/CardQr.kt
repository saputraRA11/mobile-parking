package com.example.parking.ui.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.example.parking.R
import com.example.parking.ui.theme.BluePark
import com.example.parking.ui.theme.DarkBlue
import com.example.parking.ui.utils.QrCodeGenerator

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CardImage(
    modifier: Modifier = Modifier,
    phone: String = "kosong",
    status:MutableState<Int> = remember {
        mutableStateOf(0)
    }
){
    var expanded by remember { mutableStateOf(false) }
    val icon = if(expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown

    // 0 -> non aktif, 1 konfirmasi , 2 karcis aktif

    Column (
        modifier = modifier
            .clip(RoundedCornerShape(20.dp))
    ){
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier
                .background(DarkBlue)
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            Text(
                text = "Karcis Digital",
                color = Color.White,
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.W600
            )
            Text(
                text = "Tampilkan kode ini ke aplikasi pemindai",
                color = Color.White,
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.W300
            )
        }

        Column(
                verticalArrangement = Arrangement.spacedBy(30.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .background(BluePark)
                    .fillMaxSize()
                    .padding(vertical = 10.dp)
        ) {
            CustomImage(
                painter = QrCodeGenerator(content = phone),
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .fillMaxHeight(0.5f)
                ,
                description = "QRCODE"
            )

            Row (
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                // circle
                Canvas(
                    modifier = Modifier
                        .size(1.dp)
                        .zIndex(2f)
                ) {
                    drawCircle(Color.White, radius = 50f, center = Offset(size.width / 1f, size.height / 2f))
                }

                // line
                Canvas(
                    modifier = Modifier
                        .fillMaxWidth()
                        .zIndex(1f)
                ) {
                    val pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 10f)

                    // ujung ke ujung
                    drawLine(
                        Color.LightGray,
                        Offset(0f,0f),
                        Offset(size.width,0f),
                        pathEffect = pathEffect,
                        strokeWidth = 5f
                    )

                }

                // circle
                Canvas(
                    modifier = Modifier
                        .size(1.dp)
                        .zIndex(2f)
                ) {
                    drawCircle(Color.White, radius = 50f, center = Offset(size.width / 1f, size.height / 2f))
                }

            }

            FlowRow (
                horizontalArrangement = Arrangement.spacedBy(20.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp),
                maxItemsInEachRow = 2,
                modifier = Modifier
                    .padding(horizontal = 20.dp)
            ){
                Column(
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    modifier = Modifier
                        .weight(1f)
                ) {
                    Text(
                        text = "Status Karcis",
                        color = Color.White,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.W600
                    )
                    Text(
                        text = if(status.value == 0) "Tidak Aktif" else if(status.value == 1) "Konfirmasi" else "Aktif",
                        color = Color.White,
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.W300
                    )
                }

                if(status.value != 0) {
                    Column(
                        modifier = Modifier
                            .weight(1f)
                    ) {
                        Text(
                            text = "Pembayaran",
                            fontSize = 22.sp,
                            color = Color.White,
                            fontWeight = FontWeight.W600
                        )
                        ButtonCircle(
                            onClick = {},
                            text = "Tunai",
                            textAlign = Arrangement.SpaceBetween,
                            backgroundColor = ButtonDefaults.buttonColors(containerColor = Color.White, contentColor = DarkBlue),
                            isOutlined = false,
                            modifier = Modifier
                                .fillMaxWidth(),
                            trailingIcon = {
                                CustomIcon(
                                    color = Color.Black,
                                    IconVector = icon,
                                    isOutlined = true,
                                    modifier = Modifier
                                        .clickable {
                                            expanded = !expanded
                                        },
                                    borderSize = 2.dp,
                                )
                            },
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight.W400
                        )
                    }

                    Column(
                        verticalArrangement = Arrangement.spacedBy(10.dp),
                        modifier = Modifier
                            .weight(1f)
                    ) {
                        Text(
                            text = "Lokasi",
                            color = Color.White,
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.W600
                        )
                        Text(
                            text = "Bojongsoang" ,
                            color = Color.White,
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight.W300
                        )
                    }
                    Column(
                        verticalArrangement = Arrangement.spacedBy(10.dp),
                        modifier = Modifier
                            .weight(1f)
                    ) {
                        Text(
                            text = "Kendaraan",
                            color = Color.White,
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.W600
                        )

                        ButtonCircle(
                            onClick = {},
                            text = "Mobil",
                            textAlign = Arrangement.SpaceBetween,
                            backgroundColor = ButtonDefaults.buttonColors(containerColor = Color.White, contentColor = DarkBlue),
                            isOutlined = false,
                            modifier = Modifier
                                .fillMaxWidth(),
                            trailingIcon = {
                                CustomIcon(
                                    color = Color.Black,
                                    IconVector = icon,
                                    isOutlined = true,
                                    modifier = Modifier
                                        .clickable {
                                            expanded = !expanded
                                        },
                                    borderSize = 2.dp,
                                )
                            },
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight.W400
                        )
                    }
                }

            }

            when(status.value) {
                0 -> {
                    ButtonCircle(
                        onClick = {},
                        text = "Karcis Belum Aktif",
                        backgroundColor = ButtonDefaults.buttonColors(containerColor = Color.White, contentColor = BluePark),
                        isOutlined = false,
                        modifier = Modifier
                            .padding(horizontal = 20.dp)
                            .fillMaxWidth(),
                        enable = false
                    )
                }
                1 -> {
                    ButtonCircle(
                        onClick = {},
                        text = "Konfirmasi",
                        backgroundColor = ButtonDefaults.buttonColors(containerColor = Color.White, contentColor = BluePark),
                        isOutlined = false,
                        modifier = Modifier
                            .padding(horizontal = 20.dp)
                            .fillMaxWidth(),
                    )
                }
                2 -> {
                    ButtonCircle(
                        onClick = {},
                        text = "Karcis Aktif",
                        backgroundColor = ButtonDefaults.buttonColors(containerColor = Color.White, contentColor = BluePark),
                        isOutlined = false,
                        modifier = Modifier
                            .padding(horizontal = 20.dp)
                            .fillMaxWidth(),
                        enable = false
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CardImagePreview(){
    CardImage()
}