package com.example.smartbiz.viewmodel

import androidx.lifecycle.ViewModel
import com.example.smartbiz.data.Repository

class RegisterViewModel(private val repository: Repository) : ViewModel() {

    fun register(username: String, email: String, password: String) = repository.postRegister(username, email, password)
}