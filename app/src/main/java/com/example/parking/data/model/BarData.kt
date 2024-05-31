package com.example.parking.data.model

import androidx.compose.ui.unit.dp
import kotlin.random.Random

data class BarData(val label: String, val value: Float)

data class AxisData(
    val steps: Int,
    val axisStepSize: Float,
    val bottomPadding: Float,
    val axisLabelAngle: Float,
    val labelData: (Int) -> String,
    val axisOffset: Float,
    val labelAndAxisLinePadding: Float
) {
    class Builder {
        private var steps: Int = 0
        private var axisStepSize: Float = 0f
        private var bottomPadding: Float = 0f
        private var axisLabelAngle: Float = 0f
        private var labelData: (Int) -> String = { "" }
        private var axisOffset: Float = 0f
        private var labelAndAxisLinePadding: Float = 0f

        fun steps(steps: Int) = apply { this.steps = steps }
        fun axisStepSize(axisStepSize: Float) = apply { this.axisStepSize = axisStepSize }
        fun bottomPadding(bottomPadding: Float) = apply { this.bottomPadding = bottomPadding }
        fun axisLabelAngle(axisLabelAngle: Float) = apply { this.axisLabelAngle = axisLabelAngle }
        fun labelData(labelData: (Int) -> String) = apply { this.labelData = labelData }
        fun axisOffset(axisOffset: Float) = apply { this.axisOffset = axisOffset }
        fun labelAndAxisLinePadding(labelAndAxisLinePadding: Float) = apply { this.labelAndAxisLinePadding = labelAndAxisLinePadding }
        fun build() = AxisData(steps, axisStepSize, bottomPadding, axisLabelAngle, labelData, axisOffset, labelAndAxisLinePadding)
    }
}

data class BarChartData(
    val chartData: List<BarData>,
    val xAxisData: AxisData,
    val yAxisData: AxisData,
    val paddingBetweenBars: Float,
    val barWidth: Float
)

object DataUtils {
    fun getBarChartData(size: Int, maxRange: Float): List<BarData> {
        return List(size) { index ->
            BarData(label = "Label $index", value = Random.nextFloat() * maxRange)
        }
    }
}
