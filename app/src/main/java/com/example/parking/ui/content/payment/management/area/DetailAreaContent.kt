package com.example.parking.ui.content.payment.management.area

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.parking.R
import com.example.parking.ui.component.MonthlyStatistics
import com.example.parking.ui.component.SliderStatistics

@Composable
fun DetailManagementAreaContent(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
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
                    IconButton(onClick = onClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
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

@Preview
@Composable
private fun DetailmanagementAreaPreview() {
    DetailManagementAreaContent()
}