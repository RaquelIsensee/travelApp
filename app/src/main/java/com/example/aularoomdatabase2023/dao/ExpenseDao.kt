package com.example.aularoomdatabase2023.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.aularoomdatabase2023.entity.Expense
import com.example.aularoomdatabase2023.entity.User

@Dao
interface  ExpenseDao {

    @Insert
    fun insert(expense: Expense)

    @Update
    suspend fun update(expense: Expense)

    @Delete
    suspend fun delete(expense: Expense)

    @Query("select * from expense e where e.travelId = :travelId")
    suspend fun getBugdet(travelId: Int): List<Expense>

}