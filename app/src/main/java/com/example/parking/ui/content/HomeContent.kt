package com.example.parking.ui.content

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
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
import com.example.parking.ui.component.CardCamera
import com.example.parking.ui.component.CardImage
import com.example.parking.ui.component.CustomIcon
import com.example.parking.ui.component.ImageParkGuidance
import com.example.parking.ui.component.ImageParkName
import com.example.parking.ui.component.ProfileImage
import com.example.parking.ui.theme.BluePark
import com.example.parking.ui.theme.GreyShadow

@Composable
fun HomeScreenContent(
    modifier: Modifier = Modifier,
    isCustomer:Boolean = false,
    phone: String = "kosong"
) {
    val isKarcis = remember {
        mutableStateOf(true)
    }
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

                if(isCustomer) {
                    HeadLineUser(modifier = modifier)
                } else {
                    HeadLineGuidance(modifier = modifier)
                }
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
                        .background(if (isKarcis.value) BluePark else Color.White)
                        .shadow(elevation = 0.6.dp)
                        .weight(1f)
                        .height(100.dp)
                        .clickable { isKarcis.value = true }
                ) {
                    CustomIcon(
                        color = if(isKarcis.value) Color.White else Color.Black,
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
                        color = if(isKarcis.value) Color.White else Color.Black,
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
                        color = if(!isKarcis.value) Color.White else Color.Black,
                        IconVector = ImageVector.vectorResource(id = R.drawable.icon_dompet),
                        isOutlined = false,
                        modifier = Modifier
                            .size(38.dp),
                        effect = {
                            isKarcis.value = false
                        }
                    )

                    Text(
                        color = if(!isKarcis.value) Color.White else Color.Black,
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
    ){
            innerPadding ->
        Column(
            modifier = Modifier
        ) {
            if(isKarcis.value) {
                if(isCustomer) {
                    CardImage(
                        phone = phone,
                        modifier = Modifier
                            .verticalScroll(rememberScrollState())
                            .padding(innerPadding)
                            .fillMaxSize()
                            .padding(20.dp)
                    )
                } else {
                    CardCamera(
                        modifier = Modifier
                            .verticalScroll(rememberScrollState())
                            .padding(innerPadding)
                            .fillMaxSize()
                            .padding(20.dp)
                    )
                }
            }

        }
    }
}

@Composable
fun HeadLineUser(modifier: Modifier) {
    ImageParkName(
        modifier = modifier
            .fillMaxHeight(0.09f)
            .fillMaxWidth(0.8f)
        ,
    )
    ProfileImage(
        modifier = modifier
            .fillMaxHeight(0.09f)
            .fillMaxWidth(1f)
    )
}

@Composable
fun HeadLineGuidance(modifier: Modifier) {
    ImageParkGuidance(
        modifier = modifier
            .fillMaxHeight(0.09f)
            .fillMaxWidth(0.7f)
        ,
    )

    ProfileImage(
        modifier = modifier
            .fillMaxHeight(0.09f)
            .fillMaxWidth(0.7f)
    )
}
@Preview(showBackground = true)
@Composable
fun HomeCustomerContentPreview(){
    HomeScreenContent(
        isCustomer = true
    )
}
@Preview(showBackground = true)
@Composable
fun HomePenjagaContentPreview(){
    HomeScreenContent(
        isCustomer = false
    )
}