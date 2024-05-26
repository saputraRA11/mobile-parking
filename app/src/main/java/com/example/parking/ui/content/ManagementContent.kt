package com.example.parking.ui.content

import android.os.Build
import androidx.annotation.RequiresApi
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
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.RadioButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.parking.R
import com.example.parking.ui.component.CashPayment
import com.example.parking.ui.component.CustomIcon
import com.example.parking.ui.component.DatePicker
import com.example.parking.ui.component.DatePickerRange
import com.example.parking.ui.component.FabMenu
import com.example.parking.ui.component.GuardList
import com.example.parking.ui.component.HeadLineManagement
import com.example.parking.ui.component.MonthlyStatistics
import com.example.parking.ui.component.ParkirArea
import com.example.parking.ui.component.SliderStatistics
import com.example.parking.ui.component.WeeklyStatistics
import com.example.parking.ui.navigation.Screen
import com.example.parking.ui.theme.BluePark
import com.example.parking.ui.theme.GreyShadow
import java.time.LocalDate

@Composable
fun Management(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    val parkirArea = remember { mutableStateOf(true) }

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
                modifier = modifier.fillMaxWidth()
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
        if (parkirArea.value) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(bottom = 30.dp),
                ) {
                    CashPayment()
                    ParkirArea(
                        navController = navController,
                    )
                }
                FabMenu(
                    modifier = Modifier.align(Alignment.BottomEnd),
                    listGuard = {
                        navController.navigate(Screen.GuardList.createRoute("08"))
                    },
                    addGuard = {
                        navController.navigate(Screen.AddGuard.createRoute("08"))
                    },
                    addArea = {
                        navController.navigate(Screen.AddArea.createRoute("08"))
                    }
                )
            }
        } else {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                ) {
                    MonthlyStatistics(
                        income = 100000,
                        user = 30,
                    )
                    SliderStatistics()
                    ParkirArea(
                        navController = navController,
                    )
                }
            }
        }
    }
}


@Composable
fun ManagementStatistics(
    modifier: Modifier = Modifier,
) {
    val parkirArea = remember {
        mutableStateOf(true)
    }

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
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 30.dp)
            ) {
                MonthlyStatistics(income = 100000, user = 30)
                SliderStatistics()
                ParkirArea()
            }
        }
    }
}

@Composable
fun DetailManagementPlaceStatistics(
    modifier: Modifier = Modifier,
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
                    verticalAlignment = Alignment.CenterVertically // Align children vertically
                ) {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White,
                            modifier = Modifier.size(53.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(85.dp)) // Add spacing between icon and text
                    Text(
                        text = "Area Parkir",
                        color = Color.White,
                        fontSize = 18.sp, // Increase text size
                        fontWeight = FontWeight.Bold, // Add font weight
                        modifier = Modifier.align(Alignment.CenterVertically)
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
                    .padding(bottom = 5.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(vertical = 6.dp)
                ) {
                    Text(
                        text = "Bojongsoang",
                        fontSize = 28.sp,
                        color = Color.Black,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Image(
                        painter = painterResource(id = R.drawable.profile),
                        contentDescription = "profile",
                        modifier = Modifier
                            .size(130.dp)
                            .align(Alignment.CenterHorizontally)
                    )
                }
                MonthlyStatistics(income = 100000, user = 30)
                SliderStatistics()
            }

        }
    }
}

@Composable
fun ManagementGuard(
    modifier: Modifier = Modifier,
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
                    IconButton(onClick = { /*TODO*/ }) {
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

@Composable
fun WeeklyManagementPlaceStatistics(
    modifier: Modifier = Modifier,
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
                    verticalAlignment = Alignment.CenterVertically // Align children vertically
                ) {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White,
                            modifier = Modifier.size(53.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(85.dp)) // Add spacing between icon and text
                    Text(
                        text = "Area Parkir",
                        color = Color.White,
                        fontSize = 18.sp, // Increase text size
                        fontWeight = FontWeight.Bold, // Add font weight
                        modifier = Modifier.align(Alignment.CenterVertically)
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
                    .padding(bottom = 5.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(vertical = 6.dp)
                ) {
                    Text(
                        text = "Bojongsoang",
                        fontSize = 28.sp,
                        color = Color.Black,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Image(
                        painter = painterResource(id = R.drawable.profile),
                        contentDescription = "profile",
                        modifier = Modifier
                            .size(130.dp)
                            .align(Alignment.CenterHorizontally)
                    )
                }
                WeeklyStatistics(income = 70000, user = 10)
            }

        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TimeSpanStatistics(
    modifier: Modifier = Modifier,
) {
    val options = listOf("7 Hari Terakhir", "Pilih Bulan", "Pilih Tanggal")
    var selectedOption by remember { mutableStateOf(options[0]) }
    var showCalendar by remember { mutableStateOf(false) }
    var showCalendarRange by remember { mutableStateOf(false) }
    val selectedDatesRange = remember { mutableStateOf<List<LocalDate>>(listOf()) }
    val selectedDate = remember { mutableStateOf<LocalDate?>(null) }
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
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White,
                            modifier = Modifier.size(53.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(85.dp))
                    Text(
                        text = "Rentang Waktu",
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.align(Alignment.CenterVertically)
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
                    .padding(16.dp),
                verticalArrangement = Arrangement.Top
            ) {
                options.forEach { text ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                            .selectable(
                                selected = (text == selectedOption),
                                onClick = {
                                    selectedOption = text
                                    showCalendar = text == "Pilih Bulan"
                                    showCalendarRange = text == "Pilih Tanggal"
                                }
                            ),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = text,
                            style = MaterialTheme.typography.body1,
                            modifier = Modifier.weight(1f)
                        )
                        RadioButton(
                            selected = (text == selectedOption),
                            onClick = {
                                selectedOption = text
                                showCalendar = text == "Pilih Bulan"
                                showCalendarRange = text == "Pilih Tanggal"
                            }
                        )
                    }
                    Divider()
                }
                if (showCalendar) {
                    DatePicker(
                        selectedDate = selectedDate,
                        showDialog = showCalendar,
                        closeSelection = {showCalendar = false}
                    )
                }
                if (showCalendarRange) {
                    DatePickerRange(
                        showDialog = showCalendarRange,
                        selectedDates = selectedDatesRange,
                        closeSelection = { showCalendarRange = false }
                    )
                }

            }
        }
    }
}


@Preview
@Composable
private fun ManagementGuardPreview() {
    ManagementGuard()
}

@Preview
@Composable
private fun ManagementPreview() {
    Management()
}

@Preview
@Composable
private fun ManagementStatisticsPreview() {
    ManagementStatistics()
}

@Preview
@Composable
private fun ManagementPlaceStatisticsPreview() {
    DetailManagementPlaceStatistics()
}

@Preview
@Composable
private fun WeeklyManagementPlaceStatisticsPreview() {
    WeeklyManagementPlaceStatistics()
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
private fun TimeSpanStatisticsPreview() {
    TimeSpanStatistics()
}