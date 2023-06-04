package com.example.aularoomdatabase2023.screen

import android.app.Application
import android.app.DatePickerDialog
import android.util.Log
import android.widget.DatePicker
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.aularoomdatabase2023.viewModel.*
import kotlinx.coroutines.flow.collectLatest
import java.util.Calendar
import java.util.Date

@Composable
fun NewTravel()  {

    val application = LocalContext.current.applicationContext as Application
    val viewModel: RegisterNewTravelViewModel = viewModel(
        factory = NewTravelViewModelFactory(application)
    )
    val ctx = LocalContext.current

    val scaffoldState = rememberScaffoldState()
    LaunchedEffect(Unit) {
        viewModel.toastMessage.collectLatest {
            scaffoldState.snackbarHostState.showSnackbar(
                message = it,
                duration = SnackbarDuration.Long
            )
        }
    }

    viewModel.classification = "L"

    val focusManager = LocalFocusManager.current

    //DatePicker

    // Declaring integer values
    // for year, month and day
    val mYear: Int
    val mMonth: Int
    val mDay: Int

    // Initializing a Calendar
    val mCalendar = Calendar.getInstance()

    // Fetching current year, month and day
    mYear = mCalendar.get(Calendar.YEAR)
    mMonth = mCalendar.get(Calendar.MONTH)
    mDay = mCalendar.get(Calendar.DAY_OF_MONTH)

    mCalendar.time = Date()

    // Declaring DatePickerDialog and setting
    // initial values as current values (present year, month and day)
    val mDatePickerDialogStart= DatePickerDialog(
        ctx,
        { _: DatePicker, mYear: Int, mMonth: Int, mDayOfMonth: Int ->
            viewModel.begin = "$mDayOfMonth/${mMonth+1}/$mYear"
        }, mYear, mMonth, mDay
    )
    val mDatePickerDialogEnd = DatePickerDialog(
        ctx,
        { _: DatePicker, mYear: Int, mMonth: Int, mDayOfMonth: Int ->
            viewModel.end = "$mDayOfMonth/${mMonth+1}/$mYear"
        }, mYear, mMonth, mDay
    )

    fun openDatePicker(focused: Boolean, date: String) {
        if (focused && date == "begin") {
            mDatePickerDialogStart.show();
        }
        if (focused && date == "end"){
            mDatePickerDialogEnd.show()
        }
    }

    Scaffold(scaffoldState = scaffoldState ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(paddingValues = it),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            OutlinedTextField(
                value = viewModel.destination,
                onValueChange = { viewModel.destination = it},
                label = {
                    Text(text = "Destination")
                }
            )

            Row() {
                RadioButton(
                    selected = viewModel.classification == "L",
                    onClick = {viewModel.classification = "L"}
                )
                Text(
                    text = "Leisure",
                    modifier = Modifier.padding(start = 16.dp)
                )
            }

            Row() {
                RadioButton(
                    selected = viewModel.classification == "B",
                    onClick = {viewModel.classification = "B"}
                )
                Text(
                    text = "Business",
                    modifier = Modifier.padding(start = 16.dp)
                )
            }

            OutlinedTextField(
                value = viewModel.begin,
                onValueChange = {},
                label = {
                    Text(text = "Start Date")
                },
                modifier = Modifier.onFocusChanged { a -> openDatePicker(a.isFocused, "begin") }
            )
            OutlinedTextField(
                value = viewModel.end,
                onValueChange = {},
                label = {
                    Text(text = "End Date")
                },
                modifier = Modifier.onFocusChanged { b -> openDatePicker(b.isFocused, "end") }
            )
            OutlinedTextField(
                value = viewModel.budget,
                onValueChange = { viewModel.budget = it},
                label = {
                    Text(text = "Budget")
                }
            )
            Row() {
                Button(onClick = {
                    focusManager.clearFocus()
                    viewModel.registerNewTravel(onSuccess = {
                    })
                }) {
                    Text(text = "New travel!")
                }
            }

        }
    }
}