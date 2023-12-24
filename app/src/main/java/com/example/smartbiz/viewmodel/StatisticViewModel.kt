package com.example.smartbiz.viewmodel

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smartbiz.data.Repository
import com.example.smartbiz.response.Profit
import kotlinx.coroutines.launch

class StatisticViewModel(private val repository: Repository) : ViewModel() {
    val userData = MutableLiveData<Profit>()


    fun getAllTransaction(userId: Int) {
        viewModelScope.launch {
            try {
                val result = repository.getAllTransaction(userId)
                userData.postValue(result)
            } catch (e: Exception) {
                val errorMessage = e.message ?: "Unknown error"
                Log.d("Error", errorMessage)
            }
        }
    }


}