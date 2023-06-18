package com.example.aularoomdatabase2023.repository

import com.example.aularoomdatabase2023.dao.ExpenseDao
import com.example.aularoomdatabase2023.entity.Expense
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ExpenseRepository (private val expenseDao : ExpenseDao) {

    private val coroutine = CoroutineScope(Dispatchers.Main)

    fun addExpense(expense: Expense) {
        coroutine.launch(Dispatchers.IO) {
            expenseDao.insert(expense)
        }
    }

    suspend fun findByTravel(travelId: Int): List<Expense> =
        expenseDao.getBugdet(travelId)

}