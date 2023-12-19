package com.example.smartbiz.viewmodel

import androidx.lifecycle.ViewModel
import com.example.smartbiz.data.Repository

class LoginViewModel(private val repository: Repository) : ViewModel() {

    fun login(username: String, password: String) = repository.postLogin(username, password)


}