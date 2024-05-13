package com.example.parking.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.example.parking.R
import com.example.parking.ui.theme.BluePark
import com.example.parking.ui.theme.DarkBlue

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CardImage(
    modifier: Modifier = Modifier
){
    var expanded by remember { mutableStateOf(false) }
    val icon = if(expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown
    Column (
        modifier = modifier
            .clip(RoundedCornerShape(15.dp))
            .fillMaxSize()
    ){
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = modifier
                .background(DarkBlue)
                .fillMaxWidth()
                .padding(40.dp)
        ) {
            Text(
                text = "Karcis Digital",
                color = Color.White,
                fontSize = 30.sp,
                fontWeight = FontWeight.W600
            )
            Text(
                text = "Tampilkan kode ini ke aplikasi pemindai",
                color = Color.White,
                fontWeight = FontWeight.W300
            )
        }

        Column(
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = modifier
                    .background(BluePark)
                    .fillMaxWidth()
                    .weight(4f)
        ) {
            CustomImage(
                id = R.drawable.qrcode,
                modifier = Modifier
                    .padding(top = 20.dp)
                    .size(250.dp)
                    .weight(4f)
            )

            Row (
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = modifier
                    .padding(top = 20.dp)
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
                horizontalArrangement = Arrangement.spacedBy(60.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp),
                maxItemsInEachRow = 2,
                modifier = modifier
                    .padding(20.dp)
            ){
                Column(
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    modifier = modifier
                        .weight(1f)
                ) {
                    Text(
                        text = "Status Karcis",
                        fontSize = 22.sp,
                        color = Color.White,
                        fontWeight = FontWeight.W600
                    )
                    Text(
                        text = "Tidak Aktif" ,
                        fontSize = 14.sp,
                        color = Color.White,
                        fontWeight = FontWeight.W300
                    )
                }

                Column(
                    modifier = modifier
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
                        backgroundColor = ButtonDefaults.buttonColors(containerColor = Color.White, contentColor = BluePark),
                        isOutlined = false,
                        modifier = modifier
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
                        fontSize = 17
                    )
                }

                Column(
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    modifier = modifier
                        .weight(1f)
                ) {
                    Text(
                        text = "Lokasi",
                        fontSize = 22.sp,
                        color = Color.White,
                        fontWeight = FontWeight.W600
                    )
                    Text(
                        text = "Bojongsoang" ,
                        fontSize = 14.sp,
                        color = Color.White,
                        fontWeight = FontWeight.W300
                    )
                }
                Column(
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    modifier = modifier
                        .weight(1f)
                ) {
                    Text(
                        text = "Pemindai",
                        fontSize = 22.sp,
                        color = Color.White,
                        fontWeight = FontWeight.W600
                    )
                    Text(
                        text = "Kasir Bojongsoang" ,
                        fontSize = 14.sp,
                        color = Color.White,
                        fontWeight = FontWeight.W300
                    )
                }
            }

            ButtonCircle(
                onClick = {},
                text = "Karcis Belum Aktif",
                backgroundColor = ButtonDefaults.buttonColors(containerColor = Color.White, contentColor = BluePark),
                isOutlined = false,
                modifier = modifier
                    .padding(bottom = 20.dp, start = 20.dp, end = 20.dp)
                    .fillMaxWidth(),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CardImagePreview(){
    CardImage()
}