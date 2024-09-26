package com.example.parking.ui.component

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.parking.R
import com.example.parking.ui.screen.form.AssignGuard
import com.example.parking.ui.theme.BluePark

@Composable
fun GuardList(
    modifier: Modifier = Modifier,
    listGuard: MutableState<MutableList<String>> = mutableStateOf(mutableListOf()),
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        listGuard.value.map {
            item { RepeatableItemRow(namePlace = it) }
        }
    }
}

@Composable
fun AssignGuardList(
    modifier: Modifier = Modifier,
    listGuard: MutableState<MutableList<AssignGuard>> = mutableStateOf(mutableListOf()),
    onCheck: (index:Int,id: String, status: Boolean) -> Unit = {index:Int, id: String, status: Boolean -> }
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        listGuard.value.forEachIndexed() {
            index,it ->
            listGuard.value[index].index.value = index
            item { AssignItemGuard(guard = listGuard.value[index], onCheck = onCheck) }
        }
    }
}

@Composable
fun AssignItemGuard(
    guard: AssignGuard,
    onCheck: (index:Int,id: String, status: Boolean) -> Unit = {index:Int, id: String, status: Boolean -> }
) {
    Row(
        modifier = Modifier.padding(vertical = 8.dp).fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
        ) {
//        Image(
//            painter = painterResource(id = R.drawable.profile),
//            contentDescription = "Profile Picture",
//            modifier = Modifier.size(60.dp)
//        )
        Column (
            modifier = Modifier.clickable {
                onCheck(guard.index.value,guard.id.value,guard.isAssign.value)
                if(!guard.isAssign.value){
                    guard.isAssign.value = true
                } else {
                    guard.isAssign.value = false
                }
            }
        ){
            Text(
                text = guard.name.value,
                style = MaterialTheme.typography.bodyLarge.copy(fontSize = 20.sp),
                fontWeight = FontWeight.W500,
                color = BluePark
            )
        }

        if(guard.isAssign.value){
            CustomIcon(
                color = BluePark,
                IconVector = Icons.Default.CheckCircle,
                isOutlined = false,
                modifier = Modifier
                    .size(20.dp)
            )
        }
    }
}

@Composable
fun RepeatableItemRow(
    namePlace: String,
) {
    Row(
        modifier = Modifier.padding(vertical = 8.dp),
    ) {
//        Image(
//            painter = painterResource(id = R.drawable.profile),
//            contentDescription = "Profile Picture",
//            modifier = Modifier.size(60.dp)
//        )
//        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(
                text = namePlace,
                style = MaterialTheme.typography.bodyLarge.copy(fontSize = 20.sp),
                fontWeight = FontWeight.W500,
                color = BluePark
            )
        }

    }
}

@SuppressLint("UnrememberedMutableState")
@Preview(showBackground = true)
@Composable
fun GuardListPreview() {
    GuardList(
        listGuard = mutableStateOf(
            mutableListOf("halo","hai")
        )
    )
}

@Preview(showBackground = true)
@Composable
fun AssignGuardListPreview() {
    val guardListState = remember {
        mutableStateOf(mutableListOf(
            AssignGuard(mutableStateOf(0),mutableStateOf("fjdslksjd"), mutableStateOf("saputra"), isAssign = mutableStateOf(false)),

            AssignGuard(mutableStateOf(0),mutableStateOf("fjdslksjd"), mutableStateOf("saputra"), isAssign = mutableStateOf(false)),

            AssignGuard(mutableStateOf(0),mutableStateOf("fjdslksjd"), mutableStateOf("saputra"), isAssign = mutableStateOf(false)),

            AssignGuard(mutableStateOf(0),mutableStateOf("fjdslksjd"), mutableStateOf("saputra"), isAssign = mutableStateOf(false)),
        ))
    }
    AssignGuardList(
        listGuard = guardListState
    )
}

