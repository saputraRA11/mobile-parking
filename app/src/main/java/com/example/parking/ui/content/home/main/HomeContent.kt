package com.example.parking.ui.content.home.main

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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
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
import com.example.parking.data.remote.response.Auth.DataValidation
import com.example.parking.ui.component.CardImage
import com.example.parking.ui.component.CustomIcon
import com.example.parking.ui.component.HeadLineUser
import com.example.parking.ui.screen.home.UpdateParkingHistoryDto
import com.example.parking.ui.theme.BluePark
import com.example.parking.ui.theme.GreyShadow

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeScreenContent(
    modifier: Modifier = Modifier,
    userDataLocal:MutableState<DataValidation> = mutableStateOf(DataValidation()),
    dataForm:MutableState<UpdateParkingHistoryDto> = mutableStateOf(UpdateParkingHistoryDto()),
    onRefresh: () -> Unit = {},
    paymentClick: () -> Unit = {} ,
    onSubmit: () -> Unit = {}
) {
    val refreshing = remember {
        mutableStateOf(false)
    }

    val status = remember {
        mutableStateOf(0)
    }

    val pullRefreshState = rememberPullRefreshState(
        refreshing = refreshing.value,
        onRefresh = {
           if(status.value == 2) {
                status.value = 0
           } else {
               status.value++
           }

            onRefresh()
        }
    )

    Scaffold (
        topBar = {
            Row (
                horizontalArrangement = Arrangement.Absolute.SpaceBetween,
                modifier = modifier
                    .border(
                        (2).dp,
                        brush = Brush.verticalGradient(listOf(Color.Transparent, GreyShadow)),
                        shape = RoundedCornerShape(10.dp)
                    )
                    .fillMaxWidth()
                    .padding(20.dp)
            ){
                HeadLineUser(modifier = modifier)
            }
        },
        bottomBar = {
            Row (
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier
                    .fillMaxWidth(),
            ){
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = modifier
                        .background( BluePark )
                        .shadow(elevation = 0.6.dp)
                        .weight(1f)
                        .height(100.dp)
                ) {
                    CustomIcon(
                        color = Color.White,
                        IconVector = ImageVector.vectorResource(id = R.drawable.icon_karcis),
                        isOutlined = false,
                        modifier = Modifier
                            .size(44.dp),
                    )

                    Text(
                        text = "Karcis",
                        color =Color.White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.W300,
                        modifier = Modifier
                    )
                }

                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = modifier
                        .background(Color.White)
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
                        .clickable {
                            paymentClick()
                        }
                ) {
                    CustomIcon(
                        color =  Color.Black,
                        IconVector = ImageVector.vectorResource(id = R.drawable.icon_dompet),
                        isOutlined = false,
                        modifier = Modifier
                            .size(38.dp),
                    )

                    Text(
                        color =  Color.Black,
                        text = "Pembayaran",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.W300,
                        modifier = Modifier
                    )
                }
            }
        },
        modifier = Modifier
            .fillMaxSize()
    ){
            innerPadding ->
        Column(
            modifier = Modifier
        ) {
                Box(
                    Modifier
                        .pullRefresh(pullRefreshState)
                        .verticalScroll(rememberScrollState())
                ) {
                    // Display data
                    CardImage(
                        userId = userDataLocal.value.user?.id.toString(),
                        dataForm = dataForm,
                        onSubmit = onSubmit,
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
        }
    }
}


@Preview(showBackground = true)
@Composable
fun HomeCustomerContentPreview(){
    HomeScreenContent()
}
