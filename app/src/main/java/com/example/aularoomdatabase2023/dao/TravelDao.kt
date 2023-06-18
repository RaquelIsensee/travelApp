package com.example.aularoomdatabase2023.dao

import androidx.room.*
import com.example.aularoomdatabase2023.entity.Travel


@Dao
    interface TravelDao {

        @Insert
        fun insert(travel: Travel)

        @Update
        suspend fun update(travel: Travel)

        @Delete
        suspend fun delete(travel: Travel)

        @Query("select * from travel t order by t.destination ")
        suspend fun getAll(): List<Travel>

        @Query("select * from travel t where t.destination = :destination")
        suspend fun findByDestination(destination: String): Travel

        @Query("select * from travel t where t.userId = :userId")
        suspend fun findById(userId: Int):  List<Travel>
        
}
