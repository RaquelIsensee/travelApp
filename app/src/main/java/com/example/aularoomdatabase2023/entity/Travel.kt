package com.example.aularoomdatabase2023.entity
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "travel")
data class Travel(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val destination: String,
    val classification: String,
    val begin: String,
    val end: String,
    val userId: Int
) {

}