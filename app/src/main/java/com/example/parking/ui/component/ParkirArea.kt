package com.example.parking.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.parking.R
import com.example.parking.ui.screen.home.Area
import com.example.parking.ui.theme.BluePark

@Composable
fun ParkirArea(
    modifier: Modifier = Modifier,
    onClick:(id:String) -> Unit = {},
    listArea: MutableList<Area> = mutableListOf()
) {
    Column(modifier = modifier) {
        Text(
            text = "Area Parkir",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.W500,
            color = BluePark
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ) {
            listArea.map {
                area ->
                item {
                    RepeatableItemsRow(namePlace = area.name, guard = area.guardCount, effect = {
                        onClick(area.id)
                    })
                }
            }
        }
    }
}

@Composable
fun RepeatableItemsRow(
    namePlace: String,
    guard: Int,
    tint: androidx.compose.ui.graphics.Color = BluePark,
    effect: () -> Unit = {}
) {
    Row(modifier = Modifier
        .padding(vertical = 8.dp)
        .clickable { effect() }) {
        Image(
            painter = painterResource(id = R.drawable.profile),
            contentDescription = "Profile Picture",
            modifier = Modifier.size(60.dp) // Increased size
        )
        Spacer(modifier = Modifier.width(16.dp)) // Increased spacing
        Column {
            Text(
                text = namePlace,
                style = MaterialTheme.typography.bodyLarge.copy(fontSize = 20.sp), // Increased text size
                fontWeight = FontWeight.W500,
                color = BluePark
            )
            Spacer(modifier = Modifier.height(8.dp)) // Increased spacing
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.supervisor_account),
                    contentDescription = "Guard Icon",
                    modifier = Modifier.size(24.dp), // Increased size
                    colorFilter = ColorFilter.tint(tint)
                )
                Spacer(modifier = Modifier.width(8.dp)) // Increased spacing
                Text(
                    text = if (guard == 0) "Tidak ada penjaga" else "$guard Penjaga",
                    style = MaterialTheme.typography.bodySmall.copy(fontSize = 18.sp), // Increased text size
                    color = BluePark
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ParkirAreaPreview() {
    ParkirArea()
}
