package com.example.smartbiz.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smartbiz.data.Repository
import com.example.smartbiz.data.Result
import com.example.smartbiz.response.RecoveryPassword
import com.example.smartbiz.response.ResetToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RecoveryPasswordViewModel(private val repository: Repository) : ViewModel() {
    val recoveryPassword = MutableLiveData<Result<RecoveryPassword>>()

    fun recoverPassword(email: String, token: String, newPassword: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.recoverPassword(email, token, newPassword)
            if (response.isSuccessful) {
                response.body()?.let {
                    Log.d("RecoveryPassword", it.toString())
                }
            } else {
                Log.d("RecoveryPassword", response.errorBody().toString())
            }
            }
        }
    }
