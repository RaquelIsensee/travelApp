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
import com.example.aularoomdatabase2023.viewModel.LoginViewModel
import com.example.aularoomdatabase2023.viewModel.LoginViewModelFactory
import com.example.aularoomdatabase2023.viewModel.RegisterNewUserViewModel
import com.example.aularoomdatabase2023.viewModel.RegisterNewUserViewModelFactory
import kotlinx.coroutines.flow.collectLatest

@Composable
fun LoginScreen(onAfterLogin: (Int) -> Unit, onBack:() -> Unit) {
    val application = LocalContext.current.applicationContext as Application
    val viewModel: LoginViewModel = viewModel(
        factory = LoginViewModelFactory(application)
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


    Scaffold(scaffoldState = scaffoldState ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(paddingValues = it),
            //verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            OutlinedTextField(
                value = viewModel.name,
                onValueChange = { viewModel.name = it},
                label = {
                    Text(text = "Name")
                }
            )

            OutlinedTextField(
                value = viewModel.password,
                onValueChange = { viewModel.password = it},
                label = {
                    Text(text = "Password")
                }
            )
            Row() {
                Button(onClick = {
                    focusManager.clearFocus()
                    viewModel.validateLogin(onResult = {userId ->
                        if (userId > 0) {
                            onAfterLogin(userId)
                        }
                    })
                }) {
                    Text(text = "Login")
                }
                Spacer(modifier = Modifier.size(8.dp))
                Button(
                    onClick = {
                        onBack()
                    }) {
                    Text(text = "Back")
                }


            }

        }
    }

}