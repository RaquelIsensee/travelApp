package com.example.aularoomdatabase2023.repository

import com.example.aularoomdatabase2023.dao.TravelDao
import com.example.aularoomdatabase2023.entity.Travel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TravelRepository(private val travelDao: TravelDao) {

        private val coroutine = CoroutineScope(Dispatchers.Main)

        fun addTravel(travel: Travel) {
            coroutine.launch(Dispatchers.IO) {
                travelDao.insert(travel)
            }
        }

        fun delete(travel: Travel) {
            coroutine.launch(Dispatchers.IO) {
                travelDao.delete(travel)
            }
        }

        suspend fun loadAllTravels(): List<Travel> {
            return travelDao.getAll()
        }
    }
