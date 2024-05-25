package com.example.parking.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.parking.R
import com.example.parking.ui.navigation.Screen
import com.example.parking.ui.theme.BluePark

@Composable
fun ParkirArea(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    Column(modifier = modifier.padding(16.dp)) {
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
            item {
                RepeatableItemsRow(namePlace = "Bojongsoang", guard = 0, effect = {
                    navController.navigate(
                        Screen.UpdateArea.createRoute("a")
                    )
                })
            }
            item {
                RepeatableItemsRow(namePlace = "Citra Land", guard = 0) {
                    navController.navigate(
                        Screen.UpdateArea.createRoute("b")
                    )
                }
            }
            item {
                RepeatableItemsRow(namePlace = "Jember Town Square", guard = 1) {
                    navController.navigate(
                        Screen.UpdateArea.createRoute("c")
                    )
                }
            }
            item {
                RepeatableItemsRow(namePlace = "Bojongsoang", guard = 0) {
                    navController.navigate(
                        Screen.UpdateArea.createRoute("d")
                    )
                }
            }
            item {
                RepeatableItemsRow(namePlace = "Citra Land", guard = 0) {
                    navController.navigate(
                        Screen.UpdateArea.createRoute("e")
                    )
                }
            }
            item {
                RepeatableItemsRow(namePlace = "Jember Town Square", guard = 1) {
                    navController.navigate(
                        Screen.UpdateArea.createRoute("f")
                    )
                }
            }
            item {
                RepeatableItemsRow(namePlace = "Bojongsoang", guard = 0) {
                    navController.navigate(
                        Screen.UpdateArea.createRoute("")
                    )
                }
            }
            item {
                RepeatableItemsRow(namePlace = "Citra Land", guard = 0) {
                    navController.navigate(
                        Screen.UpdateArea.createRoute("g")
                    )
                }
            }
            item {
                RepeatableItemsRow(namePlace = "Jember Town Square", guard = 1) {
                    navController.navigate(
                        Screen.UpdateArea.createRoute("h")
                    )
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
