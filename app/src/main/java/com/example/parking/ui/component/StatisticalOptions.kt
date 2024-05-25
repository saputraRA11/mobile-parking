package com.example.parking.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.parking.R

@Composable
fun StatisticalOptions(modifier: Modifier = Modifier) {
    Row(
        modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Statistik Bulanan",
            modifier.align(Alignment.CenterVertically)
        )
        IconButton(onClick = { /* Handle next action */ }) {
            Icon(
                painter = painterResource(id = R.drawable.tune),
                contentDescription = "Next",
                modifier.size(24.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun StatisticalOptionsPreview() {
    StatisticalOptions()
}