package com.example.parking.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.FloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.parking.ui.theme.BluePark

@Composable
fun FabMenu(
    modifier: Modifier = Modifier,
    listGuard: ()-> Unit = {},
    addArea: ()-> Unit = {},
    addGuard: ()-> Unit = {}
) {
    var expanded by remember { mutableStateOf(false) }

    Box(
        modifier = modifier
            .padding(25.dp),
    ) {
        Column(
            horizontalAlignment = Alignment.End
        ) {
            if (expanded) {
                FloatingActionButtonMenuItem(
                    "Daftar Penjaga",
                    Icons.Default.List,
                    onClick = { listGuard() })
                FloatingActionButtonMenuItem(
                    "Tambah Penjaga",
                    Icons.Default.Person,
                    onClick = { addGuard() })
                FloatingActionButtonMenuItem(
                    "Tambah Area",
                    Icons.Default.Home,
                    onClick = { addArea() })
            }
            FloatingActionButton(
                onClick = { expanded = !expanded },
                shape = RoundedCornerShape(16.dp),
                containerColor = BluePark,
            ) {
                Icon(
                    imageVector = if (expanded) Icons.Default.Close else Icons.Default.Add,
                    contentDescription = null,
                    tint = Color.White
                )
            }
        }
    }
}

@Composable
fun FloatingActionButtonMenuItem(text: String, icon: ImageVector, onClick: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(vertical = 8.dp)
            .clickable(onClick = onClick)
    ) {
        Text(
            text = text,
            fontSize = 16.sp,
            modifier = Modifier
                .background(Color.LightGray, shape = CircleShape)
                .padding(8.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier
                .background(BluePark, shape = CircleShape)
                .padding(8.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun FabMenuPreview() {
    FabMenu(modifier = Modifier)
}