package com.example.aularoomdatabase2023.viewModel

import android.util.Log
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aularoomdatabase2023.entity.User
import com.example.aularoomdatabase2023.repository.UserRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch


class LoginViewModel(private val userRepository: UserRepository): ViewModel() {

    var name by mutableStateOf("")
    var password by mutableStateOf("")

    private val _toastMessage = MutableSharedFlow<String>()
    val toastMessage = _toastMessage.asSharedFlow()

//TODO: add trim
    fun validateLogin(onResult: (Int) -> Unit) {
        viewModelScope.launch {
            val user = userRepository.findByName(name)
            val result = user != null && user.password == password
            if (user != null) {
                onResult(user.id)
            }
            if (!result )
                _toastMessage.emit("Invalid login")
        }

    }








}