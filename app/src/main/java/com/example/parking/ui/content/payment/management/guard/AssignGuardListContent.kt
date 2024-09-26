package com.example.parking.ui.content.payment.management.guard

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.parking.ui.component.AssignGuardList
import com.example.parking.ui.screen.form.AssignGuard

@Composable
fun AssignGuardListContent(
    modifier: Modifier = Modifier,
    onBack:() -> Unit = { },
    listGuard: MutableState<MutableList<AssignGuard>> = mutableStateOf(mutableListOf()),
    onCheck: (index:Int,id: String, status: Boolean) -> Unit = {index:Int, id: String, status: Boolean -> }
) {


    Scaffold(
        topBar = {
            Column(
                modifier = Modifier
                    .background(Color(0xFF1565C0))
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onBack) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White,
                            modifier = Modifier.size(53.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(85.dp))
                    Text(
                        text = "Daftar Penjaga",
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.align(Alignment.CenterVertically)
                    )
                }
            }
        },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            AssignGuardList(
                listGuard = listGuard,
                onCheck = onCheck
            )
        }
    }
}

@Preview
@Composable
private fun AssignListGuardPreview() {
    AssignGuardListContent()
}