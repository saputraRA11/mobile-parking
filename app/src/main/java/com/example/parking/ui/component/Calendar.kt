package com.example.parking.ui.component

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
        LocalDate.now().plusDays(3),
    )
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
        },
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
internal fun DatePickerRange(
    showDialog: Boolean,
    selectedDates: MutableState<List<LocalDate>>,
    closeSelection: UseCaseState.() -> Unit
) {

    val timeBoundary = LocalDate.now().let { now -> now.minusYears(2)..now }

    if (showDialog) {
        CalendarDialog(
            state = rememberUseCaseState(visible = true, true, onCloseRequest = closeSelection),
            config = CalendarConfig(
                yearSelection = true,
                monthSelection = true,
                boundary = timeBoundary,
                style = CalendarStyle.MONTH,
            ),
            selection = CalendarSelection.Dates { newDates ->
                selectedDates.value = newDates
            }
        )
    }
}
