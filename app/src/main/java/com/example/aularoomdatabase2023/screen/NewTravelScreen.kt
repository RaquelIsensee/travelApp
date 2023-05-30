package com.example.aularoomdatabase2023.screen

import android.app.Application
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.aularoomdatabase2023.viewModel.*
import kotlinx.coroutines.flow.collectLatest


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
            // opção para mostrar as mensagens
            // Toast.makeText(ctx, it, Toast.LENGTH_SHORT).show()
        }
    }


    val focusManager = LocalFocusManager.current

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

            OutlinedTextField(
                value = viewModel.classification,
                onValueChange = { viewModel.classification = it},
                label = {
                    Text(text = "Classification")
                }
            )
            OutlinedTextField(
                value = viewModel.begin,
                onValueChange = { viewModel.begin = it},
                label = {
                    Text(text = "Start Date")
                }
            )
            OutlinedTextField(
                value = viewModel.end,
                onValueChange = { viewModel.end = it},
                label = {
                    Text(text = "End Date")
                }
            )
            OutlinedTextField(
                value = viewModel.budget,
                onValueChange = { viewModel.budget = it},
                label = {
                    Text(text = "Budget Date")
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