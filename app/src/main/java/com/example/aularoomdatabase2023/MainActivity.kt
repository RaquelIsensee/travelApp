package com.example.aularoomdatabase2023

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.aularoomdatabase2023.screen.FormScreen
import com.example.aularoomdatabase2023.screen.ListScreen
import com.example.aularoomdatabase2023.screen.LoginScreen
import com.example.aularoomdatabase2023.screen.NewTravel
import com.example.aularoomdatabase2023.ui.theme.AulaRoomDatabaseTheme
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
                startDestination = "login" ) {

                composable("login") {
                    LoginScreen(
                        onBack = {
                            navController.navigateUp()
                        },
                        onAfterLogin = { userId ->
                            navController.navigate("list/$userId")
                            coroutineScope.launch {
                                scaffoldState.snackbarHostState.showSnackbar(
                                    message = "Login ok"
                                )
                            }
                        }
                    )
                }

                composable(
                    "list/{userId}",
                    arguments = listOf(navArgument("userId") { type = NavType.StringType })
                ) {
                    val param = it.arguments?.getString("userId")
                    val userId = param?.toInt()
                    if (userId != null) {
                        ListScreen(
                            userId,
                            OpenNewTravel = { userId ->
                                navController.navigate("new_travel/$userId")
                            }
                        )
                    }
                }

                composable(
                    "new_travel/{userId}",
                    arguments = listOf(navArgument("userId") { type = NavType.StringType })
                ) {
                    val param = it.arguments?.getString("userId")
                    val userId = param?.toInt()
                    if (userId != null) {
                        NewTravel(
                            userId,
                            onBack = {
                                navController.navigateUp()
                            }
                        )
                    }
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