package com.example.parking.ui.component

import android.os.Build
import android.util.Range
import androidx.annotation.RequiresApi
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.core.util.toRange
import com.maxkeppeker.sheets.core.models.base.UseCaseState
import com.maxkeppeker.sheets.core.models.base.rememberUseCaseState
import com.maxkeppeler.sheets.calendar.CalendarDialog
import com.maxkeppeler.sheets.calendar.models.CalendarConfig
import com.maxkeppeler.sheets.calendar.models.CalendarSelection
import com.maxkeppeler.sheets.calendar.models.CalendarStyle
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DatePicker(
    showDialog: Boolean,
    selectedDate: MutableState<LocalDate?>,
    closeSelection: UseCaseState.() -> Unit
) {
    val selectedDates = remember { mutableStateOf<List<LocalDate>>(listOf()) }
    val disabledDates = listOf(
        LocalDate.now().minusDays(7),
        LocalDate.now().minusDays(12),
        LocalDate.now().plusDays(3)
    )
    if (showDialog) {
        CalendarDialog(
            state = rememberUseCaseState(visible = true, onCloseRequest = { closeSelection() }),
            config = CalendarConfig(
                yearSelection = true,
                monthSelection = true,
                style = CalendarStyle.MONTH,
                disabledDates = disabledDates
            ),
            selection = CalendarSelection.Dates { newDates ->
                selectedDates.value = newDates
                selectedDate.value = newDates.firstOrNull()
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
internal fun DatePickerRange(
    showDialog: Boolean,
    selectedRange: MutableState<Range<LocalDate>?>,
    closeSelection: UseCaseState.() -> Unit
) {
    val timeBoundary = LocalDate.now().let { now -> now.minusYears(2)..now }
    val initialRange = LocalDate.now().minusYears(2).let { time -> time.plusDays(5)..time.plusDays(8) }
    val currentRange = remember { mutableStateOf(initialRange.toRange()) }

    if (showDialog) {
        CalendarDialog(
            state = rememberUseCaseState(visible = true, onCloseRequest = closeSelection),
            config = CalendarConfig(
                yearSelection = true,
                monthSelection = true,
                boundary = timeBoundary,
                style = CalendarStyle.MONTH
            ),
            selection = CalendarSelection.Period(
                selectedRange = currentRange.value
            ) { startDate, endDate ->
                currentRange.value = Range(startDate, endDate)
                selectedRange.value = Range(startDate, endDate)
            }
        )
    }
}
