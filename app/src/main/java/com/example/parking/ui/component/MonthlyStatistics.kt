package com.example.parking.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import java.text.NumberFormat
import java.util.Locale

@Composable
fun MonthlyStatistics(
    modifier: Modifier = Modifier,
    income: Int,
    user: Int
) {
    Column(modifier = modifier.padding(16.dp)) {
        Text(
            text = "Statistik Bulanan",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.W500
        )
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


fun formatNumber(number: Int): String {
    val format = NumberFormat.getInstance(Locale("id", "ID"))
    return format.format(number)
}


@Preview(showBackground = true)
@Composable
fun MonthlyStatisticsPreview() {
    MonthlyStatistics(modifier = Modifier, income = 100000, user = 30)
}