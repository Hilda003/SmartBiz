package com.example.smartbiz.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smartbiz.data.Repository
import com.example.smartbiz.data.Result
import com.example.smartbiz.response.ResetToken
import kotlinx.coroutines.launch

class ForgotPasswordViewModel(private val repository: Repository) : ViewModel() {

     val resetToken = MutableLiveData<Result<ResetToken>>()

    fun requestToken(email : String) {
        viewModelScope.launch {
            try {
                val response = repository.requestToken(email)
                if (response.status == "success") {
                    resetToken.value = Result.Success(response)
                }

            } catch (e: Exception) {
                Log.d("ForgotPasswordError", e.message.toString())
            }
        }
    }
}