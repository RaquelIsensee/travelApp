package com.example.aularoomdatabase2023.screen


import android.app.Application
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*



import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*

import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.aularoomdatabase2023.viewModel.ListTravelModelFactory
import com.example.aularoomdatabase2023.viewModel.ListTravelViewModel


@Composable
fun ListScreen(userId: Int, OpenNewTravel: (Int) -> Unit, listExpenses:(Int) -> Unit) {

    val application = LocalContext.current.applicationContext as Application
    val viewModel: ListTravelViewModel = viewModel(
        factory = ListTravelModelFactory (application)
    )


   viewModel.loadAllTravels(userId)


    Column(Modifier.fillMaxSize()) {
        Button(
            onClick = {
                OpenNewTravel(userId)
            }) {
            Text(text = "Book a new travel!")
        }
        LazyColumn() {
            items(items = viewModel.travels.value) {
                Card(
                    elevation = 4.dp,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(4.dp)
                        .clickable { }
                ) {
                    Row( modifier = Modifier.padding(8.dp)) {
                        Text(
                            text = "${it.destination}",
                        )
                        Spacer(Modifier.weight(1f))

                        Button(
                            onClick = {
                                listExpenses(it.id)
                            }) {
                            Text(text = "Add expenses")
                        }

                    }

                }
            }
        }

    }

}
