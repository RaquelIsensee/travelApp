package com.example.aularoomdatabase2023.screen

import android.app.Application
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.aularoomdatabase2023.viewModel.RegisterNewExpenseModelFactory
import com.example.aularoomdatabase2023.viewModel.RegisterNewExpenseViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun NewExpense(travelId: Int, onBack: () -> Unit) {

    val application = LocalContext.current.applicationContext as Application
    val viewModel: RegisterNewExpenseViewModel = viewModel(
        factory = RegisterNewExpenseModelFactory(application)
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
    val focusManager = LocalFocusManager.current
    Scaffold(scaffoldState = scaffoldState) {
        Column(
            modifier = Modifier.fillMaxSize().padding(paddingValues = it),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            OutlinedTextField(
                value = viewModel.name,
                onValueChange = { viewModel.name = it },
                label = {
                    Text(text = "Name of the expense")
                }
            )
            OutlinedTextField(
                value = viewModel.expense.toString(),
                onValueChange = {viewModel.expense = it.toFloat() },
                label = {
                    Text(text = "Value of the expense")
                }
            )
            Row() {
                Button(onClick = {
                    focusManager.clearFocus()
                    viewModel.registerNewExpense(travelId, onSuccess = {
                        onBack()
                    })
                }) {
                    Text(text = "New Expense!")
                }
            }
        }
    }
}

