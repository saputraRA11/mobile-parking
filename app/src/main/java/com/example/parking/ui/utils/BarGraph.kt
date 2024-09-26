package com.example.parking.ui.utils

import android.graphics.Paint
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement.Top
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.room.Room
import com.example.parking.ui.theme.BluePark
import kotlin.math.round

@Composable
fun BarGraph(
    graphBarData: List<Float>,
    xAxisScaleData: MutableState<MutableList<String>>,
    barData_: MutableState<MutableList<Int>>,
    height: Dp,
    roundType: BarType,
    barWidth: Dp,
    barColor: Color,
    barArrangement: Arrangement.Horizontal
) {
    val configuration = LocalConfiguration.current
    val width = configuration.screenWidthDp.dp

    val xAxisScaleHeight = 40.dp

    val yAxisScaleSpacing by remember {
        mutableStateOf(100f)
    }
    val yAxisTextWidth by remember {
        mutableStateOf(100.dp)
    }

    val barShap =
        when (roundType) {
            BarType.CIRCULAR_TYPE -> CircleShape
            BarType.TOP_CURVED -> RoundedCornerShape(topStart = 5.dp, topEnd = 5.dp)
        }

    val density = LocalDensity.current
    val textPaint = remember(density) {
        Paint().apply {
            color = Color.Black.hashCode()
            textAlign = Paint.Align.CENTER
            textSize = density.run { 12.sp.toPx() }
        }
    }

    val yCoordinates = mutableListOf<Float>()
    val pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
    val lineHeightXAxis = 10.dp
    val horizontalLineHeight = 2.dp

    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.TopStart
    ) {

        Column(
            modifier = Modifier
                .padding(top = xAxisScaleHeight, end = 3.dp)
                .height(height)
                .fillMaxWidth(),
            horizontalAlignment = CenterHorizontally
        ) {

            Canvas(modifier = Modifier
                .padding(bottom = 10.dp)
                .fillMaxSize()) {

                val yAxisScaleText = ((barData_.value + 0).max()) / 3f
                (0..3).forEach { i ->
                    drawContext.canvas.nativeCanvas.apply {
                        drawText(
                            "${round((barData_.value + 0).min() + yAxisScaleText * i).toInt()}",
                            40f,
                            size.height - yAxisScaleSpacing - i * size.height / 3f,
                            textPaint
                        )
                    }
                    yCoordinates.add(size.height - yAxisScaleSpacing - i * size.height / 3f)
                }

//                (1..3).forEach {
//                    drawLine(
//                        start = Offset(x = yAxisScaleSpacing +30f, y = yCoordinates[it]),
//                        end = Offset(x= size.width, y = yCoordinates[it]),
//                        color = Color.Gray,
//                        strokeWidth = 5f,
//                        pathEffect = pathEffect
//                    )
//                }

            }

        }

        Box(
            modifier = Modifier
                .padding(start = 50.dp)
                .width(width - yAxisTextWidth)
                .height(height + xAxisScaleHeight),
            contentAlignment = Alignment.BottomCenter
        ) {


            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.Start
            ) {
                Box(
                    modifier = Modifier
                        .width(2.dp)
                        .padding(bottom = 45.dp)
                        .background(Color.Black),
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                    )
                }

                Row(
                    modifier = Modifier
                        .padding(start = 20.dp)
                        .width(width - yAxisTextWidth),
                    verticalAlignment = Alignment.Top,
                    horizontalArrangement = Arrangement.spacedBy(30.dp)
                ) {
                    graphBarData.forEachIndexed { index, value ->

                        var animationTriggered by remember {
                            mutableStateOf(false)
                        }
                        val graphBarHeight by animateFloatAsState(
                            targetValue = if (animationTriggered) value else 0f,
                            animationSpec = tween(
                                durationMillis = 1000,
                                delayMillis = 0
                            )
                        )
                        LaunchedEffect(key1 = true) {
                            animationTriggered = true
                        }

                        Column(
                            modifier = Modifier.fillMaxHeight(),
                            verticalArrangement = Top,
                            horizontalAlignment = CenterHorizontally
                        ) {
                            Box(
                                modifier = Modifier
                                    .padding(bottom = 5.dp)
                                    .width(barWidth)
                                    .height(height - 10.dp)
                                    .background(Color.Transparent),
                                contentAlignment = BottomCenter
                            ) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .fillMaxHeight(graphBarHeight)
                                        .background(barColor)
                                )
                            }

                            Column(
                                modifier = Modifier
                                    .height(xAxisScaleHeight),
                                verticalArrangement = Top,
                                horizontalAlignment = CenterHorizontally
                            ) {

                                Box(
                                    modifier = Modifier
                                        .clip(
                                            RoundedCornerShape(
                                                bottomStart = 2.dp,
                                                bottomEnd = 2.dp
                                            )
                                        )
                                        .width(horizontalLineHeight)
                                        .height(lineHeightXAxis)
                                )

                                Text(
                                    modifier = Modifier.padding(bottom = 3.dp),
                                    text = xAxisScaleData.value[index],
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Medium,
                                    textAlign = TextAlign.Center,
                                    color = Color.Black
                                )

                            }

                        }

                    }
                }

            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Transparent),
                horizontalAlignment = CenterHorizontally
            ) {

                Box(
                    modifier = Modifier
                        .padding(bottom = xAxisScaleHeight + 3.dp)
                        .fillMaxWidth()
                        .height(horizontalLineHeight)
                        .background(Color.Black)
                )
            }
        }
    }
}

@Composable
fun TransformChartGraph(
    column:MutableState<MutableList<String>> = mutableStateOf(mutableListOf()),
    dataset:MutableState<MutableList<Int>> = mutableStateOf(mutableListOf())
){
//    val dummyset = mutableListOf(2)
//    val column = mutableListOf("June")
    Column(
        modifier = Modifier
            .padding(horizontal = 30.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        val floatValue = mutableListOf<Float>()


        dataset.value.forEachIndexed { index, value ->
            floatValue.add(index = index, element = value.toFloat()/dataset.value.max().toFloat())
        }

//        dummyset.forEachIndexed { index, value ->
//            floatValue.add(index = index, element = value.toFloat()/dummyset.max().toFloat())
//        }
//        BarGraph(
//            graphBarData = floatValue,
//            xAxisScaleData = column.toList(),
//            barData_ = dummyset.toList(),
//            height = 300.dp,
//            roundType = BarType.TOP_CURVED,
//            barWidth = 50.dp,
//            barColor = BluePark,
//            barArrangement = Arrangement.SpaceEvenly
//        )

        BarGraph(
            graphBarData = floatValue,
            xAxisScaleData = column,
            barData_ = dataset,
            height = 300.dp,
            roundType = BarType.TOP_CURVED,
            barWidth = 50.dp,
            barColor = BluePark,
            barArrangement = Arrangement.SpaceEvenly
        )
    }
}

@Preview(
    showBackground = true
)
@Composable
fun ExampleGraph(){
    val column = remember {
        mutableListOf(
            "June",
        )
    }

    val dataset = remember {

        mutableListOf(
            2
        )
    }
//    TransformChartGraph(
//        column = column,
//        dataset = dataset
//    )
}

enum class BarType {

    CIRCULAR_TYPE,
    TOP_CURVED

}













