package com.example.parking.ui.content.home.management.area

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.parking.R
import com.example.parking.ui.navigation.Screen
import com.example.parking.ui.screen.form.area.UpdateParkingAreaScreen

@Composable
fun UpdateAreaContent(
    backClick: () -> Unit = {},
    submitClick: () -> Unit = {},
    detailGuardClick: () -> Unit = {},
){
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .background(Color(0xFF1565C0))
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                IconButton(onClick = backClick) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.White,
                        modifier = Modifier.size(24.dp)
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                IconButton(onClick = submitClick) {
                    Icon(
                        painter = painterResource(id = R.drawable.check_circle),
                        contentDescription = "Next",
                        tint = Color.White,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Box(
                    modifier = Modifier.size(100.dp),
                    contentAlignment = Alignment.BottomEnd
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.profile),
                        contentDescription = "Profile Picture",
                        modifier = Modifier
                            .size(100.dp)
                            .background(Color.Gray, shape = CircleShape)
                    )
                    IconButton(
                        onClick = { /* Handle camera action */ },
                        modifier = Modifier
                            .size(32.dp)
                            .background(Color(0xFF1565C0), shape = CircleShape)
                            .align(Alignment.BottomEnd)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.add_a_photo),
                            contentDescription = "Camera Picture",
                            tint = Color.White,
                            modifier = Modifier.size(26.dp)
                        )
                    }
                }
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text("Alamat", style = MaterialTheme.typography.bodyLarge, color = Color.Black)
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Jalan Bojongsoang Nomor 1, Kecamatan Sukapura, Kabupaten Bandung, Jawa Barat",
                    color = Color.Black,
                    modifier = Modifier.weight(1f)
                )
                IconButton(onClick = { /* Handle edit action */ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.edit),
                        contentDescription = "Edit",
                        tint = Color.Black,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }
            Divider(color = Color.Black, modifier = Modifier.padding(vertical = 8.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Penjaga", color = Color.Black, modifier = Modifier.weight(1f))
                IconButton(onClick = detailGuardClick) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add",
                        tint = Color.Black,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }
            Divider(color = Color.Black, modifier = Modifier.padding(vertical = 3.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Ujey", color = Color.Black, modifier = Modifier.weight(1f))
                IconButton(onClick = { /* Handle add action */ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.remove),
                        contentDescription = "Add",
                        tint = Color.Black,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }

            Text(
                "Biaya Parkir",
                style = MaterialTheme.typography.bodyLarge,
                color = Color.Black,
                modifier = Modifier.padding(top = 16.dp)
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Mobil", modifier = Modifier.weight(1f), color = Color.Black)
                Text("Rp5000", color = Color.Black)
                IconButton(onClick = { /* Handle edit action */ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.edit),
                        contentDescription = "Edit",
                        tint = Color.Black,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Motor", modifier = Modifier.weight(1f), color = Color.Black)
                Text("Rp2000", color = Color.Black)
                IconButton(onClick = { /* Handle edit action */ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.edit),
                        contentDescription = "Edit",
                        tint = Color.Black,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun UpdateParkingAreaPreview() {
    UpdateAreaContent()
}