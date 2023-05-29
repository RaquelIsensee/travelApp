package com.example.aularoomdatabase2023.viewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.aularoomdatabase2023.database.AppDatabase
import com.example.aularoomdatabase2023.repository.UserRepository

class LoginViewModelFactory(val application: Application) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val dao = AppDatabase.getDatabase(application).userDao()
        val userRepository = UserRepository(dao)
        return LoginViewModel(userRepository) as T
    }

}
