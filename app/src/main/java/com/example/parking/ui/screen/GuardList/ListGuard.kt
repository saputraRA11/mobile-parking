package com.example.parking.ui.screen.GuardList

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.parking.R
import com.example.parking.ui.component.CustomIcon
import com.example.parking.ui.component.GuardList
import com.example.parking.ui.theme.BluePark
import com.example.parking.ui.theme.GreyShadow

@Composable
fun ManagementGuardScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    phone: String = ""
) {
    val parkirArea = remember {
        mutableStateOf(true)
    }

    Scaffold(
        topBar = {
            Column(
                modifier = Modifier
                    .background(Color(0xFF1565C0))
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White,
                            modifier = Modifier.size(53.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(85.dp))
                    Text(
                        text = "Daftar Penjaga",
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.align(Alignment.CenterVertically)
                    )
                }
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
                        .background(if (parkirArea.value) BluePark else Color.White)
                        .shadow(elevation = 0.6.dp)
                        .weight(1f)
                        .height(100.dp)
                        .clickable { parkirArea.value = true }
                ) {
                    CustomIcon(
                        color = if (parkirArea.value) Color.White else BluePark,
                        IconVector = ImageVector.vectorResource(id = R.drawable.icon_parkir),
                        isOutlined = false,
                        modifier = Modifier.size(44.dp),
                        effect = {
                            parkirArea.value = true
                        }
                    )
                    Text(
                        text = "Area Parkir",
                        color = if (parkirArea.value) Color.White else BluePark,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.W300,
                        modifier = Modifier.clickable { parkirArea.value = true }
                    )
                }
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = modifier
                        .background(if (!parkirArea.value) BluePark else Color.White)
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
                        .clickable { parkirArea.value = false }
                ) {
                    CustomIcon(
                        color = if (!parkirArea.value) Color.White else BluePark,
                        IconVector = ImageVector.vectorResource(id = R.drawable.icon_laporan),
                        isOutlined = false,
                        modifier = Modifier.size(44.dp),
                        effect = {
                            parkirArea.value = false
                        }
                    )
                    Text(
                        color = if (!parkirArea.value) Color.White else BluePark,
                        text = "Laporan",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.W300,
                        modifier = Modifier.clickable { parkirArea.value = false }
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
            GuardList()

        }
    }
}