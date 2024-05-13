package com.example.parking.ui.screen.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.LinearGradient
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.parking.R
import com.example.parking.ui.component.CardImage
import com.example.parking.ui.component.CustomIcon
import com.example.parking.ui.component.ImageParkName
import com.example.parking.ui.component.ProfileImage
import com.example.parking.ui.theme.BluePark
import com.example.parking.ui.theme.GreyShadow
import kotlin.math.round

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier
){
    val stateColor = remember {
        mutableStateOf(false)
    }

    Scaffold (
        topBar = {
             Row (
                 horizontalArrangement = Arrangement.SpaceBetween,
                 modifier = modifier
                     .border(
                         (2).dp,
                         brush = Brush.verticalGradient(listOf(Color.Transparent, GreyShadow)),
                         shape = RoundedCornerShape(15.dp)
                     )
                     .fillMaxWidth()
                 ){
                 ImageParkName(
                     modifier = modifier
                         .padding(20.dp)
                 )
                 ProfileImage(
                     size = 56,
                     modifier = modifier
                         .padding(20.dp)
                 )
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
                        .background(if (!stateColor.value) BluePark else Color.White)
                        .shadow(elevation = 0.6.dp)
                        .weight(1f)
                        .height(100.dp)
                        .clickable { stateColor.value = false }
                ) {
                    CustomIcon(
                        color = if(!stateColor.value) Color.White else Color.Black,
                        IconVector = ImageVector.vectorResource(id = R.drawable.icon_karcis),
                        isOutlined = false,
                        modifier = Modifier.size(44.dp)
                    )

                    Text(
                        text = "Karcis",
                        color = if(!stateColor.value) Color.White else Color.Black,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.W300
                    )
                }

                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = modifier
                        .background(if (stateColor.value) BluePark else Color.White)
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
                        .clickable { stateColor.value = true }
                ) {
                    CustomIcon(
                        color = if(!stateColor.value) Color.Black else Color.White,
                        IconVector = ImageVector.vectorResource(id = R.drawable.icon_dompet),
                        isOutlined = false,
                        modifier = Modifier
                            .size(38.dp)
                    )

                    Text(
                        color = if(stateColor.value) Color.White else Color.Black,
                        text = "Pembayaran",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.W300
                    )
                }
            }
        },
        modifier = modifier
            .fillMaxSize()
    ){
        innerPadding ->
        Column(
            modifier = modifier
                .padding(innerPadding)
                .padding(15.dp)
                .border(0.dp,Color.Transparent)
                .fillMaxSize()
        ) {
            CardImage(
                modifier = modifier
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview(){
    HomeScreen()
}