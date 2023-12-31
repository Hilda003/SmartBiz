package com.example.smartbiz.viewmodel

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smartbiz.data.Repository
import com.example.smartbiz.response.GetAllItem
import com.example.smartbiz.response.GetDetailProfile
import kotlinx.coroutines.launch

class ProfileViewModel(private val repository: Repository) : ViewModel() {
    val userData = MutableLiveData<GetDetailProfile>()

    fun getDetailProfile(userId: Int) {
        viewModelScope.launch {
            try {
                val result = repository.getDetailProfile(userId)
                userData.postValue(result)
            } catch (e: Exception) {
                val errorMessage = e.message ?: "Unknown error"
                Log.d("Error", errorMessage)
            }
        }
    }


}