package com.example.aularoomdatabase2023.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aularoomdatabase2023.entity.Expense
import com.example.aularoomdatabase2023.repository.ExpenseRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class RegisterNewExpenseViewModel(private val expenseRepository: ExpenseRepository): ViewModel() {
    var name by mutableStateOf("")
    var expense by mutableStateOf(0.00f)
    var travelId by mutableStateOf(0)

    private val _toastMessage = MutableSharedFlow<String>()
    val toastMessage = _toastMessage.asSharedFlow()

    fun registerNewExpense(travelId: Int, onSuccess: () -> Unit) {
        try {
            val newExpense = Expense(name = name, expense = expense, travelId = travelId)
            expenseRepository.addExpense(newExpense)
            onSuccess()
        }
        catch (e: Exception) {
            viewModelScope.launch {
                _toastMessage.emit(e.message?: "Unknown error")
            }
        }

    }
}