package com.example.smartbiz.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.smartbiz.data.Repository
import com.example.smartbiz.response.Login
import com.example.smartbiz.data.Result
import com.example.smartbiz.response.ResponseLogin

class LoginViewModel(private val repository: Repository) : ViewModel() {

    fun login(username: String, password: String): LiveData<Result<ResponseLogin>> {
        val resultLiveData = MutableLiveData<Result<ResponseLogin>>()

        repository.postLogin(username, password).observeForever { result ->
            when (result) {
                is Result.Loading -> {
                    // Tampilkan loading state jika diperlukan
                    resultLiveData.value = Result.Loading

                }
                is Result.Success -> {
                    // Tangani hasil sukses
                    resultLiveData.value = Result.Success(result.data)
                }
                is Result.Error -> {
                    // Tangani hasil kesalahan
                    resultLiveData.value = Result.Error(result.error)
                }
            }
        }

        return resultLiveData
    }
}
