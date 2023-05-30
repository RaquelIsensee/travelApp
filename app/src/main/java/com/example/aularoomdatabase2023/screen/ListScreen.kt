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
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.aularoomdatabase2023.entity.User

import com.example.aularoomdatabase2023.viewModel.ListUserViewModel
import com.example.aularoomdatabase2023.viewModel.ListUserViewModelFactory

@Composable
fun ListScreen() {
    val application = LocalContext.current.applicationContext as Application
    val viewModel: ListUserViewModel = viewModel(
        factory = ListUserViewModelFactory (application)
    )

    viewModel.loadAllUsers()

    var openDialogRemove by remember { mutableStateOf(false) }
    ConfirmDelete(openDialog = openDialogRemove,
        onRemove = {
            openDialogRemove = false
            viewModel.deleteUser()
        },
        onClose = { openDialogRemove = false }
    )


    Column(Modifier.fillMaxSize()) {
        LazyColumn() {
            items(items = viewModel.users.value) {
                Card(
                    elevation = 4.dp,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(4.dp)
                        .clickable { }
                ) {
                    Row( modifier = Modifier.padding(8.dp)) {
                        Text(
                            text = "${it.name}",
                        )
                        Spacer(Modifier.weight(1f))
                        IconButton(
                            onClick = {
                                viewModel.userForDelete = it
                                openDialogRemove = true
                            },
                        ) {
                            Icon(
                                Icons.Default.Delete,
                                contentDescription = "",
                            )
                        }
                        Button(
                            onClick = {

                            }) {
                            Text(text = "Book a new travel!")
                        }

                    }

                }
            }
        }

        
    }

}

@Composable
fun ConfirmDelete(openDialog: Boolean, onClose: () -> Unit, onRemove: () -> Unit) {
    if (openDialog) {
        AlertDialog(
            onDismissRequest = {
                onClose()
            },
            title = {
                Text(text = "Confirm delete record?")
            },
            text = {
                Column() {
                    Text("Do you want delete this record?")
                }
            },
            confirmButton = {
                Button(onClick = { onRemove() }) {
                    Text(text = "Yes")
                }
            },
            dismissButton = {
                Button(onClick = { onClose() }) {
                    Text(text = "No")
                }
            },
        )
    }
}