package com.example.parking.ui.component

import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import co.yml.charts.axis.AxisData
import co.yml.charts.axis.DataCategoryOptions
import co.yml.charts.common.utils.DataUtils
import co.yml.charts.ui.barchart.models.BarChartData
import co.yml.charts.ui.barchart.models.BarChartType
import com.example.parking.ui.theme.BluePark
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.tooling.preview.Preview
import co.yml.charts.ui.barchart.BarChart

@Composable
fun BarChart(modifier: Modifier = Modifier) {
    val stepSize = 5
    val barsData = DataUtils.getBarChartData(
        listSize = 8,
        maxRange = 8,
        barChartType = BarChartType.VERTICAL,
        dataCategoryOptions = DataCategoryOptions()
    )

    val xAxisData = AxisData.Builder()
        .axisStepSize(100.dp)
        .steps(barsData.size - 1)
        .bottomPadding(40.dp)
        .axisLabelAngle(20f)
        .labelData { index -> barsData[index].label }
        .axisLineColor(BluePark)
        .axisLabelColor(BluePark)
        .build()

    val yAxisData = AxisData.Builder()
        .steps(stepSize)
        .labelAndAxisLinePadding(20.dp)
        .axisOffset(20.dp)
        .labelData { index -> (index * (100 / stepSize)).toString() }
        .axisLineColor(BluePark)
        .axisLabelColor(BluePark)
        .build()

    val barChartData = BarChartData(
        chartData = barsData,
        xAxisData = xAxisData,
        yAxisData = yAxisData,
        backgroundColor = MaterialTheme.colorScheme.surface
    )

    BarChart(modifier.height(350.dp),
        barChartData = barChartData)
}

@Preview (showBackground = true)
@Composable
private fun BarChartPreview() {
    BarChart()
}