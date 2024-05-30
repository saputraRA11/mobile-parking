package com.example.parking.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.parking.R
import com.example.parking.ui.theme.BluePark

@Composable
fun VisitorsList(
    modifier: Modifier = Modifier,
    isMonthly: Boolean = false
) {
    Column(modifier = modifier) {
        if(isMonthly) {
            Text(
                text = "Bulan ini",
                style = MaterialTheme.typography.bodyLarge.copy(fontSize = 16.sp),
            )
        }
        LazyColumn {
            item {
                RepeatableItemRoww(
                    visitor = "Kang Seulgi",
                    date = "14 Mei 2024",
                    time = "20:00 - 21:00",
                    type = "TUNAI",
                    price = "5000"
                )
            }
            item {
                RepeatableItemRoww(
                    visitor = "Bojongsoang",
                    date = "14 Mei 2024",
                    time = "20:00 - 21:00",
                    type = "TUNAI",
                    price = "5000"
                )
            }
            item {
                RepeatableItemRoww(
                    visitor = "Bojongsoang",
                    date = "14 Mei 2024",
                    time = "20:00 - 21:00",
                    type = "TUNAI",
                    price = "5000"
                )
            }
            item {
                RepeatableItemRoww(
                    visitor = "Bojongsoang",
                    date = "14 Mei 2024",
                    time = "20:00 - 21:00",
                    type = "TUNAI",
                    price = "5000"
                )
            }
            item {
                RepeatableItemRoww(
                    visitor = "Bojongsoang",
                    date = "14 Mei 2024",
                    time = "20:00 - 21:00",
                    type = "TUNAI",
                    price = "5000"
                )
            }
            item {
                RepeatableItemRoww(
                    visitor = "Bojongsoang",
                    date = "14 Mei 2024",
                    time = "20:00 - 21:00",
                    type = "TUNAI",
                    price = "5000"
                )
            }
            item {
                RepeatableItemRoww(
                    visitor = "Bojongsoang",
                    date = "14 Mei 2024",
                    time = "20:00 - 21:00",
                    type = "TUNAI",
                    price = "5000"
                )
            }
        }
    }
}

@Composable
fun RepeatableItemRoww(
    visitor: String,
    date: String,
    time: String,
    type: String,
    price: String,
    effect: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { effect() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.profile),
            contentDescription = "Profile Picture",
            modifier = Modifier
                .size(60.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier = Modifier.weight(1f)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = visitor,
                    style = MaterialTheme.typography.bodyLarge.copy(fontSize = 18.sp),
                    fontWeight = FontWeight.W500,
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = "-Rp$price",
                    style = MaterialTheme.typography.bodyLarge.copy(fontSize = 18.sp)
                )
            }
            Spacer(modifier = Modifier.height(4.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = date,
                    style = MaterialTheme.typography.bodySmall.copy(fontSize = 14.sp)
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = time,
                    style = MaterialTheme.typography.bodySmall.copy(fontSize = 14.sp)
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = type,
                    style = MaterialTheme.typography.bodySmall.copy(fontSize = 14.sp)
                )
            }
        }
    }
}


@Composable
fun UserPayment(
    modifier: Modifier = Modifier,
    detailPayment: (id:String,type:String) -> Unit = { id: String, type: String -> }
) {
    Column(modifier = modifier) {
        LazyColumn {
            repeat(5){
                item {
                    UserPaymentRepeatableItemRow(
                        type = "qr",
                        date = "14/05/24",
                        visitor = "Kang Seulgi",
                        detailPayment
                    )
                }
            }

            repeat(5){
                item {
                    UserPaymentRepeatableItemRow(
                        type = "cash",
                        date = "14/05/24",
                        visitor = "Kang Seulgi",
                        detailPayment
                    )
                }
            }
        }
    }
}

@Composable
fun UserPaymentRepeatableItemRow(
    type: String,
    date: String,
    visitor: String,
    detailPayment: (id:String,type:String) -> Unit = { id: String, type: String -> },
) {
    val buttonShape: Shape = RoundedCornerShape(100.dp)
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable {
                detailPayment("id",type)
                       },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.profile),
            contentDescription = "Profile Picture",
            modifier = Modifier
                .size(60.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier = Modifier.weight(1f)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "($type)",
                    style = MaterialTheme.typography.bodyLarge.copy(fontSize = 18.sp),
                    fontWeight = FontWeight.W500,
                )
                Spacer(modifier = Modifier.weight(1f))
                Button(
                    onClick = {
                        detailPayment("id",type)
                              },

                    colors = ButtonDefaults.buttonColors(
                        containerColor = BluePark
                    ),
                    shape = buttonShape,
                    modifier = Modifier
                        .height(33.dp)
                ) {
                    Text(
                        text = "PEMBAYARAN",
                        color = Color.White,
                        fontSize = 14.sp
                    )
                }

            }
            Spacer(modifier = Modifier.height(4.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = date,
                    style = MaterialTheme.typography.bodySmall.copy(fontSize = 14.sp)
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = visitor,
                    style = MaterialTheme.typography.bodySmall.copy(fontSize = 14.sp)
                )
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
private fun UserPaymentRepeatableItemRowPreview() {
    UserPayment()
}

@Preview(showBackground = true)
@Composable
private fun RepeatableItemRowPreview() {
    VisitorsList()
}