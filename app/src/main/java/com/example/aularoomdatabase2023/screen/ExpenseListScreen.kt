package com.example.aularoomdatabase2023.screen

import android.app.Application
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.aularoomdatabase2023.viewModel.ListExpenseModelFactory
import com.example.aularoomdatabase2023.viewModel.ListExpenseViewModel

@Composable
fun ExpenseListScreen(travelId: Int, OpenNewExpense: (Int) -> Unit) {

    val application = LocalContext.current.applicationContext as Application
    val viewModel: ListExpenseViewModel = viewModel(
        factory = ListExpenseModelFactory(application)
    )


    viewModel.loadAllExpenses(travelId)
    var total: Float = 0.00f
    for(expense in viewModel.expenses.value) {
        total = expense.expense + total
    }

    Column(Modifier.fillMaxSize()) {
        Button(
            onClick = {
                OpenNewExpense(travelId)
            }) {
            Text(text = "Add a new expense")
        }
        LazyColumn() {
            items(items = viewModel.expenses.value) {
                Card(
                    elevation = 4.dp,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(4.dp)
                        .clickable { }
                ) {
                    Row(modifier = Modifier.padding(8.dp)) {
                        Text(
                            text = "${it.name}",
                        )
                        Spacer(Modifier.weight(1f))
                        Text(
                            text = "${it.expense}",
                        )

                    }
                }
            }
        }
        Text(
            text = "Total: " + total
        )

    }
}
