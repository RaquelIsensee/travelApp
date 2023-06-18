package com.example.aularoomdatabase2023.viewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aularoomdatabase2023.entity.Travel
import com.example.aularoomdatabase2023.repository.TravelRepository
import kotlinx.coroutines.launch

class ListTravelViewModel (private val travelRepository: TravelRepository): ViewModel() {

    var travels: MutableState<List<Travel>> = mutableStateOf(listOf())

    fun loadAllTravels(userId: Int) {
        viewModelScope.launch {
            travels.value = travelRepository.findById(userId)
        }
    }
}

