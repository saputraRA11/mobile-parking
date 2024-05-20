package com.example.parking.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.parking.R

@Composable
fun ParkirArea(
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier.padding(16.dp)) {
        Text(
            text = "Area Parkir",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.W500
        )
        RepeatableItemsRow(namePlace = "Bojongsoang", guard = 0)
        RepeatableItemsRow(namePlace = "Citra Land", guard = 0)
        RepeatableItemsRow(namePlace = "Jember Town Square", guard = 1)
    }
}

@Composable
fun RepeatableItemsRow(namePlace: String, guard: Int) {
    Row(modifier = Modifier.padding(vertical = 8.dp)) {
        Image(
            painter = painterResource(id = R.drawable.profile),
            contentDescription = "Profile Picture",
            modifier = Modifier.size(40.dp)
        )
        Spacer(modifier = Modifier.width(10.dp))
        Column {
            Text(
                text = namePlace,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.W500
            )
            Spacer(modifier = Modifier.height(4.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painterResource(id = R.drawable.supervisor_account),
                    contentDescription = "Guard Icon",
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = if (guard == 0) "Tidak ada penjaga" else "$guard Penjaga",
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ParkirAreaPreview() {
    ParkirArea()
}