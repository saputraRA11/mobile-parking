package com.example.parking.ui.content.payment.management

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.parking.R
import com.example.parking.ui.component.CustomIcon
import com.example.parking.ui.component.HeadLineManagement
import com.example.parking.ui.component.MonthlyStatistics
import com.example.parking.ui.component.ParkirArea
import com.example.parking.ui.component.SliderStatistics
import com.example.parking.ui.screen.home.Area
import com.example.parking.ui.screen.payment.ManagamenetHistoryDto
import com.example.parking.ui.theme.BluePark
import com.example.parking.ui.theme.GreyShadow
import com.example.parking.ui.utils.TransformChartGraph

@Composable
fun PaymentManagementContent(
    modifier: Modifier = Modifier,
    homeClick: () -> Unit = {},
    detailAreaClick: (id:String) -> Unit = {},
    listArea: MutableList<Area> = mutableListOf(),
    counting: MutableState<ManagamenetHistoryDto> = mutableStateOf(ManagamenetHistoryDto())
) {
    Scaffold(
        topBar = {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = modifier
                    .border(
                        2.dp,
                        brush = Brush.verticalGradient(listOf(Color.Transparent, GreyShadow)),
                        shape = RoundedCornerShape(10.dp)
                    )
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                HeadLineManagement(modifier = modifier)
            }
        },
        bottomBar = {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier.fillMaxWidth(),
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = modifier
                        .background(Color.White)
                        .shadow(elevation = 0.6.dp)
                        .weight(1f)
                        .height(100.dp)
                        .clickable {
                            homeClick()
                        }
                ) {
                    CustomIcon(
                        color =  BluePark,
                        IconVector = ImageVector.vectorResource(id = R.drawable.icon_parkir),
                        isOutlined = false,
                        modifier = Modifier.size(44.dp),
                    )
                    Text(
                        text = "Area Parkir",
                        color =  BluePark,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.W300,
                    )
                }
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = modifier
                        .background(BluePark)
                        .border(
                            brush = Brush.verticalGradient(
                                listOf(GreyShadow, Color.Transparent),
                                startY = 1f,
                                endY = 10f
                            ),
                            width = 2.dp,
                            shape = RoundedCornerShape(0.dp)
                        )
                        .height(100.dp)
                        .weight(1f)
                ) {
                    CustomIcon(
                        color = Color.White ,
                        IconVector = ImageVector.vectorResource(id = R.drawable.icon_laporan),
                        isOutlined = false,
                        modifier = Modifier.size(44.dp),
                    )
                    Text(
                        color = Color.White,
                        text = "Laporan",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.W300,
                    )
                }
            }
        },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 30.dp)
            ) {
                MonthlyStatistics(income = counting.value.totalIncome.value, user = counting.value.totalUser.value)
                TransformChartGraph(
                    column = counting.value.months,
                    dataset = counting.value.totals
                )
                ParkirArea(
                    onClick = detailAreaClick,
                    listArea = listArea
                )
            }
        }
    }
}

@Preview
@Composable
private fun PaymentManagementPreview() {
    PaymentManagementContent()
}