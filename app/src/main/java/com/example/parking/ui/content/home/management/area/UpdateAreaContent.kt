package com.example.parking.ui.content.home.management.area

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.input.key.onPreviewKeyEvent
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.parking.R
import com.example.parking.ui.component.CustomIcon
import com.example.parking.ui.component.CustomInput
import com.example.parking.ui.navigation.Screen
import com.example.parking.ui.screen.form.GuardIdentity
import com.example.parking.ui.screen.form.UpdateAreaFormDto
import com.example.parking.ui.screen.form.area.UpdateParkingAreaScreen

@Composable
fun UpdateAreaContent(
    backClick: () -> Unit = {},
    submitClick: () -> Unit = {},
    detailGuardClick: () -> Unit = {},
    scrollState: ScrollState = rememberScrollState(),
    updateAreaForm:MutableState<UpdateAreaFormDto> = mutableStateOf(UpdateAreaFormDto()),
    onDelete:(index:Int) -> Unit = {}
){
    val isFormFilled by remember {
        derivedStateOf {
            !updateAreaForm.value.isCar.value &&
             !updateAreaForm.value.isMotor.value &&
                    !updateAreaForm.value.isAddess.value
        }
    }

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
                
                if(isFormFilled){
                    IconButton(onClick = submitClick) {
                        Icon(
                            painter = painterResource(id = R.drawable.check_circle),
                            contentDescription = "Next",
                            tint = Color.White,
                            modifier = Modifier.size(24.dp)
                        )
                    }
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
//                    Image(
//                        painter = painterResource(id = R.drawable.profile),
//                        contentDescription = "Profile Picture",
//                        modifier = Modifier
//                            .size(100.dp)
//                            .background(Color.Gray, shape = CircleShape)
//                    )
//                    IconButton(
//                        onClick = { /* Handle camera action */ },
//                        modifier = Modifier
//                            .size(32.dp)
//                            .background(Color(0xFF1565C0), shape = CircleShape)
//                            .align(Alignment.BottomEnd)
//                    ) {
//                        Icon(
//                            painter = painterResource(id = R.drawable.add_a_photo),
//                            contentDescription = "Camera Picture",
//                            tint = Color.White,
//                            modifier = Modifier.size(26.dp)
//                        )
//                    }
                }
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .verticalScroll(scrollState)
        ) {
            Text("Alamat", style = MaterialTheme.typography.bodyLarge, color = Color.Black)
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                CustomInput(
                    trailing = {
                        if(!updateAreaForm.value.isAddess.value) {
                            CustomIcon(
                                color = Color.Black,
                                isImageVector = false,
                                painter = painterResource(id = R.drawable.edit),
                                isOutlined = false,
                                modifier = Modifier
                                    .size(20.dp)
                                    .clickable {
                                        updateAreaForm.value.isAddess.value = true
                                    }
                                ,
                            )
                        } else {
                            CustomIcon(
                                color = Color.Black,
                                IconVector = Icons.Default.CheckCircle,
                                isOutlined = false,
                                modifier = Modifier
                                    .size(20.dp)
                                    .clickable {
                                        updateAreaForm.value.isAddess.value = false
                                    },
                            )
                        }
                    },
                    saveState = updateAreaForm.value.address,
                    isIconDisabled = false,
                    enabled = updateAreaForm.value.isAddess.value,
                    isIndicatorTransparent = true,
                    maxLine = 3,
                    modifier = Modifier.fillMaxWidth()
                )
            }

//            Log.d("debug input",updateAreaForm.value.address.value.text)
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
                LazyColumn(
                    modifier = Modifier
                        .height(250.dp)
                        .padding(top = 40.dp, bottom = 40.dp)
                ) {
                    updateAreaForm.value.listGuard.value.forEachIndexed {
                        index,item ->
                        item() {
                            Row {
                                Text(item.name, color = Color.Black, modifier = Modifier.weight(1f))
                                IconButton(onClick = {onDelete(index)}
                                ) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.remove),
                                        contentDescription = "Add",
                                        tint = Color.Black,
                                        modifier = Modifier
                                            .size(16.dp)
                                            .clickable {
                                                onDelete(index)
                                            }
                                    )
                                }
                            }
                        }
                    }
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

                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Rp.", color = Color.Black)
                    CustomInput(
                        trailing = {
                            if(!updateAreaForm.value.isCar.value) {
                                CustomIcon(
                                    color = Color.Black,
                                    isImageVector = false,
                                    painter = painterResource(id = R.drawable.edit),
                                    isOutlined = false,
                                    modifier = Modifier
                                        .size(20.dp)
                                        .clickable {
                                            updateAreaForm.value.isCar.value = true
                                        }
                                    ,
                                )
                            } else {
                                CustomIcon(
                                    color = Color.Black,
                                    IconVector = Icons.Default.CheckCircle,
                                    isOutlined = false,
                                    modifier = Modifier
                                        .size(20.dp)
                                        .clickable {
                                            updateAreaForm.value.isCar.value = false
                                        },
                                )
                            }
                        },
                        saveState = updateAreaForm.value.carPrice,
                        isIconDisabled = false,
                        enabled = updateAreaForm.value.isCar.value,
                        isIndicatorTransparent = true,
                        maxLimit = 8,
                        isNumber = true,
                        singleLine = true,
                        fontSize = 15.sp,
                        modifier = Modifier.width(140.dp)
                    )
                }
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Motor", modifier = Modifier.weight(1f), color = Color.Black)
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Rp.", color = Color.Black)
                    CustomInput(
                        trailing = {
                            if(!updateAreaForm.value.isMotor.value) {
                                CustomIcon(
                                    color = Color.Black,
                                    isImageVector = false,
                                    painter = painterResource(id = R.drawable.edit),
                                    isOutlined = false,
                                    modifier = Modifier
                                        .size(20.dp)
                                        .clickable {
                                            updateAreaForm.value.isMotor.value = true
                                        }
                                    ,
                                )
                            } else {
                                CustomIcon(
                                    color = Color.Black,
                                    IconVector = Icons.Default.CheckCircle,
                                    isOutlined = false,
                                    modifier = Modifier
                                        .size(20.dp)
                                        .clickable {
                                            updateAreaForm.value.isMotor.value = false
                                        },
                                )
                            }
                        },
                        saveState = updateAreaForm.value.motorPrice,
                        isIconDisabled = false,
                        enabled = updateAreaForm.value.isMotor.value,
                        isIndicatorTransparent = true,
                        maxLimit = 8,
                        isNumber = true,
                        singleLine = true,
                        fontSize = 15.sp,
                        modifier = Modifier.width(140.dp)
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