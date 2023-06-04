package com.example.aularoomdatabase2023

import android.app.Application
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.aularoomdatabase2023.screen.FormScreen
import com.example.aularoomdatabase2023.screen.ListScreen
import com.example.aularoomdatabase2023.screen.LoginScreen
import com.example.aularoomdatabase2023.screen.NewTravel
import com.example.aularoomdatabase2023.ui.theme.AulaRoomDatabaseTheme
import com.example.aularoomdatabase2023.viewModel.RegisterNewUserViewModel
import com.example.aularoomdatabase2023.viewModel.RegisterNewUserViewModelFactory
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AulaRoomDatabaseTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MyApp()
                }
            }
        }
    }
}

@Composable
fun MyApp() {
    val navController = rememberNavController()
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar() {
                Button(onClick = { navController.navigate("form") }) {
                    Text(text = "Add")
                }
                Button(onClick = { navController.navigate("login") }) {
                    Text(text = "Login")
                }
            }
        }
    ) {
        Column(modifier = Modifier.padding(paddingValues = it)) {
            NavHost(
                navController = navController,
                startDestination = "list" ) {
                composable("list") {
                    ListScreen(
                        onBack = {
                        navController.navigateUp()
                    },
                    NewTravel = {
                        navController.navigate("new_travel")
                        coroutineScope.launch {
                            scaffoldState.snackbarHostState.showSnackbar(
                                message = "new travel ok"
                            )
                        }
                    }
                    )
                }
                composable("login") {
                    LoginScreen(
                        onBack = {
                            navController.navigateUp()
                        },
                        onAfterLogin = {
                            navController.navigate("list")
                            coroutineScope.launch {
                                scaffoldState.snackbarHostState.showSnackbar(
                                    message = "Login ok"
                                )
                            }
                        }
                    )
                }
                composable("form") {
                    FormScreen(onAfterSave = {
                        navController.navigateUp()
                        coroutineScope.launch {
                            scaffoldState.snackbarHostState.showSnackbar(
                                message = "User registered"
                            )
                        }
                    },
                        onBack = {
                            navController.navigateUp()
                        }
                    )
                }
                composable("new_travel") {
                    NewTravel()
                }
            }
        }
    }

}
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    AulaRoomDatabaseTheme {
       MyApp()
    }
}