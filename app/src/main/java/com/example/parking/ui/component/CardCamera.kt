package com.example.parking.ui.component

import android.Manifest
import android.content.pm.PackageManager
import android.os.Debug
import android.util.Log
import android.util.Size
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.camera.core.Preview
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.zIndex
import androidx.core.content.ContextCompat
import com.example.parking.R
import com.example.parking.ui.theme.BluePark
import com.example.parking.ui.theme.DarkBlue
import com.example.parking.ui.utils.QrCodeAnalyzer

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CardCamera(
    modifier: Modifier = Modifier
){
    var expanded by remember { mutableStateOf(false) }
    val icon = if(expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown

    // camera

    val code = remember {
        mutableStateOf("")
    }

    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val cameraProviderFuture = remember {
        ProcessCameraProvider.getInstance(context)
    }

    val isScan = remember {
        mutableStateOf(false)
    }

    var hasCamPermission = remember {
        mutableStateOf(
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED
        )
    }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = {
            granted ->
            hasCamPermission.value = granted
        }
    )

    LaunchedEffect(key1 = true) {
        launcher.launch(Manifest.permission.CAMERA)
    }

    DisposableEffect(key1 = cameraProviderFuture) {
        onDispose {
            cameraProviderFuture.get().unbindAll()
            code.value = ""
        }
        
    }

    Column (
        modifier = modifier
            .clip(RoundedCornerShape(20.dp))
    ){
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier
                .background(DarkBlue)
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            Text(
                text = "Scan Karcis",
                color = Color.White,
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.W600
            )
            Text(
                text = "Arahkan dan scan ke karcis pengguna",
                color = Color.White,
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.W300
            )
        }

        Column(
            verticalArrangement = Arrangement.spacedBy(30.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .background(BluePark)
                .fillMaxSize()
                .padding(vertical = 10.dp)
        ) {
            if(hasCamPermission.value && isScan.value) {
                AndroidView(
                    factory = { context ->
                        val previewView = PreviewView(context)
                        val preview = Preview.Builder().build()
                        val selector = CameraSelector.Builder()
                            .requireLensFacing(CameraSelector.LENS_FACING_BACK)
                            .build()
                        preview.setSurfaceProvider(previewView.surfaceProvider)
                        val imageAnalysis = ImageAnalysis.Builder()
                            .setTargetResolution(
                                Size(
                                    previewView.width,
                                    previewView.height
                                )
                            )
                            .setBackpressureStrategy(STRATEGY_KEEP_ONLY_LATEST)
                            .build()
                        imageAnalysis.setAnalyzer(
                            ContextCompat.getMainExecutor(context),
                            QrCodeAnalyzer { result ->
                                code.value = result
                            }
                        )

                        try {
                            cameraProviderFuture.let {
                                it ->
                                // clear cache
                                code.value = ""
                                it.get().unbindAll()

                                it.get().bindToLifecycle(
                                    lifecycleOwner,
                                    selector,
                                    preview,
                                    imageAnalysis
                                )
                            }
                        } catch (e: Exception) {
                            e.message?.let { Log.e("Error kamera", it) }
                            e.printStackTrace()
                        }
                        previewView
                    },
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .fillMaxHeight(0.5f)
                        .padding(30.dp)
                    ,
                )
            } else {
                CustomImage(
                    id = R.drawable.camera,
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .fillMaxHeight(0.5f),
                    description = "SCAN"
                )
            }

            Row (
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                // circle
                Canvas(
                    modifier = Modifier
                        .size(1.dp)
                        .zIndex(2f)
                ) {
                    drawCircle(Color.White, radius = 50f, center = Offset(size.width / 1f, size.height / 2f))
                }

                // line
                Canvas(
                    modifier = Modifier
                        .fillMaxWidth()
                        .zIndex(1f)
                ) {
                    val pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 10f)

                    // ujung ke ujung
                    drawLine(
                        Color.LightGray,
                        Offset(0f,0f),
                        Offset(size.width,0f),
                        pathEffect = pathEffect,
                        strokeWidth = 5f
                    )

                }

                // circle
                Canvas(
                    modifier = Modifier
                        .size(1.dp)
                        .zIndex(2f)
                ) {
                    drawCircle(Color.White, radius = 50f, center = Offset(size.width / 1f, size.height / 2f))
                }

            }

            FlowRow (
                horizontalArrangement = Arrangement.spacedBy(20.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp),
                maxItemsInEachRow = 2,
                modifier = Modifier
                    .padding(horizontal = 20.dp)
            ){
                Column(
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    modifier = Modifier
                        .weight(1f)
                ) {
                    Text(
                        text = "Waktu Pindai",
                        color = Color.White,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.W600
                    )
                    Text(
                        text = "12.00" ,
                        color = Color.White,
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.W300
                    )
                }

                Column(
                    modifier = Modifier
                        .weight(1f)
                ) {
                    Text(
                        text = "Pembayaran",
                        fontSize = 22.sp,
                        color = Color.White,
                        fontWeight = FontWeight.W600
                    )
                    ButtonCircle(
                        onClick = {},
                        text = "Tunai",
                        textAlign = Arrangement.SpaceBetween,
                        backgroundColor = ButtonDefaults.buttonColors(containerColor = Color.White, contentColor = DarkBlue),
                        isOutlined = false,
                        modifier = Modifier
                            .fillMaxWidth(),
                        trailingIcon = {
                            CustomIcon(
                                color = Color.Black,
                                IconVector = icon,
                                isOutlined = true,
                                modifier = Modifier
                                    .clickable {
                                        expanded = !expanded
                                    },
                                borderSize = 2.dp,
                            )
                        },
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.W400
                    )
                }

                Column(
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    modifier = Modifier
                        .weight(1f)
                ) {
                    Text(
                        text = "Lokasi",
                        color = Color.White,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.W600
                    )
                    Text(
                        text = "Bojongsoang" ,
                        color = Color.White,
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.W300
                    )
                }
                Column(
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    modifier = Modifier
                        .weight(1f)
                ) {
                    Text(
                        text = "Pemindai",
                        color = Color.White,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.W600
                    )
                    Text(
                        text = "Kasir Bojongsoang" ,
                        color = Color.White,
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.W300
                    )
                }
            }

            if(isScan.value) {
                ButtonCircle(
                    onClick = {
                        isScan.value = false
                    },
                    text = "Konfirmasi",
                    backgroundColor = ButtonDefaults.buttonColors(containerColor = Color.White, contentColor = BluePark),
                    isOutlined = false,
                    modifier = Modifier
                        .padding(horizontal = 20.dp)
                        .fillMaxWidth(),
                    enable = if(code.value != "") true else false
                )
            } else {
                ButtonCircle(
                    onClick = {
                        isScan.value = true
                    },
                    text = "Scan Karcis Baru",
                    backgroundColor = ButtonDefaults.buttonColors(containerColor = Color.White, contentColor = BluePark),
                    isOutlined = false,
                    modifier = Modifier
                        .padding(horizontal = 20.dp)
                        .fillMaxWidth(),
                )
            }
        }
    }
}

@androidx.compose.ui.tooling.preview.Preview(showBackground = true)
@Composable
fun CardCameraPreview(){
    CardCamera()
}