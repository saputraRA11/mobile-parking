package com.example.parking.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.parking.R
import java.text.NumberFormat
import java.util.Locale

@Composable
fun MonthlyStatistics(
    modifier: Modifier = Modifier,
    income: Int,
    user: Int
) {
    Column(modifier = modifier.padding(16.dp)) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Statistik Bulanan",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
            IconButton(onClick = { /* Handle next action */ }) {
                Icon(
                    painter = painterResource(id = R.drawable.tune),
                    contentDescription = "Next",
                    modifier = Modifier.size(24.dp)
                )
            }
        }
        Row(
            modifier = Modifier.padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Total pendapatan",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.weight(3f)
            )
            Text(
                text = "Rp${formatNumber(income)}",
                fontWeight = FontWeight.W500,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.weight(1f)
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Total pengguna",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.weight(3f)
            )
            Text(
                text = formatNumber(user),
                fontWeight = FontWeight.W500,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
fun WeeklyStatistics(
    modifier: Modifier = Modifier,
    income: Int,
    user: Int
) {
    Column(modifier = modifier.padding(16.dp)) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Statistik 7 Hari Terakhir",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
            IconButton(onClick = { /* Handle next action */ }) {
                Icon(
                    painter = painterResource(id = R.drawable.tune),
                    contentDescription = "Next",
                    modifier = Modifier.size(24.dp)
                )
            }
        }
        Row(
            modifier = Modifier.padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Total pendapatan",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.weight(3f)
            )
            Text(
                text = "Rp${formatNumber(income)}",
                fontWeight = FontWeight.W500,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.weight(1f)
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Total pengguna",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.weight(3f)
            )
            Text(
                text = formatNumber(user),
                fontWeight = FontWeight.W500,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
fun DailyPayment(modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(start = 16.dp)) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Hari ini",
                style = MaterialTheme.typography.bodyLarge.copy(fontSize = 16.sp),
                modifier = Modifier.align(Alignment.CenterVertically)
            )
            IconButton(onClick = { /* Handle next action */ }) {
                Icon(
                    painter = painterResource(id = R.drawable.tune),
                    contentDescription = "Next",
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    }
}


fun formatNumber(number: Int): String {
    val format = NumberFormat.getInstance(Locale("id", "ID"))
    return format.format(number)
}


@Preview(showBackground = true)
@Composable
fun MonthlyStatisticsPreview() {
    MonthlyStatistics(modifier = Modifier, income = 100000, user = 30)
}

@Preview(showBackground = true)
@Composable
fun WeeklyStatisticsPreview() {
    WeeklyStatistics(modifier = Modifier, income = 70000, user = 10)
}

@Preview(showBackground = true)
@Composable
private fun DailyPaymentPreview() {
    DailyPayment()
}