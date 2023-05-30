package com.example.aularoomdatabase2023.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aularoomdatabase2023.entity.Travel
import com.example.aularoomdatabase2023.repository.TravelRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class RegisterNewTravelViewModel(private val travelRepository: TravelRepository): ViewModel() {

    var destination by mutableStateOf("")
    var classification by mutableStateOf("")
    var begin by mutableStateOf("")
    var end by mutableStateOf("")
    var budget by mutableStateOf("")
    var userId = Int


    var isDestinationValid by mutableStateOf(true)
    var isClassificationValid by mutableStateOf(true)
    var isBeginValid by mutableStateOf(true)
    var isEndValid by mutableStateOf(true)
    var isBudgetValid by mutableStateOf(true)


    private val _toastMessage = MutableSharedFlow<String>()
    val toastMessage = _toastMessage.asSharedFlow()

    private fun validateFields() {
        destination = destination.isNotEmpty().toString()
        if (!isDestinationValid) {
            throw Exception("Destination is required")
        }
        classification = classification.isNotEmpty().toString()
        if (!isClassificationValid) {
            throw Exception("Classification is required")
        }
        begin = begin.isNotEmpty().toString()
        if (!isBeginValid) {
            throw Exception("Start date is required")
        }
        end = end.isNotEmpty().toString()
        if (!isEndValid) {
            throw Exception("End date is required")
        }
        if (begin <= end ) {
            throw Exception("Started date should be before end date")
        }
        budget = budget.isNotEmpty().toString()
        if (!isBudgetValid) {
            throw Exception("Budget is required")
        }

    }

    fun registerNewTravel(onSuccess: () -> Unit) {
        try {
            validateFields()
            val newTravel = Travel(destination = destination, classification = classification, begin = begin, end = end, budget = budget, userId = 1)
            travelRepository.addTravel(newTravel)
            onSuccess()
        }
        catch (e: Exception) {
            viewModelScope.launch {
                _toastMessage.emit(e.message?: "Unknown error")
            }
        }

    }

}